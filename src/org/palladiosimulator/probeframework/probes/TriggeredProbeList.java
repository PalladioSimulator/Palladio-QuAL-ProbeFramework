package org.palladiosimulator.probeframework.probes;

import java.util.LinkedList;
import java.util.List;

import org.palladiosimulator.measurementframework.Measurement;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;

/**
 * Triggered probe lists group a set of subsumed, triggered probes. Therefore, triggered probe lists
 * can implement <code>doMeasure</code> by invoking <code>doMeasure</code> on each of their subsumed
 * probes and by returning a measurement tuple of measurement results from these probes.
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public class TriggeredProbeList extends TriggeredProbe {

    /** List of subsumed probes. */
    private final List<Probe> subsumedProbes;

    /**
     * Default constructor. Expects the list of subsumed, triggered probes.
     * 
     * @param subsumedProbes
     *            The list of subsumed probes.
     * @throws IllegalArgumentException
     *             If a subsumed probe is not a triggered probe.
     */
    public TriggeredProbeList(final List<Probe> subsumedProbes) {
        super();
        for (final Probe probe : subsumedProbes) {
            if (!(probe instanceof TriggeredProbe)) {
                throw new IllegalArgumentException("In a triggered probe list, all probes must be triggered probes");
            }
        }
        this.subsumedProbes = subsumedProbes;
    }

    /**
     * {@inheritDoc}
     * 
     * FIXME Use is MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE is wrong
     */
    @Override
    protected ProbeMeasurement doMeasure(final RequestContext measurementContext) {
        final List<Measurement> childMeasurements = new LinkedList<Measurement>();

        for (final Probe childProbe : subsumedProbes) {
            childMeasurements.add(((TriggeredProbe) childProbe).doMeasure(measurementContext).getMeasurement());
        }

        final Measurement measurementTuple = new TupleMeasurement(childMeasurements,
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE);
        return new ProbeMeasurement(measurementTuple, this, measurementContext, null);
    }

}
