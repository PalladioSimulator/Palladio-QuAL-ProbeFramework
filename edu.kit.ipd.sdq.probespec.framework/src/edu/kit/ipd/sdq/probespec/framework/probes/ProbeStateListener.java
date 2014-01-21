package edu.kit.ipd.sdq.probespec.framework.probes;

public interface ProbeStateListener {

    void activationChanged(boolean active);
    
    void persistenceChanged(boolean persistent);
    
}
