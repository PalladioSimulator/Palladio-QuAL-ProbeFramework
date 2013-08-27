package edu.kit.ipd.sdq.probespec.palladio.sensorframework.strategies;

import edu.kit.ipd.sdq.probespec.framework.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface ISensorWrapper {

    public void initialise(Probe<?> probe);

    public void addMeasurement(Measurement<?, Double> measurement, Probe<?> probe, MeasurementContext... contexts);

}
