package de.uka.ipd.sdq.probespec.framework.measurements;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;

import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

/**
 * Represents a sample which is taken for a probe.
 * <p>
 * Use this class to store any type of probe sample. Therefore the class parameters V and Q have to
 * be specified which represents the sample type.
 * 
 * @param <V>
 *            denotes the class of the taken sample (Integer, Long, ...)
 * @param <Q>
 *            denotes the measured {@link Quantity}
 * @author pmerkle
 * 
 */
public final class BasicMeasurement<V, Q extends Quantity> extends Measurement {

    /** the measured value and its quantity. */
    private final Measure<V, Q> measure;

    /**
     * Class constructor specifying the measured value, the id and type of the probe.
     * 
     * @param measure
     *            the measured value in conjunction with its {@link Quantity}
     * @param measuredProbe
     * @param modelElementID
     * @param probeID
     *            the id of the probe
     * @param probeType
     *            the type of the probe
     * @see Measure
     * @see ProbeType
     */
    public BasicMeasurement(final Measure<V, Q> measure, final MetricDescription metricDescription,
            final MeasurementSource measuredProbe, final RequestContext requestContext, final String modelElementID) {
        super(requestContext, metricDescription, measuredProbe, modelElementID);
        this.measure = measure;
    }

    /**
     * Returns the encapsulated measured value in conjunction with its measured {@link Quantity}.
     * 
     * @return the measured value and its quantity
     * @see Measure
     */
    final Measure<V, Q> getMeasure() {
        return measure;
    }

    @Override
    public BasicMeasurement<V, Q> getMeasurementForMetric(final MetricDescription metricDesciption) {
        if (!metricDesciption.equals(this.metricDesciption)) {
            return null;
        }
        return this;
    }
}
