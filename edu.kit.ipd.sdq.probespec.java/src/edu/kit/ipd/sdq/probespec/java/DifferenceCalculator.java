package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.framework.Measurement;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.AbstractBinaryCalculator;

public class DifferenceCalculator extends AbstractBinaryCalculator<Integer, Integer, Integer, Long> {

    public DifferenceCalculator() {
        // Provide generic class parameters to super class. This is necessary because generic
        // type parameters are "erased" with compilation, thus making them unavailable at runtime.
        //
        // // IN1-class //// IN2-class ///// OUT-class
        super(Integer.class, Integer.class, Integer.class);
    }

    @Override
    public void setupBlackboardReader(BlackboardReader<Integer, Long> in1Reader,
            BlackboardReader<Integer, Long> in2Reader) {
        this.in1Reader = in1Reader;
        this.in2Reader = in2Reader;
    }

    @Override
    public void calculate(Probe<?> probe, MeasurementContext... contexts) {
        Measurement<Integer, Long> mm1 = in1Reader.getLatestMeasurement();
        Measurement<Integer, Long> mm2 = in2Reader.getLatestMeasurement();

        if (mm1 != null && mm2 != null) {
            Integer value = Math.abs(mm1.getValue() - mm2.getValue());
            outWriter.addMeasurement(value, contexts);
        }
    }

}
