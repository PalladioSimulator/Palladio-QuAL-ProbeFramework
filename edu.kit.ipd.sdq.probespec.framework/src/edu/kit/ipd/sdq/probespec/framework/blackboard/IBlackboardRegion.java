package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;

public interface IBlackboardRegion<T> {

    public void addMeasurement(T measurement, Probe<T> probe);

    public void addMeasurement(T measurement, Probe<T> probe, IMeasurementContext context);

    public T getLatestMeasurement(Probe<T> probe);

    public T getLatestMeasurement(Probe<T> probe, IMeasurementContext context);

    public void deleteMeasurements(IMeasurementContext context);

    public void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe);

    public void addMeasurementListener(IBlackboardListener<T> l);

    public Class<T> getGenericType();
    
}
