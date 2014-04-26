package org.palladiosimulator.probeframework.probes;

import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;

public abstract class TriggeredProbe extends Probe {

    protected TriggeredProbe(final MetricDescription metricDesciption) {
        super(metricDesciption);
    }

    public ProbeMeasurement takeMeasurement() {
        return takeMeasurement(RequestContext.EMPTY_REQUEST_CONTEXT);
    }

    public ProbeMeasurement takeMeasurement(final RequestContext measurementContext) {
        final ProbeMeasurement newMeasurement = doMeasure(measurementContext);
        notifyMeasurementSourceListener(newMeasurement);
        return newMeasurement;
    }

    protected abstract ProbeMeasurement doMeasure(RequestContext measurementContext);
}
