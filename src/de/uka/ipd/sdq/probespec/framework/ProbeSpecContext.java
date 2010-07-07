package de.uka.ipd.sdq.probespec.framework;

import de.uka.ipd.sdq.probespec.framework.concurrency.ThreadManager;
import de.uka.ipd.sdq.probespec.framework.probes.IProbeStrategyRegistry;
import de.uka.ipd.sdq.probespec.framework.utils.ProbeSetIDGenerator;

public class ProbeSpecContext {

	private static final ProbeSpecContext instance = new ProbeSpecContext();
	
	private IProbeStrategyRegistry probeStrategyRegistry;
	
	private ICalculatorFactory calculatorFactory;
	
	private SampleBlackboard sampleBlackboard;
	
	private ThreadManager threadManager;
	
	private ProbeSetIDGenerator probeSetIdGenerator;
	
	private IRegionBasedGarbageCollector<RequestContext> blackboardGarbageCollector;
	
	private ProbeSpecContext() {
		threadManager = new ThreadManager();
		probeSetIdGenerator = new ProbeSetIDGenerator();
	}
	
	public void initialise(
			SampleBlackboard sampleBlackboard,
			IRegionBasedGarbageCollector<RequestContext> blackboardGarbageCollector,
			IProbeStrategyRegistry probeStrategyRegistry,
			ICalculatorFactory calculatorFactory) {
		setSampleBlackboard(sampleBlackboard);
		setBlackboardGarbageCollector(blackboardGarbageCollector);
		setProbeStrategyRegistry(probeStrategyRegistry);
		setCalculatorFactory(calculatorFactory);
	}
	
	public static ProbeSpecContext instance() {
		return instance;
	}
	
	public void setHighestProbeSetID(Integer id) {
		probeSetIdGenerator.setHighestId(id);
	}
	
	public Integer obtainProbeSetId(String probeSetId) {
		return probeSetIdGenerator.obtainId(probeSetId);
	}

	public IProbeStrategyRegistry getProbeStrategyRegistry() {
		return probeStrategyRegistry;
	}

	public void setProbeStrategyRegistry(
			IProbeStrategyRegistry probeStrategyRegistry) {
		this.probeStrategyRegistry = probeStrategyRegistry;
	}

	public ICalculatorFactory getCalculatorFactory() {
		return calculatorFactory;
	}

	public void setCalculatorFactory(ICalculatorFactory calculatorFactory) {
		this.calculatorFactory = calculatorFactory;
	}

	public SampleBlackboard getSampleBlackboard() {
		return sampleBlackboard;
	}

	public void setSampleBlackboard(SampleBlackboard sampleBlackboard) {
		this.sampleBlackboard = sampleBlackboard;
	}

	public IRegionBasedGarbageCollector<RequestContext> getBlackboardGarbageCollector() {
		return blackboardGarbageCollector;
	}

	public void setBlackboardGarbageCollector(
			IRegionBasedGarbageCollector<RequestContext> blackboardGarbageCollector) {
		this.blackboardGarbageCollector = blackboardGarbageCollector;
	}

	public ThreadManager getThreadManager() {
		return threadManager;
	}
	
	
	
}
