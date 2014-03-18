package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies;

import java.util.HashMap;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.SensorHelper;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.State;
import de.uka.ipd.sdq.sensorframework.entities.StateSensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

public class UtilisationWriteDataStrategy extends AbstractWriteDataStrategy {

    private final HashMap<String, State> statesCache = new HashMap<String, State>();

    private State idleState;

    private State busyState;

    public UtilisationWriteDataStrategy(final IDAOFactory daoFactory,
            final Experiment experiment, final ExperimentRun run) {
        super(daoFactory, experiment, run);
    }

    @Override
    public void initialise(final MetaDataInit metaData) {
        final String sensorId = metaData.getMetricDescriptions().getTextualDescription();
        this.idleState = SensorHelper.createOrReuseState(daoFactory, "Idle");
        this.busyState = SensorHelper.createOrReuseState(daoFactory, "Busy");
        sensor = SensorHelper.createOrReuseStateSensor(daoFactory, experiment,
                sensorId, idleState);
        if (!((StateSensor) sensor).getSensorStates().contains(idleState)) {
            ((StateSensor) sensor).addSensorState(idleState);
        }
        ((StateSensor) sensor).addSensorState(busyState);
    }

    @Override
    public void writeData(final Measurement data) {
        final Measure<Double, Duration> measurementTimeMeasure = getMeasureHelper(data, 0);
        final double measurementTime = measurementTimeMeasure.doubleValue(SI.SECOND);
        final Measure<Integer, Dimensionless> numericStateMeasure = getMeasureHelper(data, 1);
        final int numericState = numericStateMeasure.intValue(Dimensionless.UNIT);
        State state = null;
        if (numericState == 0) {
            state = idleState;
        } else {
            final String stateLiteral = "Busy " + Integer.toString(numericState)
                    + " Job(s)";
            if (!statesCache.containsKey(stateLiteral)) {
                final State newState = SensorHelper.createOrReuseState(daoFactory,
                        stateLiteral);
                statesCache.put(stateLiteral, newState);
                if (!((StateSensor) sensor).getSensorStates()
                        .contains(newState)) {
                    ((StateSensor) sensor).addSensorState(newState);
                }
            }
            state = statesCache.get(stateLiteral);
        }
        run.addStateMeasurement((StateSensor)sensor, state, measurementTime);
    }

}
