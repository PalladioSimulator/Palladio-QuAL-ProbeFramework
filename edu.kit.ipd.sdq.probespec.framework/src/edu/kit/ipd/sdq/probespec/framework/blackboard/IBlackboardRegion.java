package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;

public interface IBlackboardRegion<V, T> {

    public void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts);
    
    public void addMeasurement(V value, Probe<V> probe, IMeasurementMetadata metadata, IMeasurementContext... contexts);

    public Measurement<V, T> getLatestMeasurement(Probe<V> probe, IMeasurementContext... contexts);

    public void deleteMeasurement(Probe<V> probe, IMeasurementContext... contexts);

    public void deleteMeasurements(IMeasurementContext context);

    public void addMeasurementListener(IBlackboardListener<V, T> l, Probe<V> probe);

    public void addMeasurementListener(IBlackboardListener<V, T> l);
    
    public void removeMeasurementListener(IBlackboardListener<V, T> l);

    public IBlackboardReader<V, T> getReader(Probe<V> probe);
    
    public IBlackboardWriter<V> getWriter(Probe<V> probe);
    
    public Class<V> getGenericType();

}
