package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public interface IBlackboard {

    public <T> void addMeasurement(Measurement<T> measurement, Probe<T> probe);

    public <T> void addMeasurement(Measurement<T> measurement, Probe<T> probe, IMeasurementContext... context);
    
    public <T> Measurement<T> getLatestMeasurement(Probe<T> probe);
    
    public <T> Measurement<T> getLatestMeasurement(Probe<T> probe, IMeasurementContext... context);
    
    public void deleteMeasurements(IMeasurementContext context);
    
    public <T> void deleteMeasurement(Probe<T> probe, IMeasurementContext... context);
    
    public <T> void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe);

    public <T> void addMeasurementListener(IBlackboardListener<T> l);
    
    public <T> void removeMeasurementListener(IBlackboardListener<T> l);

}
