package edu.kit.ipd.sdq.probespec.framework.calculators.binary.example;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.Measurements;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.AbstractBinaryCalculator;

public class DifferenceCalculator extends AbstractBinaryCalculator<Integer, Integer, Integer> {

    private Integer firstMeasurement;

    public DifferenceCalculator(Probe<Integer> boundedProbe) {
        super(boundedProbe, Integer.class, Integer.class, Integer.class);
    }

    @Override
    public Integer calculate(Integer firstProbe, Integer secondProbe) {
        return Math.abs(firstProbe - secondProbe);
    }

    @Override
    public void firstMeasurementArrived(Integer measurement, Probe<Integer> probe, IBlackboard blackboard) {
        this.firstMeasurement = measurement;
    }

    @Override
    public void secondMeasurementArrived(Integer measurement, Probe<Integer> probe, IBlackboard blackboard) {
        Integer secondMeasurement = measurement;
        Integer c = calculate(firstMeasurement, secondMeasurement);
        blackboard.addMeasurement(Measurements.create(c), getBoundedProbe());
    }

}
