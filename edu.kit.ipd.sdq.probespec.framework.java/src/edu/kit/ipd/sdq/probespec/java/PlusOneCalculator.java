package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.AbstractUnaryCalculator;

public class PlusOneCalculator extends AbstractUnaryCalculator<Integer, Integer, Long> {

    private IBlackboardReader<Integer, Long> inReader;
    
    public PlusOneCalculator(DerivedProbe<Integer> outputProbe) {
        // Provide generic class parameters to super class. This is necessary because generic
        // type parameters are "erased" with compilation, thus making them unavailable at runtime.
        //
        // /////////////// IN1-class //// OUT-class
        super(outputProbe, Integer.class, Integer.class);
    }

    @Override
    public void setupBlackboardAccess(IBlackboardReader<Integer, Long> reader) {
        this.inReader = reader;
        
    }
    
    @Override
    public Integer calculate(Probe<?> probe, IMeasurementContext... contexts) {
        return inReader.getLatestMeasurement().getValue() + 1;
    }

}
