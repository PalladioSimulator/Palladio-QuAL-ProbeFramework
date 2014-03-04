package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2;

import de.uka.ipd.sdq.edp2.impl.Measurement;
import de.uka.ipd.sdq.edp2.impl.MeasurementsUtility;
import de.uka.ipd.sdq.edp2.models.ExperimentData.BaseMetricDescription;
import de.uka.ipd.sdq.edp2.models.ExperimentData.Edp2Measure;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentDataFactory;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentGroup;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentRun;
import de.uka.ipd.sdq.edp2.models.ExperimentData.ExperimentSetting;
import de.uka.ipd.sdq.edp2.models.ExperimentData.Measurements;
import de.uka.ipd.sdq.edp2.models.ExperimentData.MeasurementsRange;
import de.uka.ipd.sdq.edp2.models.ExperimentData.MetricSetDescription;
import de.uka.ipd.sdq.edp2.models.ExperimentData.Monotonic;
import de.uka.ipd.sdq.edp2.models.ExperimentData.NumericalBaseMetricDescription;
import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.PipeData;

/**
 * This abstract class provides methods necessary to write raw or aggregated measurements to the EDP2.
 * 
 * @author Baum, Sebastian Lehrig
 * 
 */
public abstract class Edp2WriteStrategy {
	
	/** The metric of the measured data. */
	protected MetricSetDescription metric;
	
	/** The EDP2 measure. */
	protected Edp2Measure measure;
	
	/** The EDP2 experiment setting. */
	protected ExperimentSetting experimentSetting;
	
	/** The EDP2 experiment group. */
	protected ExperimentGroup experimentGroup;
	
	/** The EDP2 measurements. */
	protected Measurements measurements;
	
	/** The EDP2 experiment run. */
	protected ExperimentRun experimentRun;
	
	/** The EDP2 measurements range. */
	protected MeasurementsRange measurementsRange;

	/** A string containing the name of the directory the data is stored at. */
	protected String directoryName;


	/**
	 * The constructor of Edp2WriteStrategy.
	 */
	public Edp2WriteStrategy() {
		directoryName = "Edp2Measurements";
	}
	
	/**
	 * In this method, an EDP2 experiment run is prepared by initializing all
	 * necessary EDP2 members.
	 */
	protected abstract void prepareExperimentRun();	
	
	/**
	 * This method should end the current experiment and close the data output
	 * stream.
	 */
	public abstract void flush();
	
	/**
	 * The initializing meta data provided by the recorder is used to define all
	 * EDP2 metrics and the experiment setup.
	 */
	public void initialize(MetaDataInit metaData) {
		// Get EDP2MetaDataInit object
		if (!(metaData instanceof EDP2MetaDataInit)) {			
			throw new IllegalArgumentException(
					"Argument metaData must be of the type EDP2MetaDataInit");
		}			
		EDP2MetaDataInit edp2MetaData = (EDP2MetaDataInit) metaData;

		// Initialize the metric
		metric = ExperimentDataFactory.eINSTANCE.createMetricSetDescription();
		metric.setName(edp2MetaData.getMetricName());

		for (MeasurementMetric measuredObject : edp2MetaData
				.getMeasuredMetrics()) {

			BaseMetricDescription desc = convertMeasurementMetricToBaseMetricDescription(measuredObject);
			metric.getSubsumedMetrics().add(desc);
		}

		// Initialize measure
		measure = ExperimentDataFactory.eINSTANCE.createEdp2Measure();
		// TODO Lehrig: I removed the following line. Check whether that was actually needed ;)
		//measure.setPersistencyKind(PersistenceKindOptions.BINARY_PREFERRED);
		measure.setMetric(metric);
		measure.setMeasuredObject(edp2MetaData.getMeasurementName());
		// Important: Identifiers are not supported by the probespec so far
		// because ordinal values are used instead to represent nominal values.
		// If identifiers should be allowed, the initial identifier must
		// be set here.

		// Get experiment setting
		experimentSetting = edp2MetaData.getExperimentSetting();
		//experimentSetting.getMeasure().add(measure);

		// Get experiment group
		experimentGroup = edp2MetaData.getExperimentGroup();
		experimentGroup.getExperimentSettings().add(experimentSetting);
		experimentGroup.getMeasure().add(measure);

		// Get experiment run
		experimentRun = edp2MetaData.getExperimentRun();

		// Set up measurement
		measurements = ExperimentDataFactory.eINSTANCE.createMeasurements();
		measurements.setMeasure(measure);
		measurements.getAdditionalInformation().put("ModelElementID",
				metaData.getModelElementID());

		prepareExperimentRun();
	}
	
	/**
	 * Converts a MeasurementMetric object to a BaseMetricDescription object.
	 * TODO Lehrig: check whether NumericalBaseMetricDescription is the only case or whether TextualBaseMetricDescription can also occur.
	 * 
	 * @param measuredObject
	 *            The MeasurementMetric object.
	 * @return The converted BaseMetricDescription object.
	 */
	protected BaseMetricDescription convertMeasurementMetricToBaseMetricDescription(
			MeasurementMetric measuredObject) {
		NumericalBaseMetricDescription desc = ExperimentDataFactory.eINSTANCE.createNumericalBaseMetricDescription();

		// CaptureType:
		if (measuredObject.getCaptureType() == de.uka.ipd.sdq.pipesandfilters.framework.CaptureType.NATURAL_NUMBER) {
			desc
					.setCaptureType(de.uka.ipd.sdq.edp2.models.ExperimentData.CaptureType.INTEGER_NUMBER);
		} else if (measuredObject.getCaptureType() == de.uka.ipd.sdq.pipesandfilters.framework.CaptureType.REAL_NUMBER) {
			desc
					.setCaptureType(de.uka.ipd.sdq.edp2.models.ExperimentData.CaptureType.REAL_NUMBER);
		}

		// DefaultUnit:
		desc.setDefaultUnit(measuredObject.getUnit());

		// Monotonic:
		if (measuredObject.isStrongMonotonic())
			desc.setMonotonic(Monotonic.STRONG);
		else if (measuredObject.isMonotonic())
			desc.setMonotonic(Monotonic.YES);
		else
			desc.setMonotonic(Monotonic.NO);

		// Name:
		desc.setName(measuredObject.getName());

		// Scale:
		if (measuredObject.getScale() == de.uka.ipd.sdq.pipesandfilters.framework.Scale.INTERVAL) {
			desc.setScale(de.uka.ipd.sdq.edp2.models.ExperimentData.Scale.INTERVAL);
		} else if (measuredObject.getScale() == de.uka.ipd.sdq.pipesandfilters.framework.Scale.NOMINAL) {
			desc.setScale(de.uka.ipd.sdq.edp2.models.ExperimentData.Scale.NOMINAL);
		} else if (measuredObject.getScale() == de.uka.ipd.sdq.pipesandfilters.framework.Scale.ORDINAL) {
			desc.setScale(de.uka.ipd.sdq.edp2.models.ExperimentData.Scale.ORDINAL);
		} else if (measuredObject.getScale() == de.uka.ipd.sdq.pipesandfilters.framework.Scale.RATIO) {
			desc.setScale(de.uka.ipd.sdq.edp2.models.ExperimentData.Scale.RATIO);
		}

		// Description:
		desc.setTextualDescription(measuredObject.getDescription());

		return desc;
	}
	
	
	
	/**
	 * This method writes given measurement data to the EDP2.
	 * 
	 * FIXME Lehrig: I just hacked a measurement object into that code (before there was just the Object[] array). No idea whether this is correct.
	 */
	public void writeData(PipeData pipeData) {
		//Object[] data = new Object[pipeData.getTupleSize()];
		
		Measurement measurement = new Measurement(metric);
		for (int i = 0; i < pipeData.getTupleSize(); i++) {
			//data[i] = pipeData.getTupleElement(i);
			measurement.setMeasuredValue(0, pipeData.getTupleElement(i));
			MeasurementsUtility.storeMeasurement(measurements, measurement);
		}
		
		// MeasurementsUtility.storeMeasurement(measurements, data);
	}
	
	/**
	 * Returns the name of the directory the incoming data is stored at.
	 * 
	 * @return The name of the directory.
	 */
	public String getDirectoryName() {
		return directoryName;
	}

	/**
	 * Sets the name of the directory incoming data is stored at. Note: The
	 * specified directory must exist.
	 * 
	 * @param directoryName
	 *            The name of the directory.
	 */
	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	/**
	 * Returns the experiment group used by this writer.
	 * 
	 * @return The experiment group this writer is using.
	 */
	public ExperimentGroup getExperimentGroup() {
		return experimentGroup;
	}

	/**
	 * Returns the experiment setting of this writer.
	 * 
	 * @return The experiment setting of this writer.
	 */
	public ExperimentSetting getExperimentSetting() {
		return experimentSetting;
	}	
}
