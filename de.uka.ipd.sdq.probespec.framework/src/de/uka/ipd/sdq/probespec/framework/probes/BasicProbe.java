/**
 * 
 */
package de.uka.ipd.sdq.probespec.framework.probes;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.palladiosimulator.edp2.models.ExperimentData.BaseMetricDescription;

import de.uka.ipd.sdq.probespec.framework.measurements.BasicMeasurement;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

/**
 * @author snowball
 *
 */
public abstract class BasicProbe<V,Q extends Quantity> extends Probe {

    /**
     * 
     */
    public BasicProbe(final BaseMetricDescription metric) {
        super(metric);
    }

    @Override
    protected final Measurement doMeasure(final RequestContext measurementContext) {
        final BasicMeasurement<V, Q> result = new BasicMeasurement<V, Q>(
                getBasicMeasure(measurementContext),
                this.metricDesciption,
                this,
                measurementContext,
                null);
        return result;
    }

    protected abstract Measure<V, Q> getBasicMeasure(RequestContext measurementContext);

}
