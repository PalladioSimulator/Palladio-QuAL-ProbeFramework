package org.palladiosimulator.probeframework.probes;

import java.util.LinkedList;
import java.util.List;

import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.measureprovider.IMeasureProvider;
import org.palladiosimulator.measurementframework.measureprovider.MeasurementListMeasureProvider;
import org.palladiosimulator.metricspec.MetricDescription;
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
    private final List<TriggeredProbe> subsumedProbes;

    /**
     * Default constructor. Expects the list of subsumed, triggered probes.
     *
     * @param subsumedProbes
     *            The list of subsumed probes.
     * @param metricSetDescription The metric set description of the measurements this probe delivers.
     * @throws IllegalArgumentException
     *             If a subsumed probe is not a triggered probe.
     */
    public TriggeredProbeList(final MetricDescription metricSetDescription, final List<TriggeredProbe> subsumedProbes) {
        super(metricSetDescription);
        this.subsumedProbes = subsumedProbes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ProbeMeasurement doMeasure(final RequestContext measurementContext) {
        final List<MeasuringValue> childMeasurements = new LinkedList<MeasuringValue>();

        for (final TriggeredProbe childProbe : subsumedProbes) {
            final IMeasureProvider subsumedMeasureProvider = childProbe.doMeasure(measurementContext)
                    .getMeasureProvider();

            if (!(subsumedMeasureProvider instanceof MeasuringValue)) {
                throw new IllegalArgumentException("Subsumed measure providers have to be measurements");
            }

            // TODO Actually, we should recursively resolve subsumed measurements here because the
            // subsumed measurement could be a TupleMeasurement. [Lehrig]
            childMeasurements.add((MeasuringValue) subsumedMeasureProvider);
        }

        final IMeasureProvider measureProvider = new MeasurementListMeasureProvider(childMeasurements);
        return new ProbeMeasurement(measureProvider, this, measurementContext);
    }

}
