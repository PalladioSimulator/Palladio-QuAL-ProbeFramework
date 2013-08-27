package edu.kit.ipd.sdq.probespec.framework.blackboard.listener;

import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface BlackboardListener<V, T> {

    public void measurementArrived(Measurement<V, T> measurement, Probe<V> probe, MeasurementContext... contexts);

    public Class<V> getGenericType();
    
}