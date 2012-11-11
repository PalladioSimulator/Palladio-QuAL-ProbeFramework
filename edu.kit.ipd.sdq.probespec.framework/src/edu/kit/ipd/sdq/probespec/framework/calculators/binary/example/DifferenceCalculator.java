package edu.kit.ipd.sdq.probespec.framework.calculators.binary.example;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.ProbeSpecContext;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.AbstractBinaryCalculator;

public class DifferenceCalculator extends AbstractBinaryCalculator<Integer, Integer, Integer> {

    private Integer firstMeasurement;

    public DifferenceCalculator(ProbeSpecContext ctx) {
        super(ctx, Integer.class, Integer.class, Integer.class);
    }

    @Override
    public Integer calculate(Integer firstProbe, Integer secondProbe) {
        return Math.abs(firstProbe - secondProbe);
    }

    @Override
    public void firstMeasurementArrived(Integer measurement, Probe<Integer> probe) {
        this.firstMeasurement = measurement;
    }

    @Override
    public void secondMeasurementArrived(Integer measurement, Probe<Integer> probe) {
        Integer secondMeasurement = measurement;
        Integer c = calculate(firstMeasurement, secondMeasurement);
        ctx.addIntegerMeasurement(c, getBoundedProbe());
    }

}
