package org.palladiosimulator.probeframework.calculator;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.POINT_IN_TIME_METRIC;

import java.util.List;

import org.apache.log4j.Logger;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.listener.IMeasurementSourceListener;
import org.palladiosimulator.measurementspec.listener.MeasurementSource;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.util.builder.MetricSetDescriptionBuilder;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.exceptions.CalculatorException;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.listener.IProbeListener;

/**
 * This class is the abstract super class for all calculator implementations. All specific
 * calculators have to inherit from this class.
 * 
 * Because calculators have to observe probes, they implement the IProbeListener interface. They
 * also inherit from MeasurementSource, thus, making them to observable objects on their own. For
 * instance, they can provide newly calculated measurements to recorders (recorders implement the
 * IMeasurementSourceListener interface).
 * 
 * @author Sebastian Lehrig, Steffen Becker
 */
public abstract class Calculator extends MeasurementSource implements IProbeListener {

    /** Logger of this class */
    private static final Logger LOGGER = Logger.getLogger(Calculator.class);

    /** Probe Framework context to be remembered by this calculator */
    private final ProbeFrameworkContext probeFrameworkContext;

    /**
     * Default constructor.
     * 
     * @param context
     *            Probe Framework context to be remembered by this calculator (can be returned on
     *            request).
     * @param computedMetric
     *            Metric calculated by this calculator.
     */
    protected Calculator(final ProbeFrameworkContext context, final MetricDescription computedMetric) {
        super(computedMetric);
        this.probeFrameworkContext = context;
    }

    /**
     * Calculates a measurement based on a given set of probe measurements.
     * 
     * @param probeMeasurements
     *            Probe measurements conforming to the registered probes of this calculator.
     * @return Measurement that conforms to the static declaration of the metric of this class of
     *         calculators.
     * @throws CalculatorException
     *             In case something during the execution of the calculator went wrong.
     */
    protected abstract Measurement calculate(List<ProbeMeasurement> probeMeasurements) throws CalculatorException;

    /**
     * Allows to detach any registered probes for cleaning up, e.g., after a simulation.
     * 
     * TODO Isn't that method only useful for NAry calculators, thus, shout be moved down? [Lehrig]
     */
    protected abstract void detachProbes();

    /**
     * Cleans the memory of this calculator for the given request context.
     * 
     * @param requestContext
     *            Execution context of a request.
     */
    public abstract void releaseMemory(RequestContext requestContext);

    /**
     * Getter for the Probe Framework Context member variable.
     * 
     * @return The Probe Framework Context.
     */
    protected ProbeFrameworkContext getProbeFrameworkContext() {
        return probeFrameworkContext;
    }

    /**
     * This method informs calculators before they are unregistered from probes. This information
     * gives them the change to also inform and remover their own observers.
     */
    public void preUnregister() {
        for (final IMeasurementSourceListener l : this.getMeasurementSourceListeners()) {
            l.preUnregister();
            removeObserver(l);
        }
    }

    /**
     * Triggers the calculation of a measurement based on a given set of probe measurements. Also
     * informs all registered observers about this new measurement.
     * 
     * @param probeMeasurements
     *            Probe measurements conforming to the registered probes of this calculator.
     */
    protected void fireCalculated(final List<ProbeMeasurement> probeMeasurements) {
        try {
            final Measurement calculatedMeasures = calculate(probeMeasurements);
            notifyMeasurementSourceListener(calculatedMeasures);
        } catch (final CalculatorException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method to dynamically create the metric provided by this calculator. This method is
     * useful whenever metrics cannot statically be determined. For example, a metric
     * "Response Time of Operation A" is model-depended (Operation A occurs in this model).
     * Therefore, such a metric has to be created dynamically.
     * 
     * @param metricName
     *            Name of the new metric set description.
     * @param metricDescription
     *            Textual description of the new metric.
     * @param metricDescriptions
     *            Subsumed metrics.
     * @return New, dynamically created metric.
     */
    protected static MetricDescription createMetricSetDescription(final String metricName,
            final String metricDescription, final List<BaseMetricDescription> metricDescriptions) {
        final MetricSetDescription result = MetricSetDescriptionBuilder.newMetricSetDescriptionBuilder()
                .name(metricName).textualDescription(metricDescription).subsumedMetrics(POINT_IN_TIME_METRIC)
                .subsumedMetrics(metricDescriptions).build();

        return result;
    }
}
