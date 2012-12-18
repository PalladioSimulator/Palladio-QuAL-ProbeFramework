package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;

public interface IBlackboard<T> {

    public <V> void addMeasurement(V value, Probe<V> probe);
    
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementMetadata metadata);

    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts);
    
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementMetadata metadata, IMeasurementContext... contexts);
    
    public <V> IBlackboardReader<V, T> getReader(Probe<V> probe);
    
//    public <V> Measurement<V, T> getLatestMeasurement(Probe<V> probe);
//    
//    public <V> Measurement<V, T> getLatestMeasurement(Probe<V> probe, IMeasurementContext... contexts);
    
    public void deleteMeasurements(IMeasurementContext context);
    
    public <V> void deleteMeasurement(Probe<V> probe, IMeasurementContext... contexts);
    
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l, Probe<V> probe);
    
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l);
    
    public <V> void removeMeasurementListener(IBlackboardListener<V, T> l);

}
