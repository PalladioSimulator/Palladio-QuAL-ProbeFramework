package de.uka.ipd.sdq.probespec.framework.probes;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;

import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSource;

public abstract class Probe extends MeasurementSource {

    protected Probe(final MetricDescription metricDesciption) {
        super(metricDesciption);
    }

}
