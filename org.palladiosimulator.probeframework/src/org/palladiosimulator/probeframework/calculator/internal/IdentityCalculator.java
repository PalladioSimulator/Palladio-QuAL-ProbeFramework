package org.palladiosimulator.probeframework.calculator.internal;

import java.util.List;

import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementTupple;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Identity calculators are unary calculators that directly let probe measurements pass through. For
 * example, the current state of a "take passive resource state probe" may directly be passed
 * through in order to determine its utilization.
 * 
 * @author Sebastian Lehrig
 */
public class IdentityCalculator extends UnaryCalculator {

    /**
     * Default constructor. Directly passes all elements to the unary calculator constructor.
     * 
     * @param ctx
     *            the {@link ProbeFrameworkContext}
     * @param probe
     *            the referred probe
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
        if (measurement instanceof MeasurementTupple) {
            return new MeasurementTupple(((MeasurementTupple) measurement).getSubsumedMeasurements(),
                    (MetricSetDescription) this.getMetricDesciption());
        } else {
            throw new IllegalStateException("MeasurementTupple expected for identity calculators");
        }
    }
}
