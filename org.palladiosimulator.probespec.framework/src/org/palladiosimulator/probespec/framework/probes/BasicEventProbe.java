package org.palladiosimulator.probespec.framework.probes;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.palladiosimulator.edp2.models.ExperimentData.BaseMetricDescription;
import org.palladiosimulator.measurementspec.BasicMeasurement;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.requestcontext.RequestContext;

public abstract class BasicEventProbe<EventSourceType, V, Q extends Quantity> extends EventProbe<EventSourceType> {


    public BasicEventProbe(final EventSourceType eventSource, final BaseMetricDescription metric) {
        super(eventSource,metric);
    }

    protected void notify(final Measure<V,Q> eventMeasure) {
        final Measurement newMeasurement = new BasicMeasurement<V, Q>(
                eventMeasure,
                this.metricDesciption,
                this,
                RequestContext.EMPTY_REQUEST_CONTEXT,
                null);
        this.notifyMeasurementSourceListener(newMeasurement);
    }
}
