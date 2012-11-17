package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public interface IBlackboardRegion<T, U> {

    public void addMeasurement(T measurement, Probe<T> probe);

    public void addMeasurement(T measurement, Probe<T> probe, IMeasurementContext... contexts);

    public Measurement<T, U> getLatestMeasurement(Probe<T> probe);

    public Measurement<T, U> getLatestMeasurement(Probe<T> probe, IMeasurementContext... contexts);

    public void deleteMeasurement(Probe<T> probe, IMeasurementContext... context);
    
    public void deleteMeasurements(IMeasurementContext context);

    public void addMeasurementListener(IBlackboardListener<T, U> l, Probe<T> probe);

    public void addMeasurementListener(IBlackboardListener<T, U> l);
    
    public void removeMeasurementListener(IBlackboardListener<T, U> l);

    public Class<T> getGenericType();
    
}
