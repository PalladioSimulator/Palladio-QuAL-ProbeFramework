package org.palladiosimulator.probespec.framework.calculator.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.measurementspec.IMeasurementSource;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementSet;
import org.palladiosimulator.probespec.framework.ProbeSpecContext;
import org.palladiosimulator.probespec.framework.calculator.Calculator;
import org.palladiosimulator.probespec.framework.probes.Probe;
import org.palladiosimulator.measurementspec.requestcontext.RequestContext;

/**
 * This abstract class represents a binary calculator. A binary calculator expects two probe sets,
 * which are represented by their particular ID. The first probe set is denoted start probe set, the
 * latter is denoted end probe set.
 * <p>
 * As soon as a sample arrives that originates from the end probe set, the binary calculator does
 * its calculation by invoking the template method
 * {@link #calculate(MeasurementSet, MeasurementSet)}. When a sample originating from the start
 * probe set arrives, the calculator does nothing.
 * 
 * @author Philipp Merkle, Sebastian Lehrig, Steffen Becker
 * @see Calculator
 * 
 */
public abstract class NaryCalculator extends Calculator {

    protected final List<Probe> probes;
    private final Map<RequestContext, List<Measurement>> arrivedMeasurementMemory = new HashMap<RequestContext, List<Measurement>>();

    public NaryCalculator(final ProbeSpecContext ctx, final MetricDescription metricDescription,
            final List<Probe> childProbes) {
        super(ctx, metricDescription);
        probes = Collections.unmodifiableList(new ArrayList<Probe>(childProbes));
        for (final IMeasurementSource probe : childProbes) {
            probe.addObserver(this);
        }
    }

    /**
     * <p>
     * This method is called by the {@link #update(java.util.Observable, Object)} method as soon as
     * a new ProbeSetSample arrives at the blackboard (Observer Pattern). If
     * <code>probeSetSample</code> is an end probe set sample, the method tries to get the
     * corresponding start ProbeSetSample and invokes the
     * {@link #calculate(MeasurementSet, MeasurementSet)} method. If <code>probeSetSample</code> is
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
     * @see org.palladiosimulator.probespec.framework.calculator.Calculator#execute
     *      (org.palladiosimulator.measurementspec.MeasurementSet)
     */
    @Override
    public void newMeasurementAvailable(final Measurement probeMeasurement) {
        if (isMeasurementFromFirstProbe(probeMeasurement)) {
            if (arrivedMeasurementMemory.containsKey(probeMeasurement.getRequestContext())) {
                throw new IllegalStateException(
                        "First measurement to the same context arrived while previous series of the same context did not complete.");
            }
            arrivedMeasurementMemory.put(probeMeasurement.getRequestContext(), new LinkedList<Measurement>());
        }
        final List<Measurement> measurementMemory = arrivedMeasurementMemory.get(probeMeasurement.getRequestContext());
        measurementMemory.add(probeMeasurement);
        if (isMeasurementFromLastProbe(probeMeasurement)) {
            fireCalculated(measurementMemory);
            arrivedMeasurementMemory.remove(probeMeasurement.getRequestContext());
        }
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probespec.framework.calculator.Calculator#detachProbes()
     */
    @Override
    public void detachProbes() {
        for (final IMeasurementSource probe : probes) {
            probe.removeObserver(this);
        }
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probespec.framework.calculator.Calculator#releaseMemory(org.palladiosimulator.measurementspec.requestcontext.RequestContext)
     */
    @Override
    public void releaseMemory(final RequestContext requestContext) {
        arrivedMeasurementMemory.remove(requestContext);
    }

    private boolean isMeasurementFromLastProbe(final Measurement probeMeasurement) {
        return (probeMeasurement.getMeasurementSource() == probes.get(probes.size() - 1));
    }

    private boolean isMeasurementFromFirstProbe(final Measurement probeMeasurement) {
        return (probeMeasurement.getMeasurementSource() == probes.get(0));
    }
}
