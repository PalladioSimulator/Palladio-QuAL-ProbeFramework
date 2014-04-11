package org.palladiosimulator.probespec.framework.probes;

import java.util.LinkedList;
import java.util.List;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementSet;
import org.palladiosimulator.measurementspec.requestcontext.RequestContext;

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
        this(subsumedProbes,ProbeSetHelper.getMetricSetDescription(subsumedProbes, metricName));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.probespec.framework.probes.Probe#takeMeasuremnt()
     */
    @Override
    protected MeasurementSet doMeasure(final RequestContext measurementContext) {
        final List<Measurement> childMeasurements = new LinkedList<Measurement>();

        for (final Probe childProbe : subsumedProbes) {
            childMeasurements.add(((TriggeredProbe)childProbe).doMeasure(measurementContext));
        }

        final MeasurementSet result = new MeasurementSet(childMeasurements, (MetricSetDescription) getMetricDesciption(), this);
        return result;
    }

}
