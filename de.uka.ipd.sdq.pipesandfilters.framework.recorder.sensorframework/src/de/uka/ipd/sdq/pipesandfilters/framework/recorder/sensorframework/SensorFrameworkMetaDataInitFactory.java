package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework;

import java.util.Collections;
import java.util.Map;

import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;

import de.uka.ipd.sdq.pipesandfilters.framework.IMetaDataInitFactory;
import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.launch.SensorFrameworkConfig;

public class SensorFrameworkMetaDataInitFactory implements IMetaDataInitFactory {

	// TODO isRemoteRun (last constructor parameter of SensorFrameworkConfig) cannot be used currently!
    @Override
    public MetaDataInit createMetaDataInit(
    		final MetricSetDescription metricDescriptions,
            final IRecorderConfiguration recorderConfiguration,
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
    	
        return new SensorFrameworkMetaDataInit(metricDescriptions, (SensorFrameworkConfig) recorderConfiguration, 
        		experimentName, experimentRunName, modelElementID, executionResultTypes, false);        
    }

    @Override
    public MetaDataInit createMetaDataInit(
    		final MetricSetDescription metricDescriptions,
            final IRecorderConfiguration recorderConfiguration,
			final String experimentName,
			final String experimentRunName, 
			final String modelElementID) {
        return createMetaDataInit(metricDescriptions, recorderConfiguration, experimentName, experimentRunName, modelElementID, Collections.<Integer, String> emptyMap());
    }

}
