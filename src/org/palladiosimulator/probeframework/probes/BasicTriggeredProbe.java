/**
 * 
 */
package org.palladiosimulator.probeframework.probes;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.palladiosimulator.measurementspec.BasicMeasurement;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;

/**
 * @author snowball
 * 
 */
public abstract class BasicTriggeredProbe<V, Q extends Quantity> extends TriggeredProbe {

    /**
     * 
     */
    public BasicTriggeredProbe(final BaseMetricDescription metric) {
        super(metric);
    }

    @Override
    protected final ProbeMeasurement doMeasure(final RequestContext measurementContext) {
        final Measurement resultMeasurement = new BasicMeasurement<V, Q>(getBasicMeasure(measurementContext),
                this.getMetricDesciption());
        return new ProbeMeasurement(resultMeasurement, this, measurementContext, null);
    }

    protected abstract Measure<V, Q> getBasicMeasure(RequestContext measurementContext);
}
