package org.palladiosimulator.probeframework.calculator;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.AGGREGATED_COST_OVER_TIME;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.COST_OVER_TIME;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.HOLDING_TIME_METRIC_TUPLE;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.NUMBER_OF_RESOURCE_CONTAINERS_OVER_TIME;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.OPTIMISATION_TIME_METRIC_TUPLE;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.OVERALL_STATE_OF_ACTIVE_RESOURCE_METRIC;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.OVERALL_STATE_OF_PASSIVE_RESOURCE_METRIC;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC_TUPLE;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.RESOURCE_DEMAND_METRIC_TUPLE;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.STATE_OF_PASSIVE_RESOURCE_METRIC_TUPLE;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.WAITING_TIME_METRIC_TUPLE;

import java.util.List;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.probeframework.probes.ProbeConfigurations;

/**
 * This interface provides default implementations to adapt the legacy
 * ICalculatorFactory to the new interface IGenericCalculatorFactory.
 * 
 * When developing new code please use <code>IGenericCalculatorFactory</code>
 * directly.
 *
 * @author Sebastian Krach
 */
@SuppressWarnings("deprecation")
public interface ICalculatorFactoryLegacyAdapter extends ICalculatorFactory, IGenericCalculatorFactory {
    /**
     * This factory method allows to create an adapter which delegates the legacy
     * factory methods to the extensible one.
     * 
     * @param delegate the factory which adheres to the new style.
     * @return the delegating adapter
     */
    public static ICalculatorFactoryLegacyAdapter createDelegatingAdapter(final IGenericCalculatorFactory delegate) {
        return delegate::buildCalculator;
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildResponseTimeCalculator(final MeasuringPoint measuringPoint, final List<Probe> probes) {
        return buildCalculator(RESPONSE_TIME_METRIC_TUPLE, measuringPoint,
                ProbeConfigurations.createStartStopProbeConfiguration(probes.get(0), probes.get(1)));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildDemandBasedWaitingTimeCalculator(final MeasuringPoint measuringPoint,
            final List<Probe> probes) {
        /**
         * This kind of calculator cannot be mapped to the new interface as it uses the
         * same metric description as the standard waiting time calculator. Since the
         * metric is the same, at least in SimuLizar there is no way of selecting this
         * calculator. Since calculators were already cached based on their metric
         * description both methods were already ambiguous. If the differentiation is
         * required, an additional metric should be defined. [Sebastian Krach]
         */
        return buildCalculator(WAITING_TIME_METRIC_TUPLE, measuringPoint,
                ProbeConfigurations.createStartStopProbeConfiguration(probes.get(0), probes.get(1)));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildWaitingTimeCalculator(final MeasuringPoint measuringPoint, final List<Probe> probes) {
        return buildCalculator(WAITING_TIME_METRIC_TUPLE, measuringPoint,
                ProbeConfigurations.createStartStopProbeConfiguration(probes.get(0), probes.get(1)));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildHoldingTimeCalculator(final MeasuringPoint measuringPoint, final List<Probe> probes) {
        return buildCalculator(HOLDING_TIME_METRIC_TUPLE, measuringPoint,
                ProbeConfigurations.createStartStopProbeConfiguration(probes.get(0), probes.get(1)));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildStateOfActiveResourceCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return buildCalculator(STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE, measuringPoint,
                ProbeConfigurations.createSingularProbeConfiguration(probe));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildOverallStateOfActiveResourceCalculator(final MeasuringPoint measuringPoint,
            final Probe probe) {
        return buildCalculator(OVERALL_STATE_OF_ACTIVE_RESOURCE_METRIC, measuringPoint,
                ProbeConfigurations.createSingularProbeConfiguration(probe));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildStateOfPassiveResourceCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return buildCalculator(STATE_OF_PASSIVE_RESOURCE_METRIC_TUPLE, measuringPoint,
                ProbeConfigurations.createSingularProbeConfiguration(probe));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildOverallStateOfPassiveResourceCalculator(final MeasuringPoint measuringPoint,
            final Probe probe) {
        return buildCalculator(OVERALL_STATE_OF_PASSIVE_RESOURCE_METRIC, measuringPoint,
                ProbeConfigurations.createSingularProbeConfiguration(probe));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildResourceDemandCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return buildCalculator(RESOURCE_DEMAND_METRIC_TUPLE, measuringPoint,
                ProbeConfigurations.createSingularProbeConfiguration(probe));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildExecutionResultCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return buildCalculator(probe.getMetricDesciption(), measuringPoint,
                ProbeConfigurations.createSingularProbeConfiguration(probe));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildNumberOfResourceContainersCalculator(final MeasuringPoint measuringPoint,
            final Probe probe) {
        return buildCalculator(NUMBER_OF_RESOURCE_CONTAINERS_OVER_TIME, measuringPoint,
                ProbeConfigurations.createSingularProbeConfiguration(probe));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildReconfigurationTimeCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return buildCalculator(RECONFIGURATION_TIME_METRIC_TUPLE, measuringPoint,
                ProbeConfigurations.createSingularProbeConfiguration(probe));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildCostOverTimeCalculator(MeasuringPoint measuringPoint, final Probe probe) {
        return buildCalculator(COST_OVER_TIME, measuringPoint,
                ProbeConfigurations.createSingularProbeConfiguration(probe));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildAggregatedCostOverTimeCalculator(MeasuringPoint measuringPoint, final Probe probe) {
        return buildCalculator(AGGREGATED_COST_OVER_TIME, measuringPoint,
                ProbeConfigurations.createSingularProbeConfiguration(probe));
    }

    /**
     * {@inheritDoc}
     */
    default Calculator buildOptimisationTimeCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        return buildCalculator(OPTIMISATION_TIME_METRIC_TUPLE, measuringPoint,
                ProbeConfigurations.createSingularProbeConfiguration(probe));
    }
}