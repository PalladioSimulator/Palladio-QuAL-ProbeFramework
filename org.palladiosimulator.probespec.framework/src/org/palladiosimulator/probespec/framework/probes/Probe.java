package org.palladiosimulator.probespec.framework.probes;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.probespec.framework.measurements.MeasurementSource;

public abstract class Probe extends MeasurementSource {

    protected Probe(final MetricDescription metricDesciption) {
        super(metricDesciption);
    }

}
