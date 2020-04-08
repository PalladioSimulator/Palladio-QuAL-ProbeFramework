package org.palladiosimulator.probeframework.calculator;

import java.util.Collection;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;

public interface ICalculatorRegistryAccess {

    /**
     * Gets the currently registered calculators.
     *
     * @return An <b>unmodifiable</b> collection containing the currently registered
     *         calculators.
     */
    public Collection<Calculator> getRegisteredCalculators();

    /**
     * Convenience method to get all calculators associated with the given measuring
     * point.
     *
     * @param measuringPoint A {@link MeasuringPoint} instance which is associated
     *                       with a calculator.
     * @return An UNMODIFIABLE @{@link Collection} of {@link Calculator}s which are
     *         associated with the given measuring point, might be empty but never
     *         {@code null}.
     * @see #getCalculatorByMeasuringPointAndMetricDescription(MeasuringPoint,
     *      MetricDescription)
     */
    public Collection<Calculator> getCalculatorsForMeasuringPoint(MeasuringPoint measuringPoint);

    /**
     * Convenience method to get the calculator that is associated with the given
     * measuring point and metric.<br>
     * <br>
     * <b>Important note</b>: If a base metric such as 'Response Time' is passed
     * here, and an 'Response Time Tuple' calculator is available at the given
     * measuring point it is <b>NOT</b> found by the current implementation of this
     * method, since these are different metric (base metric vs. metric set)!. <br>
     * In such a case, better use
     * {@link #getCalculatorsForMeasuringPoint(MeasuringPoint)} and filter the
     * resulting collection manually for the desired base metric.
     *
     * @param mp     A {@link MeasuringPoint} instance which is associated with a
     *               calculator.
     * @param metric A {@link MetricDescription} denoting the metric the calculator
     *               is accepting.
     * @return A {@link Calculator} instance associated with the given measuring
     *         point and metric, or {@code null} if no matching {@link Calculator}
     *         is found.
     * @see #getCalculatorsForMeasuringPoint(MeasuringPoint)
     */
    public Calculator getCalculatorByMeasuringPointAndMetricDescription(final MeasuringPoint mp,
            final MetricDescription metric);

}
