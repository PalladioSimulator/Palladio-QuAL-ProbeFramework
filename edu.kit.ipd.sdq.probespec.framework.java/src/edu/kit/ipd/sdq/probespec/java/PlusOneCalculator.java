package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.ProbeMeasurementsProxy;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.AbstractUnaryCalculator;

public class PlusOneCalculator extends AbstractUnaryCalculator<Integer, Integer, Long> {

    public PlusOneCalculator(Probe<Integer> derivedProbe) {
        super(derivedProbe, Integer.class, Integer.class);
    }

    @Override
    public Integer calculate(ProbeMeasurementsProxy<Integer, Long> proxy) {
        return proxy.getLatestMeasurement().getValue() + 1;
    }

}
