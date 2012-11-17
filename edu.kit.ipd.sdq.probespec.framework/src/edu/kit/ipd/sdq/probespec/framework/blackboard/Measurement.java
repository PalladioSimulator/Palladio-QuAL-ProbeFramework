package edu.kit.ipd.sdq.probespec.framework.blackboard;

public final class Measurement<T, U> {

    // TODO what about double-typed simulation times, e.g. t=20.51 !?
    private final U timestamp;
    
    private final T value;
    
    public Measurement(U timestamp, T value) {
        this.timestamp = timestamp;
        this.value = value;
    }
    
    public T getValue() {
        return value;
    }

    public U getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Measurement [timestamp=" + timestamp + ", value=" + value + "]";
    }
    
}
