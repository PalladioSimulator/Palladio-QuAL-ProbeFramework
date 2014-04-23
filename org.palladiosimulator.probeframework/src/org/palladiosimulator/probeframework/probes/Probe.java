package org.palladiosimulator.probeframework.probes;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.measurementspec.MeasurementSource;

public abstract class Probe extends MeasurementSource {

    protected Probe(final MetricDescription metricDesciption) {
        super(metricDesciption);
    }

}
