package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.AbstractUnaryCalculator;

public class PlusOneCalculator extends AbstractUnaryCalculator<Integer, Integer, Long> {

    private BlackboardReader<Integer, Long> inReader;

    public PlusOneCalculator() {
        // Provide generic class parameters to super class. This is necessary because generic
        // type parameters are "erased" with compilation, thus making them unavailable at runtime.
        //
        // // IN1-class //// OUT-class
        super(Integer.class, Integer.class);
    }

    @Override
    public void setupBlackboardReader(BlackboardReader<Integer, Long> reader) {
        this.inReader = reader;

    }

    @Override
    public void calculate(Probe<?> probe, MeasurementContext... contexts) {
        Integer value = inReader.getLatestMeasurement().getValue() + 1;
        outWriter.addMeasurement(value);
    }

}
