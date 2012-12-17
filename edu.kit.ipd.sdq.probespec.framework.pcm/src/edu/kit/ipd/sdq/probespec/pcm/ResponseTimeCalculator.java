package edu.kit.ipd.sdq.probespec.pcm;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.ILookupStrategy;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.SameOrParentContextLookupStrategy;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.AbstractBinaryCalculator;
import edu.kit.ipd.sdq.probespec.pcm.contexts.UsageContext;

public class ResponseTimeCalculator extends AbstractBinaryCalculator<Double, Double, Double, Double> {

    public ResponseTimeCalculator(DerivedProbe<Double> outputProbe) {
        super(outputProbe, Double.class, Double.class, Double.class);
    }

    @Override
    public Double calculate(Probe<?> probe, IMeasurementContext... contexts) {
        if (probe.equals(in1Probe)) {
            return null;
        }

        ILookupStrategy lookupStrategy = new SameOrParentContextLookupStrategy(UsageContext.class);
        Measurement<Double, Double> mm1 = in1Reader.getLatestMeasurement(lookupStrategy, contexts);
        Measurement<Double, Double> mm2 = in2Reader.getLatestMeasurement(contexts);

        if (mm1 != null) {
            assert (mm1.getTimestamp() < mm2.getTimestamp());
            return mm2.getValue() - mm1.getValue();
        } else {
            return null;
        }

    }

}
