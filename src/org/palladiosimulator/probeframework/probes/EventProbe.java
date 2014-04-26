package org.palladiosimulator.probeframework.probes;

import org.palladiosimulator.metricspec.MetricDescription;

public abstract class EventProbe<EventSourceType> extends Probe {

    protected final EventSourceType eventSource;

    public EventProbe(final EventSourceType eventSource, final MetricDescription metricDesciption) {
        super(metricDesciption);
        this.eventSource = eventSource;
        registerListener();
    }

    protected abstract void registerListener();
}
