package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.framework.MeasurementListener;
import edu.kit.ipd.sdq.probespec.framework.Metadata;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;

public interface Blackboard<T> {

    public <V> void addMeasurement(V value, Probe<V> probe);
    
    public <V> void addMeasurement(V value, Probe<V> probe, Metadata metadata);

    public <V> void addMeasurement(V value, Probe<V> probe, MeasurementContext... contexts);
    
    public <V> void addMeasurement(V value, Probe<V> probe, Metadata metadata, MeasurementContext... contexts);
    
    public <V> BlackboardReader<V, T> getReader(Probe<V> probe);
    
    public <V> BlackboardWriter<V> getWriter(Probe<V> probe);
    
    public void deleteMeasurements(MeasurementContext context);
    
    public <V> void deleteMeasurement(Probe<V> probe, MeasurementContext... contexts);
    
    public <V> void addMeasurementListener(MeasurementListener<V, T> l, Probe<V> probe);
    
    public <V> void addMeasurementListener(MeasurementListener<V, T> l);
    
    public void removeMeasurementListener(MeasurementListener<?, T> l);
    
    public void removeMeasurementListener(MeasurementListener<?, T> l, Probe<?> probe);

}
