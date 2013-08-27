package edu.kit.ipd.sdq.probespec.palladio.sensorframework.strategies;

import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;
import edu.kit.ipd.sdq.probespec.framework.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public class TimeSpanSensorWrapper extends AbstractSensorWrapper {

    public TimeSpanSensorWrapper(IDAOFactory daoFactory, Experiment experiment, ExperimentRun run) {
        super(daoFactory, experiment, run);
    }

    @Override
    public void addMeasurement(Measurement<?, Double> measurement, Probe<?> probe, MeasurementContext... contexts) {
        run.addTimeSpanMeasurement((TimeSpanSensor) sensor, measurement.getTimestamp(), (Double) measurement.getValue());
    }

}
