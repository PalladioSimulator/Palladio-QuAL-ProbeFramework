package org.palladiosimulator.probeframework.probes;

import org.palladiosimulator.metricspec.MetricDescription;



/**
 * Event probes measure as soon as an event is emitted for which they are registered. Therefore,
 * they explicitly refer to an <code>eventSource</code> object. Subclasses have to realize the
 * registration to this object by implementing the template method <code>registerListener</code>.
 *
 * @author Steffen Becker, Sebastian Lehrig
 *
 * @param <EventSourceType>
 *            The type of the event source.
 */
public abstract class EventProbe<EventSourceType> extends Probe {

    /** The event source. */
    protected final EventSourceType eventSource;

    /**
     * Default constructor. Expects a suitable event source object (main characteristic of this type
     * of probe).
     *
     * @param eventSource
     *            The event source object.
     * @param metricDescription The metric of the measurements of this probe.
     */
    public EventProbe(final MetricDescription metricDescription, final EventSourceType eventSource) {
        super(metricDescription);
        this.eventSource = eventSource;
        registerListener();
    }

    /**
     * Template method for registering observers to the event source.
     */
    protected abstract void registerListener();
}
