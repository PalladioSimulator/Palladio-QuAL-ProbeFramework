package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

public class DemandedTimeWriteDataStrategy extends AbstractWriteDataStrategy {

    public DemandedTimeWriteDataStrategy(final IDAOFactory daoFactory,
            final Experiment experiment, final ExperimentRun run) {
        super(daoFactory, experiment, run);
    }

    @Override
    public void writeData(final Measurement data) {
        final Measure<Double, Duration> measurementTimeMeasure = getMeasureHelper(data, 0);
        final Measure<Double, Duration> demandedTimeMeasure = getMeasureHelper(data,1);
        final double measurementTime = measurementTimeMeasure.doubleValue(SI.SECOND);
        final double demandedTime = demandedTimeMeasure.doubleValue(SI.SECOND);
        run.addTimeSpanMeasurement((TimeSpanSensor)sensor, measurementTime, demandedTime);
    }
}
