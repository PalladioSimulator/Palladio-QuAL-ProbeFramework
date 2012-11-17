package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public interface IBlackboard<T> {

    public <V> void addMeasurement(V value, Probe<V> probe);

    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts);
    
    public <V> Measurement<V, T> getLatestMeasurement(Probe<V> probe);
    
    public <V> Measurement<V, T> getLatestMeasurement(Probe<V> probe, IMeasurementContext... contexts);
    
    public void deleteMeasurements(IMeasurementContext context);
    
    public <V> void deleteMeasurement(Probe<V> probe, IMeasurementContext... contexts);
    
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l, Probe<V> probe);

    public <V> void addMeasurementListener(IBlackboardListener<V, T> l);
    
    public <V> void removeMeasurementListener(IBlackboardListener<V, T> l);

}
