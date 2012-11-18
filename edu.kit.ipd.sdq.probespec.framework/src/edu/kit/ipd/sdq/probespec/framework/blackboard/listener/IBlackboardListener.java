package edu.kit.ipd.sdq.probespec.framework.blackboard.listener;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;

public interface IBlackboardListener<V, T> {

    public void measurementArrived(Measurement<V, T> measurement, Probe<V> probe, IMeasurementContext... contexts);

    public Class<V> getGenericType();
    
}