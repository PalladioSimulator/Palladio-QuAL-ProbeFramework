package org.palladiosimulator.probeframework.calculator.internal;

import java.util.List;

import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementTupple;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
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
     *      (org.palladiosimulator.measurementspec.MeasurementTupple)
     */
    @Override
    protected Measurement calculate(final List<ProbeMeasurement> probeMeasurements) {
        final Measurement measurement = probeMeasurements.get(0).getMeasurement();
        if(measurement instanceof MeasurementTupple) {
            return new MeasurementTupple(((MeasurementTupple) measurement).getSubsumedMeasurements(), (MetricSetDescription) this.metricDesciption);
        }
        else {
            throw new IllegalStateException("MeasurementTupple expected for identity calculators");
        }
    }
}
