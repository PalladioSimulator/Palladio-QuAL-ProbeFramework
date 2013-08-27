package edu.kit.ipd.sdq.probespec.framework.probes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.kit.ipd.sdq.probespec.framework.MeasurementListener;
import edu.kit.ipd.sdq.probespec.framework.Metadata;
import edu.kit.ipd.sdq.probespec.framework.HashMapMetadata;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Blackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;

public abstract class AbstractProbe<V> implements Probe<V> {

    private Blackboard<?> blackboard;

    private String id;

    private String name;

    private Metadata metadata;

    private boolean active = true;

    private boolean _transient = false; // "transient" is a keyword, which is the reason for the
                                        // underscore

    private List<ProbeStateListener> listeners;

    public AbstractProbe(String name) {
        this(UUID.randomUUID().toString(), name);
    }
    
    public AbstractProbe(String name, boolean _transient) {
        this(UUID.randomUUID().toString(), name, _transient);
    }
    
    public AbstractProbe(String id, String name) {
        this(id, name, false);
    }
    
    public AbstractProbe(String id, String name, boolean _transient) {
        this.id = id;
        this.name = name;
        this.metadata = new HashMapMetadata();
        this.listeners = new ArrayList<ProbeStateListener>();
        this._transient = _transient;
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
    public Metadata getMetadata() {
        return metadata;
    }

    @Override
    public void addMeasurement(V value) {
        getBlackboard().addMeasurement(value, this);
    }

    @Override
    public void addMeasurement(V value, Metadata metadata) {
        getBlackboard().addMeasurement(value, this, metadata);
    }

    @Override
    public void addMeasurement(V value, MeasurementContext... contexts) {
        getBlackboard().addMeasurement(value, this, contexts);
    }

    @Override
    public void addMeasurement(V value, Metadata metadata, MeasurementContext... contexts) {
        getBlackboard().addMeasurement(value, this, metadata, contexts);
    }

    @Override
    public final void setBlackboard(Blackboard<?> blackboard) {
        this.blackboard = blackboard;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> void addMeasurementListener(MeasurementListener<V, T> listener) {
        ((Blackboard<T>) this.blackboard).addMeasurementListener(listener, this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> void removeMeasurementListener(MeasurementListener<?, T> listener) {
        ((Blackboard<T>) this.blackboard).removeMeasurementListener(listener, this);
    }

    @Override
    public void addProbeStateListener(ProbeStateListener listener) {
        listeners.add(listener);
    }

    private Blackboard<?> getBlackboard() {
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
            for (ProbeStateListener l : listeners) {
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
            for (ProbeStateListener l : listeners) {
                l.isActive(active);
            }
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

}
