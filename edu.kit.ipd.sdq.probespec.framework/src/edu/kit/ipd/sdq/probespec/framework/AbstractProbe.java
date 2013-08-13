package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementMetadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

public abstract class AbstractProbe<V> implements Probe<V> {

    private IBlackboard<?> blackboard;

    private String id;

    private String name;

    public AbstractProbe(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addMeasurement(V value) {
        getBlackboard().addMeasurement(value, this);
    }

    @Override
    public void addMeasurement(V value, IMeasurementMetadata metadata) {

        getBlackboard().addMeasurement(value, this, metadata);
    }

    @Override
    public void addMeasurement(V value, IMeasurementContext... contexts) {
        getBlackboard().addMeasurement(value, this, contexts);
    }

    @Override
    public void addMeasurement(V value, IMeasurementMetadata metadata, IMeasurementContext... contexts) {
        getBlackboard().addMeasurement(value, this, metadata, contexts);
    }

    @Override
    public final void setBlackboard(IBlackboard<?> blackboard) {
        this.blackboard = blackboard;
    }

    private IBlackboard<?> getBlackboard() {
        if (blackboard == null) {
            throw new IllegalStateException("Probes must be registered with a ProbeManager before use.");
        } else {
            return blackboard;
        }
    }

}
