package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies;

import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.quantity.Quantity;
import javax.measure.unit.SI;

import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

public class DemandedTimeWriteDataStrategy extends AbstractWriteDataStrategy {

	public DemandedTimeWriteDataStrategy(IDAOFactory daoFactory,
			Experiment experiment, ExperimentRun run) {
		super(daoFactory, experiment, run);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void writeData(List<Measure<?, ? extends Quantity>> data) {
		Measure<Double, Duration> measurementTimeMeasure = (Measure<Double, Duration>) data
				.get(0);
		Measure<Double, Duration> demandedTimeMeasure = (Measure<Double, Duration>) data
				.get(1);
		double measurementTime = measurementTimeMeasure.doubleValue(SI.SECOND);
		double demandedTime = demandedTimeMeasure.doubleValue(SI.SECOND);
		run.addTimeSpanMeasurement((TimeSpanSensor)sensor, measurementTime, demandedTime);
	}

}
