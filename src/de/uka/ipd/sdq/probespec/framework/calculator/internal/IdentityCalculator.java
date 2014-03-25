package de.uka.ipd.sdq.probespec.framework.calculator.internal;

import java.util.List;

import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;

import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet;
import de.uka.ipd.sdq.probespec.framework.probes.Probe;

public class IdentityCalculator extends UnaryCalculator {

    /**
     * Constructor. It takes a reference of the blackboard and the ID of the probe set element taken
     * from the model.
     * 
     * @param ctx
     *            the {@link ProbeSpecContext}
     * @param probeSetID
     *            ID of the probe set element from the model
     */
    public IdentityCalculator(final ProbeSpecContext ctx, final Probe probe) {
        super(ctx, probe.getMetricDesciption(), probe);
    }

    /**
     * @see de.uka.ipd.sdq.probespec.framework.calculator.Calculator#calculate
     *      (de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet)
     */
    @Override
    protected Measurement calculate(final List<Measurement> probeMeasurements) {
        final Measurement measurement = probeMeasurements.get(0);
        if(measurement instanceof MeasurementSet) {
            return new MeasurementSet(((MeasurementSet) measurement).getSubsumedMeasurements(), (MetricSetDescription) this.metricDesciption, this);
        }
        else {
            throw new IllegalStateException("MeasurementSet expected for identity calculators");
        }
    }
}
