package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.Probe;

public interface IBlackboardRegion<T> extends IMeasurementManagement<T> {
    
    public void addBlackboardListener(IBlackboardListener<T> l, Probe<T> probe);
    
    public void addBlackboardListener(IBlackboardListener<T> l);
    
    public void notifyBlackboardListeners(T measurement, Probe<T> probe);

}
