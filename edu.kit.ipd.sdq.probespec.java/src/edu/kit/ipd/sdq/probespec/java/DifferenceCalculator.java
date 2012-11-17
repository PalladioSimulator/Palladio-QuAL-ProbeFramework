package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.AbstractBinaryCalculator;

public class DifferenceCalculator extends AbstractBinaryCalculator<Integer, Integer, Integer, Long> {

    private Integer firstMeasurement;

    public DifferenceCalculator(Probe<Integer> boundedProbe) {
        super(boundedProbe, Integer.class, Integer.class, Integer.class);
    }

    @Override
    public Integer calculate(Integer firstProbe, Integer secondProbe) {
        return Math.abs(firstProbe - secondProbe);
    }

    @Override
    public void firstMeasurementArrived(Measurement<Integer, Long> measurement, Probe<Integer> probe,
            IBlackboard<Long> blackboard) {
        this.firstMeasurement = measurement.getValue();
    }

    @Override
    public void secondMeasurementArrived(Measurement<Integer, Long> measurement, Probe<Integer> probe,
            IBlackboard<Long> blackboard) {
        Integer secondMeasurement = measurement.getValue();
        Integer c = calculate(firstMeasurement, secondMeasurement);
        blackboard.addMeasurement(c, getBoundedProbe());
    }

}
