package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.ProbeRepository;
import edu.kit.ipd.sdq.probespec.probespecFactory;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.IUnaryCalculator;

public class ProbeSpecContext {

	private IBlackboard blackboard;
	
	private ProbeRepository repository;
	
	private ProbeFactory probeFactory;
	
	private static final ProbeRepository EMPTY_REPOSITORY = probespecFactory.eINSTANCE.createProbeRepository();
	
	private static final IBlackboard DEFAULT_BLACKBOARD = new SimpleBlackboard();
	
	public ProbeSpecContext() {
		this(EMPTY_REPOSITORY, DEFAULT_BLACKBOARD);
	}
	
	public ProbeSpecContext(IBlackboard blackboard) {
		this(EMPTY_REPOSITORY, blackboard);
	}
	
	public ProbeSpecContext(ProbeRepository repository) {
	    this(repository, DEFAULT_BLACKBOARD);
	}
	
	public ProbeSpecContext(ProbeRepository repository, IBlackboard blackboard) {
	    this.repository = repository;
        this.blackboard = blackboard;
        this.probeFactory = new ProbeFactory();
    }

	public IBlackboard getBlackboard() {
		return blackboard;
	}

    public ProbeRepository getRepository() {
        return repository;
    }

    public ProbeFactory getProbeFactory() {
        return probeFactory;
    }
    
    public <I, O> void bind(DerivedProbe<O> derivedProbe, IUnaryCalculator<I, O> calculator, Probe<I> sourceProbe) {
        calculator.setBoundedProbe(derivedProbe);
        getBlackboard().addBlackboardListener(calculator.getListener(), sourceProbe);
    }
    
    public <I1, I2, O> void bind(DerivedProbe<O> derivedProbe, IBinaryCalculator<I1, I2, O> calculator, Probe<I1> sourceProbe1, Probe<I2> sourceProbe2) {
        calculator.setBoundedProbe(derivedProbe);
        getBlackboard().addBlackboardListener(calculator.getFirstListener(), sourceProbe1);
        getBlackboard().addBlackboardListener(calculator.getSecondListener(), sourceProbe2);
    }
    
}
