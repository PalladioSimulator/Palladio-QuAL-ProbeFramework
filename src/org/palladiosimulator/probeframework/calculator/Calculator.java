package org.palladiosimulator.probeframework.calculator;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.POINT_IN_TIME_METRIC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.probeframework.probes.listener.IProbeListener;

/**
 * <p>
 * This class is the abstract super class for all calculator implementations. All specific
 * calculators have to inherit from this class.
 * </p>
 * 
 * <p>
 * Because calculators have to observe probes, they implement the IProbeListener interface. They
 * also inherit from MeasurementSource, thus, making them to observable objects on their own. For
 * instance, they can provide newly calculated measurements to recorders (recorders implement the
 * IMeasurementSourceListener interface).
 * </p>
 * 
 * <p>
 * Furthermore, calculators expects a list of probes to be measured before it can do its
 * calculation. For example, for response time calculation, two probe measurements are needed (one
 * for start time and one for end time of an operation call). For this measurement series,
 * calculators maintain a memory to store measurements of the observed probes. As soon as the last
 * sample arrives, the calculators start their calculation by invoking the template method {@link
 * #calculate(List<ProbeMeasurement> measurementMemory)}.
 * </p>
 * 
 * <p>
 * When a sample originating from the first probe arrives (after a measurement series has started
 * and before it ended with the last probe), an exception is thrown because the first series of
 * measurements has to be finished first.
 * </p>
 * 
 * @author Sebastian Lehrig, Steffen Becker
 */
public abstract class Calculator extends MeasurementSource implements IProbeListener {

    /** Logger of this class */
    private static final Logger LOGGER = Logger.getLogger(Calculator.class);

    /** Probe Framework context to be remembered by this calculator */
    private final ProbeFrameworkContext probeFrameworkContext;

    /** List of n probes **/
    protected final List<Probe> probes;

    /** Maintained memory of probe measurements */
    private final Map<RequestContext, List<ProbeMeasurement>> arrivedMeasurementMemory;

    /**
     * Default constructor. Creates the observed list of n probes and initializes the measurement
     * memory.
     * 
     * @param context
     *            Probe Framework context to be remembered by this calculator (can be returned on
     *            request).
     * @param computedMetric
     *            Metric calculated by this calculator.
     * @param childProbes
     *            List of probes.
     */
    protected Calculator(final ProbeFrameworkContext context, final MetricDescription computedMetric,
            final List<Probe> childProbes) {
        super(computedMetric);
        this.probeFrameworkContext = context;
        this.arrivedMeasurementMemory = new HashMap<RequestContext, List<ProbeMeasurement>>();

        this.probes = Collections.unmodifiableList(new ArrayList<Probe>(childProbes));
        for (final Probe probe : childProbes) {
            probe.addObserver(this);
        }
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
     */
    public void detachProbes() {
        for (final Probe probe : probes) {
            probe.removeObserver(this);
        }
    }

    /**
     * Removes the given request context from the measurement memory.
     * 
     * @param requestContext
     *            Request context to be removed.
     */
    public void releaseMemory(final RequestContext requestContext) {
        arrivedMeasurementMemory.remove(requestContext);
    }

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

    /**
     * Call-back method to be informed about new probe measurements.
     * 
     * @param probeMeasurement
     *            The last ProbeMeasurement that was received from an observed probe.
     * @see org.palladiosimulator.probeframework.probes.listener.IProbeListener#newProbeMeasurementAvailable
     */
    @Override
    public void newProbeMeasurementAvailable(final ProbeMeasurement probeMeasurement) {
        if (isMeasurementFromFirstProbe(probeMeasurement)) {
            if (arrivedMeasurementMemory.containsKey(probeMeasurement.getProbeAndContext().getRequestContext())) {
                throw new IllegalStateException("First measurement to the same context arrived while"
                        + "previous series of the same context did not complete.");
            }
            arrivedMeasurementMemory.put(probeMeasurement.getProbeAndContext().getRequestContext(),
                    new LinkedList<ProbeMeasurement>());
        }
        final List<ProbeMeasurement> measurementMemory = arrivedMeasurementMemory.get(probeMeasurement
                .getProbeAndContext().getRequestContext());
        measurementMemory.add(probeMeasurement);
        if (isMeasurementFromLastProbe(probeMeasurement)) {
            fireCalculated(measurementMemory);
            arrivedMeasurementMemory.remove(probeMeasurement.getProbeAndContext().getRequestContext());
        }
    }

    /**
     * Checks whether the given measurement comes from the last probe.
     * 
     * @param probeMeasurement
     *            The measurement to be investigated.
     * @return <code>true</code> if the measurement comes from the last probe, <code>false</code>
     *         otherwise.
     */
    private boolean isMeasurementFromLastProbe(final ProbeMeasurement probeMeasurement) {
        return (probeMeasurement.getProbeAndContext().getProbe() == probes.get(probes.size() - 1));
    }

    /**
     * Checks whether the given measurement comes from the first probe.
     * 
     * @param probeMeasurement
     *            The measurement to be investigated.
     * @return <code>true</code> if the measurement comes from the first probe, <code>false</code>
     *         otherwise.
     */
    private boolean isMeasurementFromFirstProbe(final ProbeMeasurement probeMeasurement) {
        return (probeMeasurement.getProbeAndContext().getProbe() == probes.get(0));
    }
}
