package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.framework.IMetadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.BlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface Blackboard<T> {

    public <V> void addMeasurement(V value, Probe<V> probe);
    
    public <V> void addMeasurement(V value, Probe<V> probe, IMetadata metadata);

    public <V> void addMeasurement(V value, Probe<V> probe, MeasurementContext... contexts);
    
    public <V> void addMeasurement(V value, Probe<V> probe, IMetadata metadata, MeasurementContext... contexts);
    
    public <V> BlackboardReader<V, T> getReader(Probe<V> probe);
    
    public <V> BlackboardWriter<V> getWriter(Probe<V> probe);
    
    public void deleteMeasurements(MeasurementContext context);
    
    public <V> void deleteMeasurement(Probe<V> probe, MeasurementContext... contexts);
    
    public <V> void addMeasurementListener(BlackboardListener<V, T> l, Probe<V> probe);
    
    public <V> void addMeasurementListener(BlackboardListener<V, T> l);
    
    public void removeMeasurementListener(BlackboardListener<?, T> l);
    
    public void removeMeasurementListener(BlackboardListener<?, T> l, Probe<?> probe);

}
