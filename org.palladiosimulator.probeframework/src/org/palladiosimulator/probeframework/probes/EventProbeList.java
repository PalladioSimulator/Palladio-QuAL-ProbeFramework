package org.palladiosimulator.probeframework.probes;

import java.util.LinkedList;
import java.util.List;

import javax.measure.quantity.Quantity;

import org.palladiosimulator.measurementframework.Measurement;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
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
            final List<Probe> subsumedProbes) {
        super(eventProbe);
        for (final Probe probe : subsumedProbes) {
            if (!(probe instanceof TriggeredProbe)) {
                throw new IllegalArgumentException("Other probes besides the event probe have to be triggered");
            }
        }
        this.subsumedProbes = subsumedProbes;
    }

    /**
     * Receives a probe measurement from the event probe (event source). Calculates its measurement
     * by additionally triggering subsumed probes. Finally, informs all its observers about the new
     * measurement.
     * 
     * FIXME Use is MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE is wrong
     * 
     * @param measurement
     *            The probe measurement.
     */
    @Override
    public void newProbeMeasurementAvailable(final ProbeMeasurement measurement) {
        final List<Measurement> eventAndChildMeasurements = new LinkedList<Measurement>();

        eventAndChildMeasurements.add(measurement.getMeasurement());
        for (final Probe childProbe : subsumedProbes) {
            eventAndChildMeasurements.add(((TriggeredProbe) childProbe).doMeasure(RequestContext.EMPTY_REQUEST_CONTEXT)
                    .getMeasurement());
        }

        final Measurement measurementTuple = new TupleMeasurement(eventAndChildMeasurements,
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE
                );
        notifyMeasurementSourceListener(new ProbeMeasurement(measurementTuple, this,
                RequestContext.EMPTY_REQUEST_CONTEXT, null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void registerListener() {
        this.eventSource.addObserver(this);
    }

}
