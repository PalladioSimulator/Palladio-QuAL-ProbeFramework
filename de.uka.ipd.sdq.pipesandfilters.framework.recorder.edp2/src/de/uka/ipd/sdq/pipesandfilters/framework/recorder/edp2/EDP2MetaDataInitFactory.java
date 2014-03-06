package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import de.uka.ipd.sdq.pipesandfilters.framework.IMetaDataInitFactory;
import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.launch.EDP2Config;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

public class EDP2MetaDataInitFactory implements IMetaDataInitFactory {

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
    	if (!(recorderConfiguration instanceof EDP2Config)) {
			throw new IllegalArgumentException("Expected meta data containing "
					+ EDP2Config.class.getSimpleName()
					+ " but was "
					+ recorderConfiguration.getClass()
							.getSimpleName() + ".");
		}
    	
        return new EDP2MetaDataInit(measuredMetrics, (EDP2Config) recorderConfiguration, 
        		metricName, measurementName, experimentName, experimentRunName, modelElementID, executionResultTypes);
        
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
