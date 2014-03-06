package de.uka.ipd.sdq.probespec.framework.constants;

import static de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric.createNewNaturalNumberMetric;
import static de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric.createNewTimeMetric;
import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;

public final class MeasurementMetricConstants {
	
	/** Specifies a CPU metric, e.g., to store CPU utilization at a certain time/state. */
	public final static MeasurementMetric CPU_STATE_METRIC = createNewNaturalNumberMetric("State of the CPU resource", "This measure represents the state of the CPU resource");
	
	/** Specifies an execution results metric, e.g., to store simulated failure occurances. */
	public final static MeasurementMetric EXECUTION_RESULT_METRIC = createNewNaturalNumberMetric("Type of execution result", "This measure represents the type of execution result");
	
	/** Specifies a hold time metric, e.g., to store the time spend in a passive resource. */
	public final static MeasurementMetric HOLD_TIME_METRIC = createNewTimeMetric("Hold Time", "This measure represents the hold time at a resource, e.g., a passive resource pool.");
	
	/** Specifies a point in time metric, e.g., to store an event time stamp. */
	public final static MeasurementMetric POINT_IN_TIME_METRIC = createNewTimeMetric("Point in time", "This measure represents the point of time when the value is taken");
	
	/** Specifies a resource demand metric, e.g., to store CPU demand measurements. */
	public final static MeasurementMetric RESOURCE_DEMAND_METRIC = createNewTimeMetric("Demand of the entity", "This measure represents the CPU/HDD/DELAY/... demand of the entity");
	
	/** Specifies a response time metric, e.g., to store the response time of operation calls. */
	public final static MeasurementMetric RESPONSE_TIME_METRIC = createNewTimeMetric("Response Time", "This measure represents the response time.");
	
	/** Specifies a waiting time metric, e.g., to store the waiting time at passive resource pools. */
	public final static MeasurementMetric WAITING_TIME_METRIC = createNewTimeMetric("Waiting Time", "This measure represents the waiting time");
	
	private MeasurementMetricConstants() {
	}
}
