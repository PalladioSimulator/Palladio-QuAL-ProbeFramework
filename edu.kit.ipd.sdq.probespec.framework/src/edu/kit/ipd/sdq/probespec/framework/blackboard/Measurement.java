package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.framework.probes.Probe;


public final class Measurement<V, T> {

    private final V value;
    
    private final T timestamp;
    
    private final Probe<V> source;

    public Measurement(V value, T timestamp, Probe<V> source) {
        this.timestamp = timestamp;
        this.value = value;
        this.source = source;
    }

    public V getValue() {
        return value;
    }

    public T getTimestamp() {
        return timestamp;
    }

    public Probe<V> getSource() {
        return source;
    }
    
    @Override
    public String toString() {
        return "Measurement [value=" + value + ", timestamp=" + timestamp + "]";
    }

}
