package org.palladiosimulator.probeframework.calculator.internal;

import java.util.List;

import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementSet;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.probes.Probe;

public class IdentityCalculator extends UnaryCalculator {

    /**
     * Constructor. It takes a reference of the blackboard and the ID of the probe set element taken
     * from the model.
     * 
     * @param ctx
     *            the {@link ProbeFrameworkContext}
     * @param probeSetID
     *            ID of the probe set element from the model
     */
    public IdentityCalculator(final ProbeFrameworkContext ctx, final Probe probe) {
        super(ctx, probe.getMetricDesciption(), probe);
    }

    /**
     * @see org.palladiosimulator.probeframework.calculator.Calculator#calculate
     *      (org.palladiosimulator.measurementspec.MeasurementSet)
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
