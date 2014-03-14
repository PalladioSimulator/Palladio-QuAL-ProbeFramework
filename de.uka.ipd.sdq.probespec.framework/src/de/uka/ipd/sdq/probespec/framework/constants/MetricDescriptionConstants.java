package de.uka.ipd.sdq.probespec.framework.constants;

import javax.measure.quantity.Dimensionless;
import javax.measure.unit.SI;

import org.palladiosimulator.edp2.models.ExperimentData.CaptureType;
import org.palladiosimulator.edp2.models.ExperimentData.DataType;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataFactory;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage;
import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.Monotonic;
import org.palladiosimulator.edp2.models.ExperimentData.PersistenceKindOptions;
import org.palladiosimulator.edp2.models.ExperimentData.Scale;

/**
 * Constant metric descriptions, commonly used by ProbeSpec.
 * 
 * TODO Extract metric descriptions to separate EDP2 Repository.
 * 
 * @author Sebastian Lehrig, Steffen Becker
 */
public final class MetricDescriptionConstants {
	
	/** EMF initialization. Must exist but not be used in the further code. */
    @SuppressWarnings("unused")
    private final static ExperimentDataPackage experimentDataPackage = ExperimentDataPackage.eINSTANCE;
    
    /** Shortcut to experiment data factory. */
    private final static ExperimentDataFactory experimentDataFactory = ExperimentDataFactory.eINSTANCE;
    
	/** Specifies a CPU metric, e.g., to store CPU utilization at a certain time/state. */
	public final static MetricDescription CPU_STATE_METRIC = createNewNaturalNumberMetric("State of the CPU resource", "This measure represents the state of the CPU resource", "_BoroIZMbEd6Vw8NDgVSYcgLehr0");
	
	/** Specifies an execution results metric, e.g., to store simulated failure occurances. */
	public final static MetricDescription EXECUTION_RESULT_METRIC = createNewNaturalNumberMetric("Type of execution result", "This measure represents the type of execution result", "_AiroIZMbEd6Vw8NDgVSYcgLehr1");
	
	/** Specifies a hold time metric, e.g., to store the time spend in a passive resource. */
	public final static MetricDescription HOLD_TIME_METRIC = createNewTimeMetric("Hold Time", "This measure represents the hold time at a resource, e.g., a passive resource pool.", "_0xrYsCUQEd6gmLudJva2DwLehr2");
	
	/** Specifies a point in time metric, e.g., to store an event time stamp. */
	public final static MetricDescription POINT_IN_TIME_METRIC = createNewTimeMetric("Point in time", "This measure represents the point of time when the value is taken", "_38mSASUPEd6gmLudJva2DwLehr3");
	
	/** Specifies a resource demand metric, e.g., to store CPU demand measurements. */
	public final static MetricDescription RESOURCE_DEMAND_METRIC = createNewTimeMetric("Demand of the entity", "This measure represents the CPU/HDD/DELAY/... demand of the entity", "_fvNrgCUQEd6gmLudJva2DwLehr4");
	
	/** Specifies a response time metric, e.g., to store the response time of operation calls. */
	public final static MetricDescription RESPONSE_TIME_METRIC = createNewTimeMetric("Response Time", "This measure represents the response time.", "_QC3ucCUQEd6gmLudJva2DwLehr5");
	
	/** Specifies a waiting time metric, e.g., to store the waiting time at passive resource pools. */
	public final static MetricDescription WAITING_TIME_METRIC = createNewTimeMetric("Waiting Time", "This measure represents the waiting time", "_nU2AICUQEd6gmLudJva2DwLehr6");	
	
	
	private MetricDescriptionConstants() {
	}
	
	/**
	 * Creates a new time metric for a given name and description. Time metrics are
	 * expressed in natural numbers and with seconds as unit. Moreover, they are
	 * ration scaled. An example time metric is response time. 
	 * 
	 * TODO Do we really only use ratio scales here?
	 * 
	 * @param name The name of the metric, e.g., "response time".
	 * @param description The description of the metric.
	 * @param uuid A unique ID for the metric.
	 * @return A new MeasurementMetric for time.
	 */
	private static MetricDescription createNewTimeMetric(final String name, final String description, final String uuid) {
		final MetricDescription metric = experimentDataFactory.createNumericalBaseMetricDescription(
				name,
				description,
				CaptureType.REAL_NUMBER,
	            Scale.RATIO, // time is generally Scale.INTERVAL but for our time metrics, we have an absolute 0-point (-> Scale.RATIO!)
	            DataType.QUANTITATIVE,
	            SI.SECOND,
	            Monotonic.NO,
	            PersistenceKindOptions.BINARY_PREFERRED
	        );
		metric.setUuid(uuid);
		return metric;
	}

	/**
	 * Creates a new natural number metric for a given name and description. Natural number metrics are
	 * expressed in natural numbers and have a dimensionless unit. Moreover, they are
	 * ordinal. An example natural number metric is resource demand.
	 * 
	 * @param name The name of the metric, e.g., "resource demand".
	 * @param description The description of the metric.
	 * @param uuid A unique ID for the metric.
	 * @return A new MeasurementMetric for state information.
	 */
	private static MetricDescription createNewNaturalNumberMetric(final String name, final String description, final String uuid) {
		final MetricDescription metric = experimentDataFactory.createNumericalBaseMetricDescription(
				name,
				description,
				CaptureType.INTEGER_NUMBER,
	            Scale.ORDINAL,
	            DataType.QUANTITATIVE,
	            Dimensionless.UNIT,
	            Monotonic.NO,
	            PersistenceKindOptions.BINARY_PREFERRED
	        );
		metric.setUuid(uuid);
		return metric;
	}
}
