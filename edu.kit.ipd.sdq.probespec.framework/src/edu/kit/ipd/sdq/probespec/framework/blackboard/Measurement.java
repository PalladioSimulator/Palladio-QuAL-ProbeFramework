package edu.kit.ipd.sdq.probespec.framework.blackboard;

public final class Measurement<T> {

    // TODO what about double-typed simulation times, e.g. t=20.51 !?
    private final long timestamp;
    
    private final T value;
    
    public Measurement(long timestamp, T value) {
        this.timestamp = timestamp;
        this.value = value;
    }
    
    public T getValue() {
        return value;
    }
    
}
