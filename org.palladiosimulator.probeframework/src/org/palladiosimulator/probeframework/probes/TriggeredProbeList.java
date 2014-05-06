package org.palladiosimulator.probeframework.probes;

import java.util.LinkedList;
import java.util.List;

import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementTuple;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
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
     * @param metricDesciption
     *            A metric description as needed by the superclass.
     * @throws IllegalArgumentException
     *             If a subsumed probe is not a triggered probe.
     */
    public TriggeredProbeList(final List<Probe> subsumedProbes, final MetricDescription metricDesciption) {
        super(metricDesciption);
        for (final Probe probe : subsumedProbes) {
            if (!(probe instanceof TriggeredProbe)) {
                throw new IllegalArgumentException("In a triggered probe list, all probes must be triggered probes");
            }
        }
        this.subsumedProbes = subsumedProbes;
    }

    /**
     * Convenience constructor creating a default metric description based on a given name.
     * 
     * @see #TriggeredProbeList(List, MetricDescription)
     * 
     * @param subsumedProbes
     *            The list of subsumed probes.
     * @param metricName
     *            Metric name used for a new metric description.
     */
    public TriggeredProbeList(final List<Probe> subsumedProbes, final String metricName) {
        this(subsumedProbes, ProbeListHelper.getMetricSetDescription(subsumedProbes, metricName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ProbeMeasurement doMeasure(final RequestContext measurementContext) {
        final List<Measurement> childMeasurements = new LinkedList<Measurement>();

        for (final Probe childProbe : subsumedProbes) {
            childMeasurements.add(((TriggeredProbe) childProbe).doMeasure(measurementContext).getMeasurement());
        }

        final MeasurementTuple result = new MeasurementTuple(childMeasurements,
                (MetricSetDescription) getMetricDesciption());
        return new ProbeMeasurement(result, this, measurementContext, null);
    }

}
