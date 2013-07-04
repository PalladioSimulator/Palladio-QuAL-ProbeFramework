package edu.kit.ipd.sdq.probespec.pcm;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.ILookupStrategy;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.SameOrParentContextLookupStrategy;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.AbstractBinaryCalculator;
import edu.kit.ipd.sdq.probespec.pcm.contexts.UsageContext;

public class ResponseTimeCalculator extends AbstractBinaryCalculator<Double, Double, Double, Double> {

    public ResponseTimeCalculator() {
        super(Double.class, Double.class, Double.class);
    }

    @Override
    public void calculate(Probe<?> probe, IMeasurementContext... contexts) {
        if (probe.equals(in1Probe)) {
            return;
        }

        ILookupStrategy lookupStrategy = new SameOrParentContextLookupStrategy(UsageContext.class);
        Measurement<Double, Double> mm1 = in1Reader.getLatestMeasurement(lookupStrategy, contexts);
        Measurement<Double, Double> mm2 = in2Reader.getLatestMeasurement(contexts);

        if (mm1 != null) {
            assert (mm1.getTimestamp() < mm2.getTimestamp());
            Double value = mm2.getValue() - mm1.getValue();
            outWriter.addMeasurement(value, contexts);
        }
    }

}
