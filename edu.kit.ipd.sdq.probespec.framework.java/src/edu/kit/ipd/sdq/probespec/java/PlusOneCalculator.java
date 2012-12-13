package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.ProbeMeasurementsProxy;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.AbstractUnaryCalculator;

public class PlusOneCalculator extends AbstractUnaryCalculator<Integer, Integer, Long> {

    public PlusOneCalculator(DerivedProbe<Integer> outputProbe) {
        // Provide generic class parameters to super class. This is necessary because generic
        // type parameters are "erased" with compilation, thus making them unavailable at runtime.
        //
        // /////////////// IN1-class //// OUT-class
        super(outputProbe, Integer.class, Integer.class);
    }

    @Override
    public Integer calculate(ProbeMeasurementsProxy<Integer, Long> proxy) {
        return proxy.getLatestMeasurement().getValue() + 1;
    }

}
