package org.palladiosimulator.probeframework.probes;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.palladiosimulator.measurementspec.BasicMeasurement;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;

public abstract class BasicEventProbe<EventSourceType, V, Q extends Quantity> extends EventProbe<EventSourceType> {


    public BasicEventProbe(final EventSourceType eventSource, final BaseMetricDescription metric) {
        super(eventSource,metric);
    }

    protected void notify(final Measure<V,Q> eventMeasure) {
        final Measurement basicMeasurement = new BasicMeasurement<V, Q>(
                eventMeasure,
                this.metricDesciption);
        final ProbeMeasurement newMeasurement = new ProbeMeasurement(basicMeasurement,
                this,
                RequestContext.EMPTY_REQUEST_CONTEXT,
                null);
        this.notifyMeasurementSourceListener(newMeasurement);
    }
}
