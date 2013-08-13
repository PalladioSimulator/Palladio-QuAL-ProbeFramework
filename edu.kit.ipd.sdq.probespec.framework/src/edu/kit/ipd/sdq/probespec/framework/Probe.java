package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementMetadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

public interface Probe<V> {

    String getId();
    
    String getName();
    
    Class<V> getGenericClass();

    void addMeasurement(V value);
    
    void addMeasurement(V value, IMeasurementMetadata metadata);

    void addMeasurement(V value, IMeasurementContext... contexts);

    void addMeasurement(V value, IMeasurementMetadata metadata, IMeasurementContext... contexts);
    
    /**
     * This method is not intended to be invoked by clients.
     */
    void setBlackboard(IBlackboard<?> blackboard);
    
}
