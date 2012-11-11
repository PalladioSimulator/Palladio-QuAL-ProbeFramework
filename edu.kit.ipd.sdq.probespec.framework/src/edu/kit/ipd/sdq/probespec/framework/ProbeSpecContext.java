package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.ProbeRepository;
import edu.kit.ipd.sdq.probespec.probespecFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.SimpleBlackboard;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.BinaryCalculatorBinding;
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
    
    public <I, O> void registerUnaryCalculator(IUnaryCalculator<I, O> calculator, Probe<I> sourceProbe) {
//        calculator.setBoundedProbe(derivedProbe);
        getBlackboard().addMeasurementListener(calculator.getListener(), sourceProbe);
    }
    
    public <IN1, IN2, OUT> void registerBinaryCalculator(IBinaryCalculator<IN1, IN2, OUT> calculator, Probe<IN1> sourceProbe1, Probe<IN2> sourceProbe2) {
        BinaryCalculatorBinding<IN1, IN2, OUT> binding = new BinaryCalculatorBinding<IN1, IN2, OUT>(calculator);
        binding.bindInput(getBlackboard(), sourceProbe1, sourceProbe2);
    }
    
//    public <I1, I2, O> void bindCalculatorOutput(IBinaryCalculator<I1, I2, O> calculator, DerivedProbe<O> derivedProbe) {
//        calculator.setBoundedProbe(derivedProbe);
//    }
    
//    public <I1, I2, O> void bind(DerivedProbe<O> derivedProbe, IBinaryCalculator<I1, I2, O> calculator, Probe<I1> sourceProbe1, Probe<I2> sourceProbe2) {
//        calculator.setBoundedProbe(derivedProbe);
//        getBlackboard().addMeasurementListener(calculator.getFirstListener(), sourceProbe1);
//        getBlackboard().addMeasurementListener(calculator.getSecondListener(), sourceProbe2);
//    }
    
//    public void addIntegerMeasurement(Integer measurement, Probe<Integer> probe) {
//        blackboard.addMeasurement(measurement, probe);
//    }
//    
//    public void addDoubleMeasurement(Double measurement, Probe<Double> probe) {
//        blackboard.addMeasurement(measurement, probe);
//    }
    
    // TODO consider further types other than Integer and Double
    
    
}
