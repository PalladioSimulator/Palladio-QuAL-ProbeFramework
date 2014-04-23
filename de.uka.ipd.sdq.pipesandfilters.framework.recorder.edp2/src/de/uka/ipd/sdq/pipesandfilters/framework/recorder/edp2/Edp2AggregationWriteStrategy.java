package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2;

import java.util.Date;

import javax.measure.Measure;
import javax.measure.unit.SI;

import org.palladiosimulator.edp2.impl.MeasurementsUtility;
import org.palladiosimulator.edp2.models.ExperimentData.AggregatedMeasurements;
import org.palladiosimulator.edp2.models.ExperimentData.AggregationFunctionDescription;
import org.palladiosimulator.edp2.models.ExperimentData.BaseMetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataFactory;
import org.palladiosimulator.edp2.models.ExperimentData.FixedIntervals;
import org.palladiosimulator.edp2.models.ExperimentData.FixedWidthAggregatedMeasurements;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.AggregationMetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.FixedWidthAggregationMetaData;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.IAggregationWriteStrategy;

/**
 * This class provides methods necessary to write aggregated measurements to the
 * EDP2.
 * 
 * TODO This class is not considered by the current extension. Some extensions are needed to enable such aggregated measurements.
 * 
 * 
 * @author Baum, Sebastian Lehrig
 * 
 */
public class Edp2AggregationWriteStrategy extends Edp2WriteStrategy implements
		IAggregationWriteStrategy {
	/**
	 * In this method an EDP2 experiment run is prepared by initializing all
	 * necessary EDP2 members.
	 */
	@Override
	protected void prepareExperimentRun() {
		experimentRun.getMeasurements().add(measurements);
		experimentSetting.getExperimentRuns().add(experimentRun);

		measurementsRange = MeasurementsUtility.addMeasurementRange(measurements);
	}

	/**
	 * Initializes the aggregated measurements of the write strategy.
	 * 
	 * @param metaData
	 *            The initializing meta data.
	 */
	@Override
	public void initializeAggregatedMeasurements(
			AggregationMetaDataInit metaData) {

		FixedWidthAggregatedMeasurements aggregatedMeasurements = ExperimentDataFactory.eINSTANCE
				.createFixedWidthAggregatedMeasurements();

		// Set metric
		aggregatedMeasurements.setMetric(metricSetDescription);

		// Set aggregated metric
		aggregatedMeasurements.setAggregationOn((BaseMetricDescription) metricSetDescription
				.getSubsumedMetrics().get(metaData.getAggregatedMetricIndex()));

		// Set aggregation function description
		AggregationFunctionDescription desc = ExperimentDataFactory.eINSTANCE
				.createAggregationFunctionDescription();
		desc.setName(metaData.getAggregationFunctionName());
		desc
				.setTextualDescription(metaData
						.getAggregationFunctionDescription());
		aggregatedMeasurements.setFunction(desc);

		measurementsRange.getAggregatedMeasurements()
				.add(aggregatedMeasurements);
		//TODO: add aggregated measurements properly. 
		/*MeasurementsUtility.addDataSeries(aggregatedMeasurements,
				aggregatedMeasurements.getFunction());*/
		throw new UnsupportedOperationException("Aggregated Measurements " +
				"are not yet supported by EDP2. Fix the "
				+this.getClass().getName()+"'s initializeAggregatedMeasurements()" +
				" method when EDP2 has been adjusted.");
	}

	/**
	 * Sets the fixed width aggregated measurements meta data for the
	 * AggregatedMeasurements object at the specified index
	 * (AggregatedMeasurements are ordered ascending in time they were
	 * initialized at).
	 * 
	 * @param aggregatedMeasurementsIndex
	 *            Index of the AggregateMeasurements object.
	 * @param metaData
	 *            The fixed width aggregated measurements meta data.
	 */
	@Override
	public void setFixedWidthAggregatedMeasurementsMetaData(
			int aggregatedMeasurementsIndex,
			FixedWidthAggregationMetaData metaData) {

		FixedIntervals intervals = ExperimentDataFactory.eINSTANCE
				.createFixedIntervals();
		intervals.setLowerBound(metaData.getLowerBound());
		intervals.setWidth(metaData.getWidth());
		intervals.setNumberOfIntervals(metaData.getNumberOfIntervals());

		((FixedWidthAggregatedMeasurements) measurementsRange
				.getAggregatedMeasurements().get(aggregatedMeasurementsIndex))
				.setIntervals(intervals);
	}

	/**
	 * This method will end the current experiment and close the data output
	 * stream.
	 */
	@Override
	public void flush() {

		long startTime = experimentRun.getStartTime().getTime();
		long endTime = new Date().getTime();
		experimentRun.setDuration(Measure.valueOf(endTime - startTime,
				SI.SECOND));
		measurementsRange.setStartTime(Measure.valueOf(startTime, SI.SECOND));
		measurementsRange.setEndTime(Measure.valueOf(endTime, SI.SECOND));

		for (AggregatedMeasurements measurements : measurementsRange
				.getAggregatedMeasurements()) {
			FixedWidthAggregatedMeasurements fixedMeas = ((FixedWidthAggregatedMeasurements) measurements);

/*	Now superfluous?
 * 			for (DataSeries ds : fixedMeas.getDataSeries()) {
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
}