/**
 * 
 */
package org.palladiosimulator.probespec.framework.probes;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.palladiosimulator.edp2.models.ExperimentData.BaseMetricDescription;
import org.palladiosimulator.probespec.framework.measurements.BasicMeasurement;
import org.palladiosimulator.probespec.framework.measurements.Measurement;
import org.palladiosimulator.probespec.framework.requestcontext.RequestContext;

/**
 * @author snowball
 *
 */
public abstract class BasicTriggeredProbe<V,Q extends Quantity> extends TriggeredProbe {

    /**
     * 
     */
    public BasicTriggeredProbe(final BaseMetricDescription metric) {
        super(metric);
    }

    @Override
    protected final Measurement doMeasure(final RequestContext measurementContext) {
        return new BasicMeasurement<V, Q>(
                getBasicMeasure(measurementContext),
                this.metricDesciption,
                this,
                measurementContext,
                null);
    }

    protected abstract Measure<V, Q> getBasicMeasure(RequestContext measurementContext);
}