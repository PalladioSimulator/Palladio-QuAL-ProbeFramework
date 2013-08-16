package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public interface Probe<V> {

    String getId();

    String getName();

    Class<V> getGenericClass();

    IMetadata getMetadata();

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

    void addMeasurement(V value, IMetadata metadata);

    void addMeasurement(V value, IMeasurementContext... contexts);

    void addMeasurement(V value, IMetadata metadata, IMeasurementContext... contexts);
    
    void addProbeStateListener(IProbeStateListener listener);

    /**
     * This method is not intended to be invoked by clients.
     */
    void setBlackboard(IBlackboard<?> blackboard);

    <T> void addMeasurementListener(IBlackboardListener<V, T> listener);
    
    <T> void removeMeasurementListener(IBlackboardListener<?, T> listener);

}
