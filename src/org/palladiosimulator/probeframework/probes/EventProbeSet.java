package org.palladiosimulator.probeframework.probes;

import java.util.LinkedList;
import java.util.List;

import javax.measure.quantity.Quantity;

import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementTupple;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.listener.IProbeListener;

public class EventProbeSet extends EventProbe<EventProbe<?>> implements IProbeListener {

    private final List<Probe> subsumedProbes;

    public <EventSourceType, V, Q extends Quantity> EventProbeSet(final EventProbe<?> eventProbe, final List<Probe> subsumedProbes, final String metricName) {
        super(eventProbe, ProbeSetHelper.getMetricSetDescription(getAllProbesList(eventProbe, subsumedProbes), metricName));
        for (final Probe probe : subsumedProbes) {
            if (!(probe instanceof TriggeredProbe)) {
                throw new IllegalArgumentException("Other probes besides the event probe have to be triggered");
            }
        }
        this.subsumedProbes = subsumedProbes;
    }

    @Override
    public void newProbeMeasurementAvailable(final ProbeMeasurement measurement) {
        final List<Measurement> measurements = new LinkedList<Measurement>();

        measurements.add(measurement.getMeasurement());
        for (final Probe childProbe : subsumedProbes) {
            measurements.add(((TriggeredProbe)childProbe).doMeasure(RequestContext.EMPTY_REQUEST_CONTEXT).getMeasurement());
        }

        final MeasurementTupple result = new MeasurementTupple(measurements, (MetricSetDescription) getMetricDesciption());
        notifyMeasurementSourceListener(new ProbeMeasurement(result, this, RequestContext.EMPTY_REQUEST_CONTEXT, null));
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

}
