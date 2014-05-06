package org.palladiosimulator.probeframework.calculator.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * <p>
 * This abstract class represents an n-ary calculator. An n-ary calculator expects n probes to be
 * measured before it can do its calculation. For example, for response time calculation, two probe
 * measurements are needed (one for start time and one for end time of an operation call). For this
 * measurement series, it maintains a memory to store measurements of the n observed probes. As soon
 * as the n-th sample arrives, the n-ary calculator starts its calculation by invoking the template
 * method {@link #calculate(List<ProbeMeasurement> measurementMemory)}.
 * </p>
 * 
 * <p>
 * When a sample originating from the first probe arrives, an exception is thrown because the first
 * series of measurements has to be finished first.
 * </p>
 * 
 * @author Sebastian Lehrig, Steffen Becker
 * @see Calculator
 * 
 */
public abstract class NaryCalculator extends Calculator {

    /** List of n probes **/
    protected final List<Probe> probes;

    /** Maintained memory of probe measurements */
    private final Map<RequestContext, List<ProbeMeasurement>> arrivedMeasurementMemory;

    /**
     * Default constructor. Creates the observed list of n probes and initializes the measurement
     * memory.
     * 
     * @param context
     *            ProbeFrameworkContext as needed by the superclass.
     * @param metricDescription
     *            MetricDescription as needed by the superclass.
     * @param childProbes
     *            List of n probes.
     */
    protected NaryCalculator(final ProbeFrameworkContext context, final MetricDescription metricDescription,
            final List<Probe> childProbes) {
        super(context, metricDescription);

        this.arrivedMeasurementMemory = new HashMap<RequestContext, List<ProbeMeasurement>>();

        this.probes = Collections.unmodifiableList(new ArrayList<Probe>(childProbes));
        for (final Probe probe : childProbes) {
            probe.addObserver(this);
        }
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
     * @see org.palladiosimulator.probeframework.calculator.Calculator#detachProbes()
     */
    @Override
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
     * @see org.palladiosimulator.probeframework.calculator.Calculator#releaseMemory
     *      (org.palladiosimulator.measurementspec.requestcontext.RequestContext)
     */
    @Override
    public void releaseMemory(final RequestContext requestContext) {
        arrivedMeasurementMemory.remove(requestContext);
    }

    /**
     * Checks whether the given measurement comes from the n-th probe.
     * 
     * @param probeMeasurement
     *            The measurement to be investigated.
     * @return <code>true</code> if the measurement comes from the n-th probe, <code>false</code>
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
