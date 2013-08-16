package edu.kit.ipd.sdq.probespec.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public abstract class AbstractProbe<V> implements Probe<V> {

    private IBlackboard<?> blackboard;

    private String id;

    private String name;

    private IMetadata metadata;

    private boolean active = true;

    private boolean _transient = false; // "transient" is a keyword, which is the reason for the
                                        // underscore

    private List<IProbeStateListener> listeners;

    public AbstractProbe(String id, String name) {
        this(id, name, false);
    }
    
    public AbstractProbe(String id, String name, boolean _transient) {
        this.id = id;
        this.name = name;
        this.metadata = new Metadata();
        this.listeners = new ArrayList<IProbeStateListener>();
        this._transient = _transient;
    }

    public AbstractProbe(String name) {
        this(UUID.randomUUID().toString(), name);
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
    public IMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void addMeasurement(V value) {
        getBlackboard().addMeasurement(value, this);
    }

    @Override
    public void addMeasurement(V value, IMetadata metadata) {
        getBlackboard().addMeasurement(value, this, metadata);
    }

    @Override
    public void addMeasurement(V value, IMeasurementContext... contexts) {
        getBlackboard().addMeasurement(value, this, contexts);
    }

    @Override
    public void addMeasurement(V value, IMetadata metadata, IMeasurementContext... contexts) {
        getBlackboard().addMeasurement(value, this, metadata, contexts);
    }

    @Override
    public final void setBlackboard(IBlackboard<?> blackboard) {
        this.blackboard = blackboard;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> void addMeasurementListener(IBlackboardListener<V, T> listener) {
        ((IBlackboard<T>) this.blackboard).addMeasurementListener(listener, this);
    }

    @Override
    public <T> void removeMeasurementListener(IBlackboardListener<?, T> listener) {
        ((IBlackboard<T>) this.blackboard).removeMeasurementListener(listener, this);
    }

    @Override
    public void addProbeStateListener(IProbeStateListener listener) {
        listeners.add(listener);
    }

    private IBlackboard<?> getBlackboard() {
        if (blackboard == null) {
            throw new IllegalStateException("Probes must be registered with a ProbeManager before use.");
        } else {
            return blackboard;
        }
    }

    @Override
    public void setTransient(boolean _transient) {
        boolean _transientOld = this._transient;
        this._transient = _transient;

        // notify listeners
        if (_transientOld != _transient) {
            for (IProbeStateListener l : listeners) {
                l.isTransient(_transient);
            }
        }
    }

    @Override
    public boolean isTransient() {
        return _transient;
    }

    @Override
    public void setActive(boolean active) {
        boolean activeOld = this.active;
        this.active = active;

        // notify listeners
        if (activeOld != active) {
            for (IProbeStateListener l : listeners) {
                l.isActive(active);
            }
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

}
