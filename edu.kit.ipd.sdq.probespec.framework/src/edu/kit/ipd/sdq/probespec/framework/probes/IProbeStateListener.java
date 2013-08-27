package edu.kit.ipd.sdq.probespec.framework.probes;

public interface IProbeStateListener {

    void isActive(boolean active);
    
    void isTransient(boolean _transient);
    
}
