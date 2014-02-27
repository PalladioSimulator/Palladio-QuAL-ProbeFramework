package de.uka.ipd.sdq.pipesandfilters.framework;

import java.util.Map;
import java.util.Vector;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

public interface IMetaDataInitFactory {

    MetaDataInit createMetaDataInit(Vector<MeasurementMetric> measuredMetrics,
            IRecorderConfiguration recorderConfiguration);

    MetaDataInit createMetaDataInit(Vector<MeasurementMetric> measuredMetrics,
            IRecorderConfiguration recorderConfiguration, Map<Integer, String> executionResultTypes);

}
