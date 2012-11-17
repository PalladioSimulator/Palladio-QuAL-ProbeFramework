package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.AbstractUnaryCalculator;

public class PlusOneCalculator extends AbstractUnaryCalculator<Integer, Integer, Long> {

    public PlusOneCalculator(Probe<Integer> boundedProbe) {
        super(boundedProbe, Integer.class, Integer.class);
    }

    @Override
    public Integer calculate(Integer value) {
        return ++value;
    }

    @Override
    public void measurementArrived(Measurement<Integer, Long> measurement, Probe<Integer> probe, IBlackboard<Long> blackboard) {
        blackboard.addMeasurement(calculate(measurement.getValue()), getBoundedProbe());
    }

}
