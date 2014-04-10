package org.palladiosimulator.probespec.framework.calculator.internal;

import static org.palladiosimulator.probespec.framework.constants.MetricDescriptionConstants.POINT_IN_TIME_METRIC;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;
import org.palladiosimulator.probespec.framework.ProbeSpecContext;
import org.palladiosimulator.probespec.framework.exceptions.CalculatorException;
import org.palladiosimulator.probespec.framework.measurements.BasicMeasurement;
import org.palladiosimulator.probespec.framework.measurements.Measurement;
import org.palladiosimulator.probespec.framework.measurements.MeasurementSet;
import org.palladiosimulator.probespec.framework.probes.Probe;
/**
 * Calculates a time span. It expects two ProbeSets each containing at least a
 * {@link ProbeType#CURRENT_TIME} probe.
 * 
 * @author Faber, Philipp Merkle, Sebastian Lehrig, Steffen Becker
 * 
 */
public abstract class TimeSpanCalculator extends NaryCalculator {

    public TimeSpanCalculator(final ProbeSpecContext ctx, final MetricDescription metricDescriptions, final List<Probe> probes) {
        super(ctx, metricDescriptions, probes);
    }

    /**
     * @see
     * org.palladiosimulator.probespec.framework.calculator.Calculator#calculate
     * (org.palladiosimulator.probespec.framework.measurements.MeasurementSet)
     */
    @Override
    protected Measurement calculate(final List<Measurement> probeMeasurements) throws CalculatorException {
        final List<Measurement> result = new ArrayList<Measurement>(2);

        final Measure<Double, Duration> startTimeMeasure = probeMeasurements.get(0).getMeasureForMetric(POINT_IN_TIME_METRIC);
        final Measure<Double, Duration> endTimeMeasure = probeMeasurements.get(1).getMeasureForMetric(POINT_IN_TIME_METRIC);
        final double timeSpan = endTimeMeasure.doubleValue(SI.SECOND)-startTimeMeasure.doubleValue(SI.SECOND);
        final Measure<Double, Duration> timeSpanMeasure = Measure.valueOf(timeSpan, SI.SECOND);

        final Measurement startTimeMeasurement = probeMeasurements.get(0).getMeasurementForMetric(POINT_IN_TIME_METRIC);
        result.add(startTimeMeasurement);
        final BasicMeasurement<Double,Duration> timeSpanMeasurement = new BasicMeasurement<Double,Duration>(
                timeSpanMeasure,
                ((MetricSetDescription)this.metricDesciption).getSubsumedMetrics().get(1),
                this,
                startTimeMeasurement.getRequestContext(),
                null);
        result.add(timeSpanMeasurement);

        return new MeasurementSet(result, (MetricSetDescription)this.metricDesciption, this);
    }
}