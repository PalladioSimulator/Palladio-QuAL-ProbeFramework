package edu.kit.ipd.sdq.probespec.framework.blackboard.writer;

import edu.kit.ipd.sdq.probespec.framework.IMetadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;

public interface BlackboardWriter<V> {

    public void addMeasurement(V value);

    public void addMeasurement(V value, IMetadata metadata);

    public void addMeasurement(V value, MeasurementContext... contexts);

    public void addMeasurement(V value, IMetadata metadata, MeasurementContext... contexts);

}
