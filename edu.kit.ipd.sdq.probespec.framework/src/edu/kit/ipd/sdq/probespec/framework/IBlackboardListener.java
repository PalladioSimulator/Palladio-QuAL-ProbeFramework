package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.Probe;

// TODO rename to IMeasurementSink?
public interface IBlackboardListener<T> {
    
    public void measurementArrived(T measurement, Probe<T> probe);
    
    public Class<T> getGenericType();
    
}
