package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;

public interface IBlackboardListener<T> {
    
    public void measurementArrived(T measurement, Probe<T> probe, IBlackboard blackboard);
    
    public Class<T> getGenericType();
    
}
