package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.Probe;

public interface IBlackboardRegion<T> extends IMeasurementManager<T> {
    
    public void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe);
    
    public void addMeasurementListener(IBlackboardListener<T> l);
    
    public void notifyMeasurementListeners(T measurement, Probe<T> probe);

}
