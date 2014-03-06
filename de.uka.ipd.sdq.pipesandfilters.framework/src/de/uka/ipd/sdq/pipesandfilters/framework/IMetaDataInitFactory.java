package de.uka.ipd.sdq.pipesandfilters.framework;

import java.util.List;
import java.util.Map;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

public interface IMetaDataInitFactory {

	public MetaDataInit createMetaDataInit(
    		final List<MeasurementMetric> measuredMetrics,
            final IRecorderConfiguration recorderConfiguration,
            final String metricName,
			final String measurementName, 
			final String experimentName,
			final String experimentRunName, 
			final String modelElementID,
			final Map<Integer, String> executionResultTypes);

	public MetaDataInit createMetaDataInit(
    		final List<MeasurementMetric> measuredMetrics,
            final IRecorderConfiguration recorderConfiguration,
            final String metricName,
			final String measurementName, 
			final String experimentName,
			final String experimentRunName, 
			final String modelElementID);
}
