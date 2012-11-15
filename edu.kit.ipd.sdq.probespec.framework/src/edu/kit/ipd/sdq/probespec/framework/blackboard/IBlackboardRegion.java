package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public interface IBlackboardRegion<T> {

    public void addMeasurement(T value, Probe<T> probe);

    public void addMeasurement(T value, Probe<T> probe, IMeasurementContext... contexts);

    public T getLatestMeasurement(Probe<T> probe);

    public T getLatestMeasurement(Probe<T> probe, IMeasurementContext... contexts);

    public void deleteMeasurement(Probe<T> probe, IMeasurementContext... context);
    
    public void deleteMeasurements(IMeasurementContext context);

    public void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe);

    public void addMeasurementListener(IBlackboardListener<T> l);
    
    public void removeMeasurementListener(IBlackboardListener<T> l);

    public Class<T> getGenericType();
    
}
