package de.uka.ipd.sdq.probespec.framework.probes;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;

import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSource;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

public abstract class Probe extends MeasurementSource {

    protected Probe(final MetricDescription metricDesciption) {
        super(metricDesciption);
    }

    public final Measurement takeMeasurement(final RequestContext measurementContext) {
        final Measurement newMeasurement = doMeasure(measurementContext);
        notifyMeasurementSourceListener(newMeasurement);
        return newMeasurement;
    }

    protected abstract Measurement doMeasure(RequestContext measurementContext);
}
