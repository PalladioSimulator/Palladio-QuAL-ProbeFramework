package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework;

import java.util.Map;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;

import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.launch.SensorFrameworkConfig;

public class SensorFrameworkMetaDataInit extends MetaDataInit {

	private final boolean isRemoteRun;	
	
	public SensorFrameworkMetaDataInit(MetricSetDescription metricDescriptions,
			SensorFrameworkConfig recorderConfiguration,
			String experimentName,
			String experimentRunName, String modelElementID,
			Map<Integer, String> executionResultTypes, boolean isRemoteRun) {
		super(metricDescriptions, recorderConfiguration,
				experimentName, experimentRunName, modelElementID, executionResultTypes);
		this.isRemoteRun = isRemoteRun;
	}
	
	public SensorFrameworkMetaDataInit(MetricSetDescription metricDescriptions,
			SensorFrameworkConfig recorderConfiguration, MetricDescription metricDescription,
			String measurementName, String experimentName,
			String experimentRunName, String modelElementID) {
		this(metricDescriptions, recorderConfiguration,
				experimentName, experimentRunName, modelElementID, null, false);
	}


    public boolean isRemoteRun() {
		return isRemoteRun;
	}
}
