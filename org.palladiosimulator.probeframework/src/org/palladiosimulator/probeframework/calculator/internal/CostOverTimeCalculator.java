package org.palladiosimulator.probeframework.calculator.internal;

import static org.jscience.economics.money.Currency.EUR;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.COST_OVER_TIME;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.POINT_IN_TIME_METRIC;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.jscience.economics.money.Money;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.measurementframework.BasicMeasurement;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.exceptions.CalculatorException;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Calculator for calculating the cost of resource containers over time.
 * 
 * @author Hendrik Eikerling
 */
public class CostOverTimeCalculator extends UnaryCalculator {

    public CostOverTimeCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        super(COST_OVER_TIME, measuringPoint, probe);
    }

    @Override
    protected MeasuringValue calculate(final List<ProbeMeasurement> probeMeasurements) throws CalculatorException {

        final List<MeasuringValue> result = new ArrayList<MeasuringValue>(2);

        final MetricSetDescription metricSetDescription = (MetricSetDescription) this.getMetricDesciption();

        final Measure<Double, Duration> pointInTime = probeMeasurements.get(0).getMeasureProvider()
                .getMeasureForMetric(POINT_IN_TIME_METRIC);
        final Measure<Double, Money> costPerContainer = probeMeasurements.get(0).getMeasureProvider()
                .getMeasureForMetric(MetricDescriptionConstants.COST_PER_RESOURCE_CONTAINER);

        // TODO Include calculation of cost of all resource containers
        // final Measure<Double, Dimensionless> resourceContainers =
        // probeMeasurements.get(0).getMeasureProvider()
        // .getMeasureForMetric(NUMBER_OF_RESOURCE_CONTAINERS);
        // final double costOfContainers = resourceContainers.doubleValue(ONE) *
        // costPerContainer.doubleValue(EUR);

        final double costOfContainers = costPerContainer.doubleValue(EUR);
        final Measure<Double, Money> costOfContainerMeasure = Measure.valueOf(costOfContainers, EUR);

        final MeasuringValue pointInTimeMeasurement = new BasicMeasurement<Double, Duration>(pointInTime,
                (BaseMetricDescription) ((MetricSetDescription) this.getMetricDesciption()).getSubsumedMetrics()
                        .get(0));
        final MeasuringValue costOfContainerMeasurement = new BasicMeasurement<Double, Money>(costOfContainerMeasure,
                (BaseMetricDescription) ((MetricSetDescription) this.getMetricDesciption()).getSubsumedMetrics()
                        .get(1));

        result.add(pointInTimeMeasurement);
        result.add(costOfContainerMeasurement);

        return new TupleMeasurement(result, metricSetDescription);
    }

}
