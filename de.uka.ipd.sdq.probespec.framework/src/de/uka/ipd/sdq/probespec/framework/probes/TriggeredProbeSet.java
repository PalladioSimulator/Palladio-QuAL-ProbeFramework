package de.uka.ipd.sdq.probespec.framework.probes;

import java.util.LinkedList;
import java.util.List;

import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;

import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

public class TriggeredProbeSet extends TriggeredProbe {

    private final List<Probe> subsumedProbes;

    public TriggeredProbeSet(final List<Probe> subsumedProbes, final String metricName) {
        super(ProbeSetHelper.getMetricSetDescription(subsumedProbes, metricName));
        for (final Probe probe : subsumedProbes) {
            if (!(probe instanceof TriggeredProbe)) {
                throw new IllegalArgumentException("In a passive probe set, all probes must be passive");
            }
        }
        this.subsumedProbes = subsumedProbes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uka.ipd.sdq.probespec.framework.probes.Probe#takeMeasuremnt()
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
