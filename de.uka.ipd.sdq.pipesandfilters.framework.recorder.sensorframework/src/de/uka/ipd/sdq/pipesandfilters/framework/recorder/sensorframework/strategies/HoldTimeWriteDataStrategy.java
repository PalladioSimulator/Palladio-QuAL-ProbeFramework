package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

public class HoldTimeWriteDataStrategy extends AbstractWriteDataStrategy {

    public HoldTimeWriteDataStrategy(final IDAOFactory daoFactory,
            final Experiment experiment, final ExperimentRun run) {
        super(daoFactory, experiment, run);
    }

    @Override
    public void writeData(final Measurement data) {
        final Measure<Double, Duration> timeSpanMeasure = getMeasureHelper(data, 0);
        final double timeSpan = timeSpanMeasure.doubleValue(SI.SECOND);
        final Measure<Double, Duration> eventTimeMeasure = getMeasureHelper(data, 1);
        final double eventTime = eventTimeMeasure.doubleValue(SI.SECOND);
        run.addTimeSpanMeasurement((TimeSpanSensor)sensor, eventTime, timeSpan);
    }

}
