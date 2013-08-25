package edu.kit.ipd.sdq.probespec.pcm;

import java.math.BigDecimal;

import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.ILookupStrategy;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.SameOrParentContextLookupStrategy;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.AbstractBinaryCalculator;
import edu.kit.ipd.sdq.probespec.pcm.contexts.AssemblyContextWrapper;
import edu.kit.ipd.sdq.probespec.pcm.contexts.RequestContext2;

public class ResponseTimeCalculator2 extends AbstractBinaryCalculator<Double, Double, Double, Double> {

    private AssemblyContextWrapper assemblyCtx;
    
    public ResponseTimeCalculator2() {
        super(Double.class, Double.class, Double.class);
    }
    
    public ResponseTimeCalculator2(AssemblyContextWrapper assemblyCtx) {
        super(Double.class, Double.class, Double.class);
    }

    @Override
    public void calculate(Probe<?> probe, IMeasurementContext... contexts) {
        if (probe.equals(in1Probe)) {
            return;
        }        

        ILookupStrategy lookupStrategy = new SameOrParentContextLookupStrategy(RequestContext2.class);
        Measurement<Double, Double> mm1 = in1Reader.getLatestMeasurement(lookupStrategy, contexts);
        Measurement<Double, Double> mm2 = in2Reader.getLatestMeasurement(contexts);

        if (mm1 != null) {
            assert (mm1.getTimestamp() < mm2.getTimestamp());

            // the following type conversions shall ensure backward-compatibility to ProbeSpec 1.x
            // such that calculations yield exactly (!) the same result. Simply subtracting two
            // doubles reveals the limited double precision, which earlier ProbeSpec versions
            // addressed via BigDecimal.
            //
            // TODO: for a performance gain, the backward-compatibility could be dropped
            Double value = BigDecimal.valueOf(mm2.getValue()).subtract(BigDecimal.valueOf(mm1.getValue()))
                    .doubleValue();
            // Double value = mm2.getValue() - mm1.getValue();

            outWriter.addMeasurement(value, contexts);
        }
    }

}
