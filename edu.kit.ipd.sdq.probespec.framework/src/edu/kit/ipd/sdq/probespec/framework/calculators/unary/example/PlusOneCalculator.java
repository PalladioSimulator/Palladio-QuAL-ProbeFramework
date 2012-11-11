package edu.kit.ipd.sdq.probespec.framework.calculators.unary.example;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.ProbeSpecContext;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.AbstractUnaryCalculator;

public class PlusOneCalculator extends AbstractUnaryCalculator<Integer, Integer> {

    public PlusOneCalculator(ProbeSpecContext ctx) {
        super(ctx, Integer.class, Integer.class);
    }

    @Override
    public Integer calculate(Integer value) {
        return ++value;
    }

    @Override
    public void singleMeasurementArrived(Integer measurement, Probe<Integer> probe) {
        ctx.addIntegerMeasurement(calculate(measurement), getBoundedProbe());
    }

}
