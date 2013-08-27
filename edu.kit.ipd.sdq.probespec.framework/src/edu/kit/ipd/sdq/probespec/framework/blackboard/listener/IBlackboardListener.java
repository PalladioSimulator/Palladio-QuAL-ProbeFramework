package edu.kit.ipd.sdq.probespec.framework.blackboard.listener;

import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface IBlackboardListener<V, T> {

    public void measurementArrived(Measurement<V, T> measurement, Probe<V> probe, IMeasurementContext... contexts);

    public Class<V> getGenericType();
    
}