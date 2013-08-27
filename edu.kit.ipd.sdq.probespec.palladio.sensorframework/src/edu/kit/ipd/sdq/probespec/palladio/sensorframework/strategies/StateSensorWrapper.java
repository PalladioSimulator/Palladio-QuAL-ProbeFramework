package edu.kit.ipd.sdq.probespec.palladio.sensorframework.strategies;

import java.util.HashMap;

import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.State;
import de.uka.ipd.sdq.sensorframework.entities.StateSensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.palladio.sensorframework.SensorHelper;

public class StateSensorWrapper extends AbstractSensorWrapper {

    private HashMap<String, State> statesCache = new HashMap<String, State>();

    private State idleState;

    private State busyState;

    public StateSensorWrapper(IDAOFactory daoFactory, Experiment experiment, ExperimentRun run) {
        super(daoFactory, experiment, run);
    }

    @Override
    public void addMeasurement(Measurement<?, Double> measurement, Probe<?> probe, IMeasurementContext... contexts) {
        int numericState = (Integer) measurement.getValue();
        State state = null;
        if (numericState == 0) {
            state = idleState;
        } else {
            String stateLiteral = "Busy " + Integer.toString(numericState) + " Job(s)";
            if (!statesCache.containsKey(stateLiteral)) {
                State newState = SensorHelper.createOrReuseState(daoFactory, stateLiteral);
                statesCache.put(stateLiteral, newState);
                if (!((StateSensor) sensor).getSensorStates().contains(newState))
                    ((StateSensor) sensor).addSensorState(newState);
            }
            state = statesCache.get(stateLiteral);
        }
        run.addStateMeasurement((StateSensor) sensor, state, (Double) measurement.getTimestamp());
    }

    @Override
    public void initialise(Probe<?> probe) {
        String sensorId = probe.getName(); // metaData.getMeasurementName();

        this.idleState = SensorHelper.createOrReuseState(daoFactory, "Idle");
        this.busyState = SensorHelper.createOrReuseState(daoFactory, "Busy");
        sensor = SensorHelper.createOrReuseStateSensor(daoFactory, experiment, sensorId, idleState);
        if (!((StateSensor) sensor).getSensorStates().contains(idleState)) {
            ((StateSensor) sensor).addSensorState(idleState);
        }
        ((StateSensor) sensor).addSensorState(busyState);
    }

}
