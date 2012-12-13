package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.ProbeMeasurementsProxy;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.AbstractBinaryCalculator;

public class DifferenceCalculator extends AbstractBinaryCalculator<Integer, Integer, Integer, Long> {

    public static int count;
    
    public DifferenceCalculator(DerivedProbe<Integer> outputProbe) {
        super(outputProbe, Integer.class, Integer.class, Integer.class);
    }

    @Override
    public Integer calculate(ProbeMeasurementsProxy<Integer, Long> proxy1, ProbeMeasurementsProxy<Integer, Long> proxy2) {
        count++;
        
        Measurement<Integer, Long> mm1 = proxy1.getLatestMeasurement();
        Measurement<Integer, Long> mm2 = proxy2.getLatestMeasurement();

        if (mm1 != null && mm2 != null) {
            return Math.abs(mm1.getValue() - mm2.getValue());
        } else {
            return null;
        }
    }
    

}
