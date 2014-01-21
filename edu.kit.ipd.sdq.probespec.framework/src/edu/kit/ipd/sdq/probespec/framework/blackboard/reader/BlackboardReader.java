package edu.kit.ipd.sdq.probespec.framework.blackboard.reader;

import edu.kit.ipd.sdq.probespec.framework.Measurement;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;

public interface BlackboardReader<V, T> {

    public Measurement<V, T> getLatestMeasurement();

    public Measurement<V, T> getLatestMeasurement(MeasurementContext... contexts);
    
    public Measurement<V, T> getLatestMeasurement(LookupStrategy lookupStrategy, MeasurementContext... contexts);

    public Probe<V> getProbe();
    
    public void close();

}