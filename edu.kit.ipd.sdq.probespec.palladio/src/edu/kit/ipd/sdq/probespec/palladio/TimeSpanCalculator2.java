package edu.kit.ipd.sdq.probespec.palladio;

import java.math.BigDecimal;

import edu.kit.ipd.sdq.probespec.framework.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.LookupStrategy;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.SameOrParentContextLookupStrategy;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.AbstractBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;
import edu.kit.ipd.sdq.probespec.palladio.contexts.AssemblyContextWrapper;
import edu.kit.ipd.sdq.probespec.palladio.contexts.RequestContext2;

public class TimeSpanCalculator2 extends AbstractBinaryCalculator<Double, Double, Double, Double> {

    private AssemblyContextWrapper assemblyCtx;

    public TimeSpanCalculator2() {
        super(Double.class, Double.class, Double.class);
    }

    public TimeSpanCalculator2(AssemblyContextWrapper assemblyCtx) {
        super(Double.class, Double.class, Double.class);
    }

    @Override
    public void calculate(Probe<?> probe, MeasurementContext... contexts) {
        // nothing to calculate if we observe the 1st measurement because the 2nd measurement
        // is still pending
        if (probe.equals(in1Probe)) {
            return;
        }

        // if an assembly context has been specified, this calculator shall react only to
        // measurements originating from that assembly context
        if (assemblyCtx != null && !containsAssemblyContext(contexts, assemblyCtx)) {
            return;
        }

        // retrieve 1st and 2nd measurement
        LookupStrategy lookupStrategy = new SameOrParentContextLookupStrategy(RequestContext2.class);
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

    private boolean containsAssemblyContext(MeasurementContext[] contexts, AssemblyContextWrapper assemblyCtx) {
        for (MeasurementContext ctx : contexts) {
            if (ctx.equals(assemblyCtx)) {
                return true;
            }
        }
        return false;
    }

}
