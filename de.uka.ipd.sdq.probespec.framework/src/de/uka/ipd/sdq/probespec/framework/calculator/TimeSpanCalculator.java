package de.uka.ipd.sdq.probespec.framework.calculator;

import static de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants.POINT_IN_TIME_METRIC;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;

import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.exceptions.CalculatorException;
import de.uka.ipd.sdq.probespec.framework.measurements.BasicMeasurement;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet;
import de.uka.ipd.sdq.probespec.framework.probes.Probe;
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
     * de.uka.ipd.sdq.probespec.framework.calculator.Calculator#calculate
     * (de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet)
     */
    @Override
    protected Measurement calculate(final List<Measurement> probeMeasurements) throws CalculatorException {
        final List<Measurement> result = new ArrayList<Measurement>(2);

        final Measure<Double, Duration> startTimeMeasure = obtainMeasure(probeMeasurements.get(0), POINT_IN_TIME_METRIC);
        final Measure<Double, Duration> endTimeMeasure = obtainMeasure(probeMeasurements.get(1), POINT_IN_TIME_METRIC);
        final double timeSpan = endTimeMeasure.doubleValue(SI.SECOND)-startTimeMeasure.doubleValue(SI.SECOND);
        final Measure<Double, Duration> timeSpanMeasure = Measure.valueOf(timeSpan, SI.SECOND);

        final Measurement startTimeMeasurement = obtainMeasurement(probeMeasurements.get(0),POINT_IN_TIME_METRIC);
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
