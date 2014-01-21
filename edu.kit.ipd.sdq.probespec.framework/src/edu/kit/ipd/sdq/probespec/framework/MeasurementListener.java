package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;

public interface MeasurementListener<V, T> {

    public void measurementArrived(Measurement<V, T> measurement, Probe<V> probe, MeasurementContext... contexts);

    public Class<V> getGenericType();
    
}