package de.uka.ipd.sdq.pipesandfilters.framework;

import java.util.List;
import java.util.Map;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

/**
 * The MetaDataInit class is used to provide all recorders with initializing
 * meta information. It can be used by the specified WriteStrategy to store
 * detailed information about the measurements.
 * 
 * @author Baum, Sebastian Lehrig, Steffen Becker
 */
public abstract class MetaDataInit {

	/**
	 * This list should hold one MeasuredMetric with measurement information
	 * for each tuple that is inducted to the pipe by the calculators.
	 */
	private final List<MeasurementMetric> measuredMetrics;

	private final IRecorderConfiguration recorderConfiguration;

	/**
	 * The name of the measured metric.
	 */
	private final String metricName;

	/**
	 * The name of the measurement.
	 */
	private final String measurementName;

	/**
	 * The name of the experiment.
	 */
	private final String experimentName;
	
	/**
	 * The name of the experiment run.
	 */
	private final String experimentRunName;

	/**
	 * The id of the model element.
	 */
	private final String modelElementID;

	/**
     * The mapping of numerical values to strings representing the possible
     * execution results.
     */
    private final Map<Integer, String> executionResultTypes;
    

    /**
     * The constructor. Represents the meta data for initialization of an
     * ExecutionResultWriteDataStrategy.
     * 
     * In addition to the other constructors, this constructor contains a mapping from numerical
     * values to strings representing the different possible execution results. This
     * is necessary so that during the simulation, only the numerical values need to
     * be piped to the target sensor.
     * 
     * @param measuredMetrics A vector of all measured metrics of a calculator
     * @param recorderConfiguration The recorder configuration
     * @param metricName Name of the metric
     * @param measurementName Name of the measurement
     * @param experimentName Name of the experiment
     * @param experimentRunName Name of the experiment run 
     * @param modelElementID Unique ID of the model element
     * @param executionResultTypes The mapping of numerical values to strings representing the
     *            possible execution results
     */
	public MetaDataInit(List<MeasurementMetric> measuredMetrics,
			IRecorderConfiguration recorderConfiguration, String metricName,
			String measurementName, String experimentName,
			String experimentRunName, String modelElementID,
			Map<Integer, String> executionResultTypes) {
		super();
		
		if( measuredMetrics == null ||
			measuredMetrics.isEmpty() ||
			recorderConfiguration == null ||
			!isSet(metricName) ||
			!isSet(measurementName) ||
			!isSet(experimentRunName) ||
			//!isSet(modelElementID) || // TODO enable IDs for elements
			executionResultTypes == null
		) {
			throw new IllegalArgumentException("Tried to initialize MetaDataInit incorrectly");
		}
		
		this.measuredMetrics = measuredMetrics;
		this.recorderConfiguration = recorderConfiguration;
		this.metricName = metricName;
		this.measurementName = measurementName;
		this.experimentName = experimentName;
		this.experimentRunName = experimentRunName;
		this.modelElementID = modelElementID;
		this.executionResultTypes = executionResultTypes;
	}

	private boolean isSet(String string) {
		return (string != null && !string.isEmpty());
	}
	
	public IRecorderConfiguration getRecorderConfiguration() {
		return recorderConfiguration;
	}

	/**
	 * Returns the name of the metric.
	 * 
	 * @return The name of the metric.
	 */
	public String getMetricName() {
		return metricName;
	}

	/**
	 * Returns the name of the experiment.
	 * 
	 * @return The name of the experiment.
	 */
	public String getExperimentName() {
		return experimentName;
	}
	
	/**
	 * Returns the name of the experiment run.
	 * 
	 * @return The name of the experiment run.
	 */
	public String getExperimentRunName() {
		return experimentRunName;
	}


	/**
	 * Returns the vector of all measured objects.
	 * 
	 * @return A Vector of measured objects.
	 */
	public List<MeasurementMetric> getMeasuredMetrics() {
		return measuredMetrics;
	}

	/**
	 * Adds a measured object to the vector of measured objects.
	 * 
	 * @param o
	 *            The measured object to be added.
	 */
	public void addMeasuredObject(MeasurementMetric o) {
		measuredMetrics.add(o);
	}

	/**
	 * Returns the name of the measurement.
	 * 
	 * @return The name of the measurement.
	 */
	public String getMeasurementName() {
		return measurementName;
	}

	/**
	 * Returns the ID of the model element.
	 * 
	 * @return The model element ID.
	 */
	public String getModelElementID() {
		return modelElementID;
	}
	
	/**
     * Retrieves the collection of execution result types.
     * 
     * @return the execution result types
     */
    public Map<Integer, String> getExecutionResultTypes() {
        return executionResultTypes;
    }
}