package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.Probe;

public interface IBlackboard {
    
    public <O> void addMeasurementListener(IBlackboardListener<O> l, Probe<O> probe);
    
    public <O> void addMeasurementListener(IBlackboardListener<O> l);
    
    public <O> IBlackboardRegion<O> getRegion(Class<O> clazz);
    
}
