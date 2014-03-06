package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import de.uka.ipd.sdq.pipesandfilters.framework.IMetaDataInitFactory;
import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.launch.SensorFrameworkConfig;

public class SensorFrameworkMetaDataInitFactory implements IMetaDataInitFactory {

	// TODO isRemoteRun (last constructor parameter of SensorFrameworkConfig) cannot be used currently!
    @Override
    public MetaDataInit createMetaDataInit(
    		final List<MeasurementMetric> measuredMetrics,
            final IRecorderConfiguration recorderConfiguration,
            final String metricName,
			final String measurementName, 
			final String experimentName,
			final String experimentRunName, 
			final String modelElementID,
			final Map<Integer, String> executionResultTypes) {
    	if (!(recorderConfiguration instanceof SensorFrameworkConfig)) {
			throw new IllegalArgumentException("Expected meta data containing "
					+ SensorFrameworkConfig.class.getSimpleName()
					+ " but was "
					+ recorderConfiguration.getClass()
							.getSimpleName() + ".");
		}
    	
        return new SensorFrameworkMetaDataInit(measuredMetrics, (SensorFrameworkConfig) recorderConfiguration, 
        		metricName, measurementName, experimentName, experimentRunName, modelElementID, executionResultTypes, false);        
    }

    @Override
    public MetaDataInit createMetaDataInit(
    		final List<MeasurementMetric> measuredMetrics,
            final IRecorderConfiguration recorderConfiguration,
            final String metricName,
			final String measurementName, 
			final String experimentName,
			final String experimentRunName, 
			final String modelElementID) {
        return createMetaDataInit(measuredMetrics, recorderConfiguration, metricName, measurementName, experimentName, experimentRunName, modelElementID, Collections.<Integer, String> emptyMap());
    }

}
