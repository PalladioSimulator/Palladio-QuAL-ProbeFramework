package org.palladiosimulator.probeframework.probes;

import java.util.LinkedList;
import java.util.List;

import javax.measure.quantity.Quantity;

import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;
import org.palladiosimulator.measurementspec.IMeasurementSourceListener;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementSet;
import org.palladiosimulator.measurementspec.requestcontext.RequestContext;

public class EventProbeSet extends EventProbe<EventProbe<?>> implements IMeasurementSourceListener {

    private final List<Probe> subsumedProbes;
    private final EventProbe<?> eventProbe;

    public <EventSourceType, V, Q extends Quantity> EventProbeSet(final EventProbe<?> eventProbe, final List<Probe> subsumedProbes, final String metricName) {
        super(eventProbe,ProbeSetHelper.getMetricSetDescription(getAllProbesList(eventProbe, subsumedProbes), metricName));
        for (final Probe probe : subsumedProbes) {
            if (!(probe instanceof TriggeredProbe)) {
                throw new IllegalArgumentException("Other probes besides the event probe have to be triggered");
            }
        }
        this.eventProbe = eventProbe;
        this.subsumedProbes = subsumedProbes;
    }

    @Override
    public void newMeasurementAvailable(final Measurement measurement) {
        final List<Measurement> measurements = new LinkedList<Measurement>();

        measurements.add(measurement);
        for (final Probe childProbe : subsumedProbes) {
            measurements.add(((TriggeredProbe)childProbe).doMeasure(RequestContext.EMPTY_REQUEST_CONTEXT));
        }

        final MeasurementSet result = new MeasurementSet(measurements, (MetricSetDescription) getMetricDesciption(), this);
        notifyMeasurementSourceListener(result);
    }

    private static List<Probe> getAllProbesList(final Probe firstProbe, final List<Probe> tail) {
        final List<Probe> result = new LinkedList<Probe>();
        result.add(firstProbe);
        result.addAll(tail);

        return result;
    }

    @Override
    protected void registerListener() {
        this.eventSource.addObserver(this);
    }

	@Override
	public void preUnregister() {
	}
}
