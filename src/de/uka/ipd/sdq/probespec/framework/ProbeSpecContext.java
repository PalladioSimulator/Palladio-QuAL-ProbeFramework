package de.uka.ipd.sdq.probespec.framework;

import java.util.HashSet;
import java.util.Set;

import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory;
import de.uka.ipd.sdq.probespec.framework.concurrency.ThreadManager;
import de.uka.ipd.sdq.probespec.framework.garbagecollection.IRegionBasedGarbageCollector;
import de.uka.ipd.sdq.probespec.framework.probes.IProbeStrategyRegistry;
import de.uka.ipd.sdq.probespec.framework.utils.ProbeSetIDGenerator;

public class ProbeSpecContext {
	
	private IProbeStrategyRegistry probeStrategyRegistry;
	
	private ICalculatorFactory calculatorFactory;
	
	private ISampleBlackboard sampleBlackboard;
	
	private final ThreadManager threadManager;
	
	private final ProbeSetIDGenerator probeSetIdGenerator;
	
	private IRegionBasedGarbageCollector<RequestContext> blackboardGarbageCollector;
	
	private final Set<Calculator> calculators;

	private boolean initialised;

	public ProbeSpecContext() {
		threadManager = new ThreadManager();
		probeSetIdGenerator = new ProbeSetIDGenerator();
		calculators = new HashSet<Calculator>();
	}
	
	public void initialise(ISampleBlackboard blackboard,
            IProbeStrategyRegistry probeStrategyRegistry,
            ICalculatorFactory calculatorFactory) {
        this.sampleBlackboard = blackboard;
        this.probeStrategyRegistry = probeStrategyRegistry;
        this.calculatorFactory = calculatorFactory;
        this.initialised = true;
	}
	
	public void finish() {
	    throwExceptionIfNotInitialised();
	    
		// stop registered threads
		getThreadManager().stopThreads();
		
		// remove all listeners from used calculators
		for(Calculator c : calculators) {
			c.unregisterCalculatorListeners();
		}
		
		// clear calculators
		calculators.clear();
	}
	
	public Integer obtainProbeSetId(String probeSetId) {
		return probeSetIdGenerator.obtainId(probeSetId);
	}
	
	public String obtainOriginalProbeSetId(Integer probeSetId) {
		return probeSetIdGenerator.obtainOriginalId(probeSetId);
	}

	public IProbeStrategyRegistry getProbeStrategyRegistry() {
	    throwExceptionIfNotInitialised();
		return probeStrategyRegistry;
	}

	public void setProbeStrategyRegistry(
			IProbeStrategyRegistry probeStrategyRegistry) {
		this.probeStrategyRegistry = probeStrategyRegistry;
	}

	public ICalculatorFactory getCalculatorFactory() {
	    throwExceptionIfNotInitialised();
		return calculatorFactory;
	}

	public void setCalculatorFactory(ICalculatorFactory calculatorFactory) {
		this.calculatorFactory = calculatorFactory;
	}

	public ISampleBlackboard getSampleBlackboard() {
	    throwExceptionIfNotInitialised();
		return sampleBlackboard;
	}

	public void setSampleBlackboard(ISampleBlackboard sampleBlackboard) {
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
	
	
    private void throwExceptionIfNotInitialised() {
        if (!initialised) {
            throw new RuntimeException("Initialise the ProbeSpecification context before using it.");
        }
    }
	
	public void addCalculator(Calculator calculator) {
		this.calculators.add(calculator);
	}

	public Calculator getCalculatorForId(String calculatorId) {
		for (Calculator c : calculators) {
			if(c.getCalculatorId().equals(calculatorId)) {
				return c;
			}
		}
		
		return null;
	}
	
	public void registerCalculator(String calculatorId, Calculator calculator) {
		calculator.setCalculatorId(calculatorId);
	}
}
