package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies;

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

public class OverallUtilisationWriteDataStrategy extends AbstractWriteDataStrategy {

    private State idleState;

    private State busyState;

    public OverallUtilisationWriteDataStrategy(final IDAOFactory daoFactory,
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
            state = busyState;
        }
        run.addStateMeasurement((StateSensor)sensor, state, measurementTime);
    }

}
