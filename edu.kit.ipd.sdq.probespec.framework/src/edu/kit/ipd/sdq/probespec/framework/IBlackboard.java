package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.Probe;

public interface IBlackboard  {

    public <O> void addMeasurement(O measurement, Probe<O> probe);
    
    public <O> void addBlackboardListener(IBlackboardListener<O> l, Probe<O> probe);
    
    public <O> void addBlackboardListener(IBlackboardListener<O> l);
    
}
