package edu.kit.ipd.sdq.probespec.palladio.sensorframework.strategies;

import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.Sensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;
import edu.kit.ipd.sdq.probespec.palladio.sensorframework.SensorHelper;

public abstract class AbstractSensorWrapper implements ISensorWrapper {

    protected IDAOFactory daoFactory;

    protected Experiment experiment;

    protected ExperimentRun run;

    protected Sensor sensor;

    public AbstractSensorWrapper(IDAOFactory daoFactory, Experiment experiment, ExperimentRun run) {
        this.daoFactory = daoFactory;
        this.experiment = experiment;
        this.run = run;
    }

    @Override
    public void initialise(Probe<?> probe) {
        String sensorId = probe.getName(); // metaData.getMeasurementName();
        sensor = SensorHelper.createOrReuseTimeSensor(daoFactory, experiment, sensorId);
    }

}
