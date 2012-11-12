package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;

public interface IBlackboardRegion<T> {

    public void addMeasurement(T measurement, Probe<T> probe);

    public void addMeasurement(T measurement, Probe<T> probe, IMeasurementContext<T>... context);

    public T getLatestMeasurement(Probe<T> probe);

    public T getLatestMeasurement(Probe<T> probe, IMeasurementContext<T> context);

    public void deleteMeasurements(IMeasurementContext<T> context);

    public void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe);

    public void addMeasurementListener(IBlackboardListener<T> l);
    
    public void removeMeasurementListener(IBlackboardListener<T> l);

    public Class<T> getGenericType();
    
}
