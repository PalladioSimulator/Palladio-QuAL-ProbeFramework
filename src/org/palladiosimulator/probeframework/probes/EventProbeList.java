package org.palladiosimulator.probeframework.probes;

import java.util.LinkedList;
import java.util.List;

import javax.measure.quantity.Quantity;

import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementTuple;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.listener.IProbeListener;

/**
 * Event probe lists group a list of subsumed, triggered probes that are triggered as soon as an
 * additional, dedicated event probe emits an event. Event probe lists can register themselves to
 * this event probe because this class implements the <code>IProbeListener</code> interface. Once a
 * new measurement arrives (via the call-back method <code>newProbeMeasurementAvailable</code>),
 * event probe lists create a measurement tuple by creating a list with the new measurement plus the
 * measurements from subsumed probes. These measurements are received by invoking
 * <code>doMeasure</code> on each subsumed probe (that is possible since only triggered probes are
 * subsumed). Finally, registered listeners (e.g., calculators) are informed about the newly
 * available measurement tuple.
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public class EventProbeList extends EventProbe<EventProbe<?>> implements IProbeListener {

    /** List of subsumed probes. */
    private final List<Probe> subsumedProbes;

    /**
     * Default constructor. Expects one event probe as well as the list of subsumed, triggered
     * probes.
     * 
     * @param eventProbe
     *            The event probe.
     * @param subsumedProbes
     *            The list of subsumed probes.
     * @param metricName
     *            A textual metric name, used to dynamically create a metric description as needed
     *            by the superclass.
     * @param <EventSourceType>
     *            The type of the event source .
     * @param <V>
     *            The value type of the basic measure.
     * @param <Q>
     *            The quantity type of the basic measure.
     * @throws IllegalArgumentException
     *             If a subsumed probe is not a triggered probe.
     */
    public <EventSourceType, V, Q extends Quantity> EventProbeList(final EventProbe<?> eventProbe,
            final List<Probe> subsumedProbes, final String metricName) {
        super(eventProbe, ProbeListHelper.getMetricSetDescription(getAllProbesList(eventProbe, subsumedProbes),
                metricName));
        for (final Probe probe : subsumedProbes) {
            if (!(probe instanceof TriggeredProbe)) {
                throw new IllegalArgumentException("Other probes besides the event probe have to be triggered");
            }
        }
        this.subsumedProbes = subsumedProbes;
    }

    /**
     * Helper method to merge the given first probe and the given list of probes to a new list.
     * 
     * @param firstProbe
     *            The first probe of the new list.
     * @param tail
     *            The remaining probes of the list.
     * @return The merged list.
     */
    private static List<Probe> getAllProbesList(final Probe firstProbe, final List<Probe> tail) {
        final List<Probe> result = new LinkedList<Probe>();
        result.add(firstProbe);
        result.addAll(tail);

        return result;
    }

    /**
     * Receives a probe measurement from the event probe (event source). Calculates its measurement
     * by additionally triggering subsumed probes. Finally, informs all its observers about the new
     * measurement.
     * 
     * @param measurement
     *            The probe measurement.
     */
    @Override
    public void newProbeMeasurementAvailable(final ProbeMeasurement measurement) {
        final List<Measurement> measurements = new LinkedList<Measurement>();

        measurements.add(measurement.getMeasurement());
        for (final Probe childProbe : subsumedProbes) {
            measurements.add(((TriggeredProbe) childProbe).doMeasure(RequestContext.EMPTY_REQUEST_CONTEXT)
                    .getMeasurement());
        }

        final MeasurementTuple result = new MeasurementTuple(measurements,
                (MetricSetDescription) getMetricDesciption());
        notifyMeasurementSourceListener(new ProbeMeasurement(result, this, RequestContext.EMPTY_REQUEST_CONTEXT, null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void registerListener() {
        this.eventSource.addObserver(this);
    }

}
