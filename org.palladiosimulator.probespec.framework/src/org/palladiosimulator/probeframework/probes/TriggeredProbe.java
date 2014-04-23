package org.palladiosimulator.probeframework.probes;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.requestcontext.RequestContext;

public abstract class TriggeredProbe extends Probe {

    protected TriggeredProbe(final MetricDescription metricDesciption) {
        super(metricDesciption);
    }

    public Measurement takeMeasurement() {
        return takeMeasurement(RequestContext.EMPTY_REQUEST_CONTEXT);
    }

    public Measurement takeMeasurement(final RequestContext measurementContext) {
        final Measurement newMeasurement = doMeasure(measurementContext);
        notifyMeasurementSourceListener(newMeasurement);
        return newMeasurement;
    }

    protected abstract Measurement doMeasure(RequestContext measurementContext);
}
