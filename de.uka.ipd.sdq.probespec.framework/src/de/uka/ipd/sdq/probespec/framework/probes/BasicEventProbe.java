package de.uka.ipd.sdq.probespec.framework.probes;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.palladiosimulator.edp2.models.ExperimentData.BaseMetricDescription;

import de.uka.ipd.sdq.probespec.framework.measurements.BasicMeasurement;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

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
