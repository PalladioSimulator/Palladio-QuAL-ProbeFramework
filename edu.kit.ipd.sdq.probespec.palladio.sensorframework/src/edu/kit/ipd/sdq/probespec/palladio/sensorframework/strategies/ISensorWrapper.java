package edu.kit.ipd.sdq.probespec.palladio.sensorframework.strategies;

import edu.kit.ipd.sdq.probespec.framework.Measurement;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;

public interface ISensorWrapper {

    public void initialise(Probe<?> probe);

    public void addMeasurement(Measurement<?, Double> measurement, Probe<?> probe, MeasurementContext... contexts);

}
