package edu.kit.ipd.sdq.probespec.framework.blackboard.reader;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

public interface IBlackboardReader<V, T> {

    public Measurement<V, T> getLatestMeasurement();

    public Measurement<V, T> getLatestMeasurement(IMeasurementContext... contexts);
    
    public Measurement<V, T> getLatestMeasurement(ILookupStrategy lookupStrategy, IMeasurementContext... contexts);

    public Probe<V> getProbe();
    
    public void close();

}