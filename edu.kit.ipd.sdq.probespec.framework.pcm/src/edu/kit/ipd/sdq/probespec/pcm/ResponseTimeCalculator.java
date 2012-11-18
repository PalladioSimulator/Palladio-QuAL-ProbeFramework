package edu.kit.ipd.sdq.probespec.pcm;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.ProbeMeasurementsProxy;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.AbstractBinaryCalculator;

public class ResponseTimeCalculator extends AbstractBinaryCalculator<Double, Double, Double, Double> {

    public ResponseTimeCalculator(Probe<Double> derivedProbe) {
        super(derivedProbe, Double.class, Double.class, Double.class);
    }

    @Override
    public Double calculate(ProbeMeasurementsProxy<Double, Double> proxy1, ProbeMeasurementsProxy<Double, Double> proxy2) {
        return null;
    }

}
