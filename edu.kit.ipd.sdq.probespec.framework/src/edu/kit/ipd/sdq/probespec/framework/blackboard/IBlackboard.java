package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.framework.IMetadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface IBlackboard<T> {

    public <V> void addMeasurement(V value, Probe<V> probe);
    
    public <V> void addMeasurement(V value, Probe<V> probe, IMetadata metadata);

    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts);
    
    public <V> void addMeasurement(V value, Probe<V> probe, IMetadata metadata, IMeasurementContext... contexts);
    
    public <V> IBlackboardReader<V, T> getReader(Probe<V> probe);
    
    public <V> IBlackboardWriter<V> getWriter(Probe<V> probe);
    
    public void deleteMeasurements(IMeasurementContext context);
    
    public <V> void deleteMeasurement(Probe<V> probe, IMeasurementContext... contexts);
    
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l, Probe<V> probe);
    
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l);
    
    public void removeMeasurementListener(IBlackboardListener<?, T> l);
    
    public void removeMeasurementListener(IBlackboardListener<?, T> l, Probe<?> probe);

}
