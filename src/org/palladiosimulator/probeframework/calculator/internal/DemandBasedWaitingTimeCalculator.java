package org.palladiosimulator.probeframework.calculator.internal;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.measurementspec.BasicMeasurement;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementTupple;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Calculates the waiting time for resources in environments where the stop of the waiting period
 * cannot be observed directly. Rather the following values (respectively events) should be
 * observable.
 * <ul>
 * <li><code>start</code> - "request for processing"-event</li>
 * <li><code>stop</code> - "end of processing"-event (Notice: This is different from the waiting
 * period stop)</li>
 * <li><code>demand</code> - the demanded time</li>
 * </ul>
 * The waiting time results from calculating <code>(stop - start) - demand </code>.
 * 
 * @author pmerkle, Sebastian Lehrig, Steffen Becker
 * 
 */
public class DemandBasedWaitingTimeCalculator extends WaitingTimeCalculator {

    /**
     * Default constructor.
     * 
     * @param context
     *            ProbeFrameworkContext as needed by the superclass.
     * @param metricName
     *            Metric name as needed by the superclass.
     * @param metricDescription
     *            Textual metric description as needed by the superclass.
     * @param probes
     *            Probes as needed by the superclass.
     */
    public DemandBasedWaitingTimeCalculator(final ProbeFrameworkContext context, final String metricName,
            final String metricDescription, final List<Probe> probes) {
        super(context, metricName, metricDescription, probes);
    }

    /**
     * Overrides the inherited calculate method because the latter assumed that the stop of the
     * waiting period can directly be obtained. The calculation functions as described in the header
     * of this class.
     * 
     * @param probeMeasurements
     *            The probe measurements used for the calculation.
     * @return The calculated waiting time measurement.
     * @see org.palladiosimulator.probeframework.calculator.Calculator#calculate
     */
    @Override
    protected Measurement calculate(final List<ProbeMeasurement> probeMeasurements) {
        // raw measures
        final Measure<Double, Duration> startTimeMeasure = probeMeasurements.get(0).getMeasurement()
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        final Measure<Double, Duration> demandMeasure = probeMeasurements.get(0).getMeasurement()
                .getMeasureForMetric(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC);
        final Measure<Double, Duration> endTimeMeasure = probeMeasurements.get(1).getMeasurement()
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);

        // time span
        final double timeSpan = endTimeMeasure.doubleValue(SI.SECOND) - startTimeMeasure.doubleValue(SI.SECOND);

        // waiting time
        // TODO Check whether demands can be used as seconds. Note that
        // the demand metric is a natural number metric and no time metric.
        double waitingTime = timeSpan - demandMeasure.doubleValue(SI.SECOND);
        if (waitingTime < 0) { // necessary due to double precision errors
            waitingTime = 0;
        }
        final Measure<Double, Duration> waitingTimeMeasure = Measure.valueOf(waitingTime, SI.SECOND);

        final List<Measurement> result = new ArrayList<Measurement>(2);
        final BasicMeasurement<Double, Duration> waitingTimeMeasurement = new BasicMeasurement<Double, Duration>(
                waitingTimeMeasure, MetricDescriptionConstants.WAITING_TIME_METRIC);

        result.add(probeMeasurements.get(1).getMeasurement()
                .getMeasurementForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC));
        result.add(waitingTimeMeasurement);

        return new MeasurementTupple(result, (MetricSetDescription) this.getMetricDesciption());
    }

}
