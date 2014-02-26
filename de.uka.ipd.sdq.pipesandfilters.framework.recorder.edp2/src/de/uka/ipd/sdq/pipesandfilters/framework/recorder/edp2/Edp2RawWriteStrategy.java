package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2;

import java.util.Date;

import javax.measure.Measure;
import javax.measure.unit.SI;

import de.uka.ipd.sdq.edp2.impl.MeasurementsUtility;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentDataFactory;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.IRawWriteStrategy;

/**
 * This class provides methods necessary to write raw measurements to the EDP2.
 * 
 * @author Baum
 * 
 */
public class Edp2RawWriteStrategy extends Edp2WriteStrategy implements IRawWriteStrategy {

	/**
	 * In this method an EDP2 experiment run is prepared by initializing all
	 * necessary EDP2 members.
	 */
	protected void prepareExperimentRun() {
		
//		measurement = ExperimentDataFactory.eINSTANCE.createMeasurement();
//		measurement.setMeasure(measure);
//		experimentRun = ExperimentDataFactory.eINSTANCE.createExperimentRun();
		
		experimentRun.getMeasurements().add(measurements);
		experimentSetting.getExperimentRuns().add(experimentRun);

		measurementsRange = MeasurementsUtility.addMeasurementRange(measurements);
		measurementsRange.setRawMeasurements(ExperimentDataFactory.eINSTANCE
				.createRawMeasurements());

		// TODO check whether this is needed
		//MeasurementsUtility.addDataSeries(measurementsRange.getRawMeasurements());
	}

	/**
	 * This method will end the current experiment and close the data output
	 * stream.
	 */
	public void flush() {

		long startTime = experimentRun.getStartTime().getTime();
		long endTime = new Date().getTime();
		experimentRun.setDuration(Measure.valueOf(endTime - startTime,
				SI.SECOND));
		measurementsRange.setStartTime(Measure.valueOf(startTime, SI.SECOND));
		measurementsRange.setEndTime(Measure.valueOf(endTime, SI.SECOND));

/*	Now superfluous?
 * 	for (DataSeries ds : measurementRange.getRawMeasurements().getDataSeries())
		{
			try {
				if (StorageFactory.INSTANCE.getDaoRegistry().getEdp2Dao(
						ds.getValuesUuid()).isOpen())
				StorageFactory.INSTANCE.getDaoRegistry().getEdp2Dao(
						ds.getValuesUuid()).close();
			} catch (DataNotAccessibleException e) {
				e.printStackTrace();
			}
		}*/
	}
}
