package edu.kit.ipd.sdq.probespec.palladio.sensorframework.strategies;

import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

public interface ISensorWrapper {

    public void initialise(Probe<?> probe);

    public void addMeasurement(Measurement<?, Double> measurement, Probe<?> probe, IMeasurementContext... contexts);

}
