package org.palladiosimulator.probeframework.probes;

import javax.measure.quantity.Quantity;

import org.palladiosimulator.metricspec.BaseMetricDescription;

public abstract class BasicObjectStateProbe<StateObjectType, V, Q extends Quantity> extends BasicTriggeredProbe<V, Q> {

    private final StateObjectType observedStateObject;

    public BasicObjectStateProbe(final StateObjectType stateObject, final BaseMetricDescription metric) {
        super(metric);
        if (stateObject == null) {
            throw new IllegalArgumentException("State object must not be null");
        }
        this.observedStateObject = stateObject;
    }

    protected StateObjectType getStateObject() {
        return this.observedStateObject;
    }
}
