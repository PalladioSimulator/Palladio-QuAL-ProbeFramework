package de.uka.ipd.sdq.probespec.framework.probes;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;

public abstract class EventProbe<EventSourceType> extends Probe {

    protected final EventSourceType eventSource;

    public EventProbe(final EventSourceType eventSource, final MetricDescription metricDesciption) {
        super(metricDesciption);
        this.eventSource = eventSource;
        registerListener();
    }

    protected abstract void registerListener();
}
