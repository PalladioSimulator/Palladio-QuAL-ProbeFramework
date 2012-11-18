package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;

public class ProbeMeasurementsProxy<V, T> {

    private Probe<V> probe;
    
    private IBlackboard<T> blackboard;

    public ProbeMeasurementsProxy(Probe<V> probe, IBlackboard<T> blackboard) {
        this.probe = probe;
        this.blackboard = blackboard;
    }
    
    public Measurement<V, T> getLatestMeasurement() {
        return blackboard.getLatestMeasurement(probe);
    }
    
    public Measurement<V, T> getLatestMeasurement(IMeasurementContext... contexts) {
        return blackboard.getLatestMeasurement(probe, contexts);
    }
    
    @SuppressWarnings("hiding")
    public <V> void addMeasurement(V value, Probe<V> probe) {
        blackboard.addMeasurement(value, probe);
    }

    @SuppressWarnings("hiding")
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts) {
        blackboard.addMeasurement(value, probe, contexts);
    }

    public Probe<V> getProbe() {
        return probe;
    }
    
}
