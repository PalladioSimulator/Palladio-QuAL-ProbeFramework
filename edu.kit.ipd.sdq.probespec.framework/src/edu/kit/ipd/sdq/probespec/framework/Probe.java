package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.framework.blackboard.Blackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.probes.ProbeStateListener;

public interface Probe<V> {

    String getId();

    String getName();

    Class<V> getGenericClass();

    Metadata getMetadata();

    /**
     * Makes this probe transient or persistent (i.e., non-transient). For transient probes,
     * measurements shall not be persisted while listeners shall still be notified. Transient probes
     * are most commonly used as input to calculators while the calculated output is non-transient,
     * i.e. persistent.
     */
    void setTransient(boolean _transient);

    /**
     * Returns true if this is a transient probe.
     */
    boolean isTransient();

    void setActive(boolean active);

    boolean isActive();

    void addMeasurement(V value);

    void addMeasurement(V value, Metadata metadata);

    void addMeasurement(V value, MeasurementContext... contexts);

    void addMeasurement(V value, Metadata metadata, MeasurementContext... contexts);
    
    void addProbeStateListener(ProbeStateListener listener);

    /**
     * This method is not intended to be invoked by clients.
     */
    void setBlackboard(Blackboard<?> blackboard);

    <T> void addMeasurementListener(MeasurementListener<V, T> listener);
    
    <T> void removeMeasurementListener(MeasurementListener<?, T> listener);

}
