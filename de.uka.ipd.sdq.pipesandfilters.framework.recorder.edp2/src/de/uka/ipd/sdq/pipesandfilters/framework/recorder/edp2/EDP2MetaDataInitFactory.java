package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2;

import java.util.Map;
import java.util.Vector;

import de.uka.ipd.sdq.pipesandfilters.framework.IMetaDataInitFactory;
import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

public class EDP2MetaDataInitFactory implements IMetaDataInitFactory {

    @Override
    public MetaDataInit createMetaDataInit(Vector<MeasurementMetric> measuredMetrics,
            IRecorderConfiguration recorderConfiguration) {
        return new EDP2MetaDataInit(measuredMetrics, recorderConfiguration);
    }

    @Override
    public MetaDataInit createMetaDataInit(Vector<MeasurementMetric> measuredMetrics,
            IRecorderConfiguration recorderConfiguration, Map<Integer, String> executionResultTypes) {
        return new EDP2MetaDataInit(measuredMetrics, recorderConfiguration, executionResultTypes);
    }

}
