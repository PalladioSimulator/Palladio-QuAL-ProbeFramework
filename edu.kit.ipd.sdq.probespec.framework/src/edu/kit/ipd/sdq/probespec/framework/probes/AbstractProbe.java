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

    private boolean persistent = true;

    private List<ProbeStateListener> listeners;

    public AbstractProbe(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public AbstractProbe(String name, boolean persistent) {
        this(UUID.randomUUID().toString(), name, persistent);
        System.out.println("Creaded id " + id + " for probe " + name);
    }

    public AbstractProbe(String id, String name) {
        this(id, name, false);
    }

    public AbstractProbe(String id, String name, boolean persistent) {
        this.id = id;
        this.name = name;
        this.metadata = new HashMapMetadata();
        this.listeners = new ArrayList<ProbeStateListener>();
        this.persistent = persistent;
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
    public void setName(String name) {
        this.name = name;

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
    public void setPersistent(boolean persistent) {
        boolean persistentOld = this.persistent;
        System.out.println("Probe " + id + "(" + name + ") set persistent: " + persistent + " (was " + persistentOld
                + ")");

        // notify listeners
        if (persistentOld != persistent) {
            for (ProbeStateListener l : listeners) {
                l.persistenceChanged(persistent);
            }
        }
        this.persistent = persistent;
    }

    @Override
    public boolean isPersistent() {
        System.out.println("Probe " + id + " is persistent: " + persistent);
        return persistent;
    }

    @Override
    public void setActive(boolean active) {
        boolean activeOld = this.active;
        this.active = active;

        // notify listeners
        if (activeOld != active) {
            for (ProbeStateListener l : listeners) {
                l.activationChanged(active);
            }
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

}
