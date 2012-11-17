package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public interface IBlackboard<U> {

    public <T> void addMeasurement(T measurement, Probe<T> probe);

    public <T> void addMeasurement(T measurement, Probe<T> probe, IMeasurementContext... context);
    
    public <T> Measurement<T, U> getLatestMeasurement(Probe<T> probe);
    
    public <T> Measurement<T, U> getLatestMeasurement(Probe<T> probe, IMeasurementContext... context);
    
    public void deleteMeasurements(IMeasurementContext context);
    
    public <T> void deleteMeasurement(Probe<T> probe, IMeasurementContext... context);
    
    public <T> void addMeasurementListener(IBlackboardListener<T, U> l, Probe<T> probe);

    public <T> void addMeasurementListener(IBlackboardListener<T, U> l);
    
    public <T> void removeMeasurementListener(IBlackboardListener<T, U> l);

}
