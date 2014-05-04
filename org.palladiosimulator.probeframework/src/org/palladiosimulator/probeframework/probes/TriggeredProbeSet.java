package org.palladiosimulator.probeframework.probes;

import java.util.LinkedList;
import java.util.List;

import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementTupple;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;

public class TriggeredProbeSet extends TriggeredProbe {

    private final List<Probe> subsumedProbes;

    public TriggeredProbeSet(final List<Probe> subsumedProbes, final MetricDescription metric) {
        super(metric);
        for (final Probe probe : subsumedProbes) {
            if (!(probe instanceof TriggeredProbe)) {
                throw new IllegalArgumentException("In a passive probe set, all probes must be passive");
            }
        }
        this.subsumedProbes = subsumedProbes;
    }

    public TriggeredProbeSet(final List<Probe> subsumedProbes, final String metricName) {
        this(subsumedProbes, ProbeSetHelper.getMetricSetDescription(subsumedProbes, metricName));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.probeframework.probes.Probe#takeMeasuremnt()
     */
    @Override
    protected ProbeMeasurement doMeasure(final RequestContext measurementContext) {
        final List<Measurement> childMeasurements = new LinkedList<Measurement>();

        for (final Probe childProbe : subsumedProbes) {
            childMeasurements.add(((TriggeredProbe) childProbe).doMeasure(measurementContext).getMeasurement());
        }

        final MeasurementTupple result = new MeasurementTupple(childMeasurements,
                (MetricSetDescription) getMetricDesciption());
        return new ProbeMeasurement(result, this, measurementContext, null);
    }

}
