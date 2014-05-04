package org.palladiosimulator.probeframework.calculator.internal;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.POINT_IN_TIME_METRIC;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.measurementspec.BasicMeasurement;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementTupple;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.exceptions.CalculatorException;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Time span calculators calculates a time span. These calculators expect two probes, each providing
 * a POINT_IN_TIME_METRIC. Subsequently, they calculate the time span by subtracting the second
 * point in time from the first point in time.
 * 
 * @author Sebastian Lehrig, Steffen Becker
 * 
 */
public abstract class TimeSpanCalculator extends NaryCalculator {

    public TimeSpanCalculator(final ProbeFrameworkContext ctx, final MetricDescription metricDescriptions,
            final List<Probe> probes) {
        super(ctx, metricDescriptions, probes);
    }

    /**
     * @see org.palladiosimulator.probeframework.calculator.Calculator#calculate
     *      (org.palladiosimulator.measurementspec.MeasurementTupple)
     */
    @Override
    protected Measurement calculate(final List<ProbeMeasurement> probeMeasurements) throws CalculatorException {
        final List<Measurement> result = new ArrayList<Measurement>(2);

        final Measure<Double, Duration> startTimeMeasure = probeMeasurements.get(0).getMeasurement()
                .getMeasureForMetric(POINT_IN_TIME_METRIC);
        final Measure<Double, Duration> endTimeMeasure = probeMeasurements.get(1).getMeasurement()
                .getMeasureForMetric(POINT_IN_TIME_METRIC);
        final double timeSpan = endTimeMeasure.doubleValue(SI.SECOND) - startTimeMeasure.doubleValue(SI.SECOND);
        final Measure<Double, Duration> timeSpanMeasure = Measure.valueOf(timeSpan, SI.SECOND);

        final Measurement startTimeMeasurement = probeMeasurements.get(0).getMeasurement()
                .getMeasurementForMetric(POINT_IN_TIME_METRIC);
        result.add(startTimeMeasurement);
        final BasicMeasurement<Double, Duration> timeSpanMeasurement = new BasicMeasurement<Double, Duration>(
                timeSpanMeasure, ((MetricSetDescription) this.getMetricDesciption()).getSubsumedMetrics().get(1));
        result.add(timeSpanMeasurement);

        return new MeasurementTupple(result, (MetricSetDescription) this.getMetricDesciption());
    }
}
