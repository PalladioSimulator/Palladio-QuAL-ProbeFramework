package edu.kit.ipd.sdq.probespec.framework.blackboard.listener;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;

public interface IBlackboardListener<T> {
    
    public void measurementArrived(IBlackboard blackboard, T measurement, Probe<T> probe, IMeasurementContext... contexts);
    
    public Class<T> getGenericType();
    
}
