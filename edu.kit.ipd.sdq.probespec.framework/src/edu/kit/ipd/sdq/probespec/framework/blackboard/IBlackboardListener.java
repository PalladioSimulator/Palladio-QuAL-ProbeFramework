package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;

public interface IBlackboardListener<T> {
    
    public void measurementArrived(IBlackboard blackboard, T measurement, Probe<T> probe, IMeasurementContext... contexts);
    
    public Class<T> getGenericType();
    
}
