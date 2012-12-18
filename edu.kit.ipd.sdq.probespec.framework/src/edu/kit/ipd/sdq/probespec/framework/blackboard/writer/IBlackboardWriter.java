package edu.kit.ipd.sdq.probespec.framework.blackboard.writer;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementMetadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

public interface IBlackboardWriter<V> {

    public void addMeasurement(V value);

    public void addMeasurement(V value, IMeasurementMetadata metadata);

    public void addMeasurement(V value, IMeasurementContext... contexts);

    public void addMeasurement(V value, IMeasurementMetadata metadata, IMeasurementContext... contexts);

}
