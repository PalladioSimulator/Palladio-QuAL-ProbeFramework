package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.framework.MeasurementListener;
import edu.kit.ipd.sdq.probespec.framework.Metadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface BlackboardRegion<V, T> {

    public void addMeasurement(V value, Probe<V> probe, MeasurementContext... contexts);
    
    public void addMeasurement(V value, Probe<V> probe, Metadata metadata, MeasurementContext... contexts);

    public Measurement<V, T> getLatestMeasurement(Probe<V> probe, MeasurementContext... contexts);

    public void deleteMeasurement(Probe<V> probe, MeasurementContext... contexts);

    public void deleteMeasurements(MeasurementContext context);

    public void addMeasurementListener(MeasurementListener<V, T> l, Probe<V> probe);

    public void addMeasurementListener(MeasurementListener<V, T> l);
    
    public void removeMeasurementListener(MeasurementListener<?, T> l);
    
    public void removeMeasurementListener(MeasurementListener<?, T> l, Probe<?> probe);

    public BlackboardReader<V, T> getReader(Probe<V> probe);
    
    public BlackboardWriter<V> getWriter(Probe<V> probe);
    
    public Class<V> getGenericType();

}
