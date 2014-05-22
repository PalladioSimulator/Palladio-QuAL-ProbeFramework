package org.palladiosimulator.probeframework.calculator.internal;

import java.util.List;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.measurementframework.Measurement;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.metricentity.IMetricEntity;
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
     * @param measuringPoint
     *            MeasuringPoint as needed by the superclass.
     * @param probe
     *            The observed probe.
     */
    public IdentityCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        super(getMetricDescriptionFromProbe(probe), measuringPoint, probe);
    }

    /**
     * Returns a metric description from a given probe.
     * 
     * @param probe
     *            The probe providing a metric description.
     * @return The metric description.
     * @throws IllegalArgumentException
     *             If the probe is not an IMetricEntity.
     */
    private static MetricDescription getMetricDescriptionFromProbe(final Probe probe) {
        if (!(probe instanceof IMetricEntity)) {
            throw new IllegalArgumentException(
                    "IdentityCalculator can only cope with probes that provide metrics, i.e., BasicProbes.");
        }

        return ((IMetricEntity) probe).getMetricDesciption();
    }

    /**
     * This calculation directly lets a single probe measurement pass through.
     * 
     * @param probeMeasurements
     *            The list of probe measurements is expected to contain a single measurement of type
     *            TupleMeasurement.
     * @return The probe measurement value wrapped in a measurement.
     * @throws CalculatorException
     *             In case the first list element is not a TupleMeasurement.
     * @see org.palladiosimulator.probeframework.calculator.Calculator#calculate
     */
    @Override
    protected Measurement calculate(final List<ProbeMeasurement> probeMeasurements) throws CalculatorException {
        final Measurement measurement = (Measurement) probeMeasurements.get(0).getMeasureProvider();
        if (measurement instanceof TupleMeasurement) {
            return measurement;
        } else {
            throw new CalculatorException("TupleMeasurement expected for identity calculators");
        }
    }
}
