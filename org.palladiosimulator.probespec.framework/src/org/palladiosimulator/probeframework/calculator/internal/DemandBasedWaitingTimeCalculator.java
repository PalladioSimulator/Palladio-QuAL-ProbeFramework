package org.palladiosimulator.probeframework.calculator.internal;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;
import org.palladiosimulator.measurementspec.BasicMeasurement;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementSet;
import org.palladiosimulator.metricspec.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Calculates the waiting time for resources in environments where the stop of
 * the waiting period cannot be observed directly. Rather the following values
 * (respectively events) should be observable.
 * <ul>
 * <li><code>start</code> - "request for processing"-event</li>
 * <li><code>stop</code> - "end of processing"-event (Notice: This is different
 * from the waiting period stop)</li>
 * <li><code>demand</code> - the demanded time</li>
 * </ul>
 * The waiting time results from calculating
 * <code>(stop - start) - demand </code>.
 * 
 * @author pmerkle, Sebastian Lehrig, Steffen Becker
 * 
 */
public class DemandBasedWaitingTimeCalculator extends WaitingTimeCalculator {

    public DemandBasedWaitingTimeCalculator(final ProbeFrameworkContext ctx, final String metricName, final String metricDescription, final List<Probe> probes) {
        super(ctx, metricName, metricDescription, probes);
    }

    /**
     * @see
     * org.palladiosimulator.probeframework.calculator.Calculator#calculate
     * (org.palladiosimulator.measurementspec.MeasurementSet)
     */
    @Override
    protected Measurement calculate(final List<Measurement> probeSetSamples) {
        // raw measures
        final Measure<Double, Duration> startTimeMeasure = probeSetSamples.get(0).getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        final Measure<Double, Duration> demandMeasure = probeSetSamples.get(0).getMeasureForMetric(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC);
        final Measure<Double, Duration> endTimeMeasure = probeSetSamples.get(1).getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);

        // time span
        final double timeSpan = endTimeMeasure.doubleValue(SI.SECOND)-startTimeMeasure.doubleValue(SI.SECOND);

        // waiting time
        // TODO Check whether demands can be used as seconds. Note that
        //      the demand metric is a natural number metric and no time metric.
        double waitingTime = timeSpan - demandMeasure.doubleValue(SI.SECOND);
        if (waitingTime < 0) { // necessary due to double precision errors
            waitingTime = 0;
        }
        final Measure<Double, Duration> waitingTimeMeasure = Measure.valueOf(waitingTime, SI.SECOND);

        final List<Measurement> result = new ArrayList<Measurement>(2);
        final BasicMeasurement<Double,Duration> waitingTimeMeasurement = new BasicMeasurement<Double,Duration>(
                waitingTimeMeasure,
                MetricDescriptionConstants.WAITING_TIME_METRIC,
                this,
                probeSetSamples.get(0).getRequestContext(),
                null);

        result.add(probeSetSamples.get(1).getMeasurementForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC));
        result.add(waitingTimeMeasurement);

        return new MeasurementSet(result, (MetricSetDescription) this.metricDesciption, this);
    }

}
