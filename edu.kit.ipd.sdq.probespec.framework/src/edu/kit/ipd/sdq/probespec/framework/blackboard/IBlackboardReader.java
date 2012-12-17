package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;

public interface IBlackboardReader<V, T> {

    public Measurement<V, T> getLatestMeasurement();

    public Measurement<V, T> getLatestMeasurement(IMeasurementContext... contexts);
    
    public Measurement<V, T> getLatestMeasurement(ILookupStrategy lookupStrategy, IMeasurementContext... contexts);

    public Probe<V> getProbe();
    
    public void close();

}