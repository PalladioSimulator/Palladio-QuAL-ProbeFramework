package edu.kit.ipd.sdq.probespec.framework.blackboard;

public final class Measurement<V, T> {

    private final V value;
    
    private final T timestamp;

    public Measurement(V value, T timestamp) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public T getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Measurement [value=" + value + ", timestamp=" + timestamp + "]";
    }

}
