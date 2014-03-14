package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2;

import java.util.Collections;
import java.util.Map;

import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;

import de.uka.ipd.sdq.pipesandfilters.framework.IMetaDataInitFactory;
import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.launch.EDP2Config;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

public class EDP2MetaDataInitFactory implements IMetaDataInitFactory {

    @Override
    public MetaDataInit createMetaDataInit(
    		final MetricSetDescription metricDescriptions,
            final IRecorderConfiguration recorderConfiguration,
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
    	
        return new EDP2MetaDataInit(metricDescriptions, (EDP2Config) recorderConfiguration, 
        		experimentName, experimentRunName, modelElementID, executionResultTypes);
        
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
