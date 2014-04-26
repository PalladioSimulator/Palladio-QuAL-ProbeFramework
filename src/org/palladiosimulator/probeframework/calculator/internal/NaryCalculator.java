package org.palladiosimulator.probeframework.calculator.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.palladiosimulator.measurementspec.MeasurementTupple;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * This abstract class represents a binary calculator. A binary calculator expects two probe sets,
 * which are represented by their particular ID. The first probe set is denoted start probe set, the
 * latter is denoted end probe set.
 * <p>
 * As soon as a sample arrives that originates from the end probe set, the binary calculator does
 * its calculation by invoking the template method
 * {@link #calculate(MeasurementTupple, MeasurementTupple)}. When a sample originating from the start
 * probe set arrives, the calculator does nothing.
 * 
 * @author Philipp Merkle, Sebastian Lehrig, Steffen Becker
 * @see Calculator
 * 
 */
public abstract class NaryCalculator extends Calculator {

    protected final List<Probe> probes;
    private final Map<RequestContext, List<ProbeMeasurement>> arrivedMeasurementMemory = new HashMap<RequestContext, List<ProbeMeasurement>>();

    public NaryCalculator(final ProbeFrameworkContext ctx, final MetricDescription metricDescription,
            final List<Probe> childProbes) {
        super(ctx, metricDescription);
        probes = Collections.unmodifiableList(new ArrayList<Probe>(childProbes));
        for (final Probe probe : childProbes) {
            probe.addObserver(this);
        }
    }

    /**
     * <p>
     * This method is called by the {@link #update(java.util.Observable, Object)} method as soon as
     * a new ProbeSetSample arrives at the blackboard (Observer Pattern). If
     * <code>probeSetSample</code> is an end probe set sample, the method tries to get the
     * corresponding start ProbeSetSample and invokes the
     * {@link #calculate(MeasurementTupple, MeasurementTupple)} method. If <code>probeSetSample</code> is
     * an start ProbeSetSample, this method will do nothing.
     * </p>
     * 
     * <p>
     * After the calculation the result is passed to the pipes-and-filters chain.
     * </p>
     * 
     * <p>
     * Here, we make the assumption that the start ProbeSetSample always arrives before the end
     * ProbeSetSample. Without this assumption all binary calculators would also have to try to get
     * the end ProbeSetSample when the start ProbeSetSample arrives. Probably this would have a
     * negative effect on the performance.
     * </p>
     * 
     * TODO Move calculate into the calculator class using a List of ProbeSetSample. Then, enable
     * checking of list size and consistency between metrics and measures.
     * 
     * @param probeSetSample
     *            the last ProbeSetSample which was added to the SampleBlackboard and so triggered
     *            this Calculator.
     * @see org.palladiosimulator.probeframework.calculator.Calculator#execute
     *      (org.palladiosimulator.measurementspec.MeasurementTupple)
     */
    @Override
    public void newProbeMeasurementAvailable(final ProbeMeasurement probeMeasurement) {
        if (isMeasurementFromFirstProbe(probeMeasurement)) {
            if (arrivedMeasurementMemory.containsKey(probeMeasurement.getSourceAndContext().getRequestContext())) {
                throw new IllegalStateException(
                        "First measurement to the same context arrived while previous series of the same context did not complete.");
            }
            arrivedMeasurementMemory.put(probeMeasurement.getSourceAndContext().getRequestContext(), new LinkedList<ProbeMeasurement>());
        }
        final List<ProbeMeasurement> measurementMemory = arrivedMeasurementMemory.get(probeMeasurement.getSourceAndContext().getRequestContext());
        measurementMemory.add(probeMeasurement);
        if (isMeasurementFromLastProbe(probeMeasurement)) {
            fireCalculated(measurementMemory);
            arrivedMeasurementMemory.remove(probeMeasurement.getSourceAndContext().getRequestContext());
        }
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probeframework.calculator.Calculator#detachProbes()
     */
    @Override
    public void detachProbes() {
        for (final Probe probe : probes) {
            probe.removeObserver(this);
        }
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probeframework.calculator.Calculator#releaseMemory(org.palladiosimulator.measurementspec.requestcontext.RequestContext)
     */
    @Override
    public void releaseMemory(final RequestContext requestContext) {
        arrivedMeasurementMemory.remove(requestContext);
    }

    private boolean isMeasurementFromLastProbe(final ProbeMeasurement probeMeasurement) {
        return (probeMeasurement.getSourceAndContext().getMeasurementSource() == probes.get(probes.size() - 1));
    }

    private boolean isMeasurementFromFirstProbe(final ProbeMeasurement probeMeasurement) {
        return (probeMeasurement.getSourceAndContext().getMeasurementSource() == probes.get(0));
    }
}
