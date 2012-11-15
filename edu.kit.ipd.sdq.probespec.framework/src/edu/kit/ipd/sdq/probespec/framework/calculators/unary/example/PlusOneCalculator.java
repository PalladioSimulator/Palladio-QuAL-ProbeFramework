package edu.kit.ipd.sdq.probespec.framework.calculators.unary.example;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.Measurements;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.AbstractUnaryCalculator;

public class PlusOneCalculator extends AbstractUnaryCalculator<Integer, Integer> {

    public PlusOneCalculator(Probe<Integer> boundedProbe) {
        super(boundedProbe, Integer.class, Integer.class);
    }

    @Override
    public Integer calculate(Integer value) {
        return ++value;
    }

    @Override
    public void measurementArrived(Integer measurement, Probe<Integer> probe, IBlackboard blackboard) {
        blackboard.addMeasurement(Measurements.create(calculate(measurement)), getBoundedProbe());
    }

}
