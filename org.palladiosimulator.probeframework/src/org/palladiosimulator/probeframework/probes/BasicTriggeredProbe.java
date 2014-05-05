package org.palladiosimulator.probeframework.probes;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.palladiosimulator.measurementspec.BasicMeasurement;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;

/**
 * Implements the <code>doMeasure</code> method by returning a <code>BasicMeasurement</code>, i.e.,
 * a measurement for a <code>BaseMetricDescription</code>. Therefore, basic triggered probes are
 * constructed by passing an appropriate base metric description as a parameter that is used to
 * construct basic measurements.
 * 
 * For determining the measurement itself, basic triggered probes invoke the template method
 * <code>getBasicMeasure</code>. The values of the returned measure are of type V and their quantity
 * is given in Q.
 * 
 * @see Measure
 * 
 * @author Steffen Becker, Sebastian Lehrig
 * 
 * @param <V>
 *            The value type of the basic measure.
 * @param <Q>
 *            The quantity type of the basic measure.
 */
public abstract class BasicTriggeredProbe<V, Q extends Quantity> extends TriggeredProbe {

    /**
     * Default constructor. Restricts general metric descriptions to BaseMetricDescriptions (central
     * characteristic of this type of triggered probe).
     * 
     * @param metricDesciption
     *            A BaseMetricDescription as needed by the superclass.
     */
    public BasicTriggeredProbe(final BaseMetricDescription metricDesciption) {
        super(metricDesciption);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final ProbeMeasurement doMeasure(final RequestContext measurementContext) {
        final Measurement resultMeasurement = new BasicMeasurement<V, Q>(getBasicMeasure(measurementContext),
                this.getMetricDesciption());
        return new ProbeMeasurement(resultMeasurement, this, measurementContext, null);
    }

    /**
     * Template method for taking the desired measure in a given request context.
     * 
     * @param measurementContext
     *            The measurement context for this probe.
     * @return The taken measure.
     */
    protected abstract Measure<V, Q> getBasicMeasure(RequestContext measurementContext);
}
