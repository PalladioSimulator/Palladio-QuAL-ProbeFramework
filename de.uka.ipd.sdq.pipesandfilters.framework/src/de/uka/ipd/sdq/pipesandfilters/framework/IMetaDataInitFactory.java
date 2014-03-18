package de.uka.ipd.sdq.pipesandfilters.framework;

import java.util.Map;

import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

public interface IMetaDataInitFactory {

	public MetaDataInit createMetaDataInit(
    		final MetricSetDescription metricDescriptions,
            final IRecorderConfiguration recorderConfiguration,
			final String experimentName,
			final String experimentRunName, 
			final String modelElementID,
			final Map<Integer, String> executionResultTypes);

	public MetaDataInit createMetaDataInit(
    		final MetricSetDescription metricDescriptions,
            final IRecorderConfiguration recorderConfiguration,
			final String experimentName,
			final String experimentRunName, 
			final String modelElementID);
}
