package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public interface IBlackboardRegion<T> {

    public void addMeasurement(Measurement<T> measurement, Probe<T> probe);

    public void addMeasurement(Measurement<T> measurement, Probe<T> probe, IMeasurementContext... contexts);

    public Measurement<T> getLatestMeasurement(Probe<T> probe);

    public Measurement<T> getLatestMeasurement(Probe<T> probe, IMeasurementContext... contexts);

    public void deleteMeasurement(Probe<T> probe, IMeasurementContext... context);
    
    public void deleteMeasurements(IMeasurementContext context);

    public void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe);

    public void addMeasurementListener(IBlackboardListener<T> l);
    
    public void removeMeasurementListener(IBlackboardListener<T> l);

    public Class<T> getGenericType();
    
}
