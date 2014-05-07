package org.palladiosimulator.probeframework.calculator.internal;

import java.util.List;

import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementTuple;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.probeframework.exceptions.CalculatorException;
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
     * @param probe
     *            The observed probe.
     */
    public IdentityCalculator(final Probe probe) {
        super(probe.getMetricDesciption(), probe);
    }

    /**
     * This calculation directly lets a single probe measurement pass through.
     * 
     * @param probeMeasurements
     *            The list of probe measurements is expected to contain a single measurement of type
     *            MeasurementTuple.
     * @return The probe measurement value wrapped in a measurement.
     * @throws CalculatorException
     *             In case the first list element is not a MeasurementTuple.
     * @see org.palladiosimulator.probeframework.calculator.Calculator#calculate
     */
    @Override
    protected Measurement calculate(final List<ProbeMeasurement> probeMeasurements) throws CalculatorException {
        final Measurement measurement = probeMeasurements.get(0).getMeasurement();
        if (measurement instanceof MeasurementTuple) {
            return new MeasurementTuple(((MeasurementTuple) measurement).getSubsumedMeasurements(),
                    (MetricSetDescription) this.getMetricDesciption());
        } else {
            throw new CalculatorException("MeasurementTuple expected for identity calculators");
        }
    }
}
