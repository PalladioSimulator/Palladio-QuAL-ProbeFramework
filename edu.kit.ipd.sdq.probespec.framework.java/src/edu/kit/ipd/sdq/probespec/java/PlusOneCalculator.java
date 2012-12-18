package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
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
    public void calculate(Probe<?> probe, IMeasurementContext... contexts) {
        Integer value = inReader.getLatestMeasurement().getValue() + 1;
        outWriter.addMeasurement(value);
    }

}
