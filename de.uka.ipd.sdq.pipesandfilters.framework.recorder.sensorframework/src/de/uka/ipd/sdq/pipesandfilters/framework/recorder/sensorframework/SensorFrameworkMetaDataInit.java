package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework;

import java.util.List;
import java.util.Map;

import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.launch.SensorFrameworkConfig;

public class SensorFrameworkMetaDataInit extends MetaDataInit {

	private final boolean isRemoteRun;	
	
	public SensorFrameworkMetaDataInit(List<MeasurementMetric> measuredMetrics,
			SensorFrameworkConfig recorderConfiguration, String metricName,
			String measurementName, String experimentName,
			String experimentRunName, String modelElementID,
			Map<Integer, String> executionResultTypes, boolean isRemoteRun) {
		super(measuredMetrics, recorderConfiguration, metricName, measurementName,
				experimentName, experimentRunName, modelElementID, executionResultTypes);
		this.isRemoteRun = isRemoteRun;
	}
	
	public SensorFrameworkMetaDataInit(List<MeasurementMetric> measuredMetrics,
			SensorFrameworkConfig recorderConfiguration, String metricName,
			String measurementName, String experimentName,
			String experimentRunName, String modelElementID) {
		this(measuredMetrics, recorderConfiguration, metricName, measurementName,
				experimentName, experimentRunName, modelElementID, null, false);
	}


    public boolean isRemoteRun() {
		return isRemoteRun;
	}
}
