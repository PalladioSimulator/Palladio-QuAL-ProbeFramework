package org.palladiosimulator.probeframework.calculator;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.AGGREGATED_COST_OVER_TIME;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.COST_OVER_TIME;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.NUMBER_OF_RESOURCE_CONTAINERS_OVER_TIME;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.OPTIMISATION_TIME_METRIC_TUPLE;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.OVERALL_STATE_OF_PASSIVE_RESOURCE_METRIC;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC_TUPLE;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.RESOURCE_DEMAND_METRIC_TUPLE;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.STATE_OF_PASSIVE_RESOURCE_METRIC_TUPLE;

import java.util.List;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.calculator.internal.DemandBasedWaitingTimeCalculator;
import org.palladiosimulator.probeframework.calculator.internal.HoldingTimeCalculator;
import org.palladiosimulator.probeframework.calculator.internal.IdentityCalculator;
import org.palladiosimulator.probeframework.calculator.internal.ResponseTimeCalculator;
import org.palladiosimulator.probeframework.calculator.internal.WaitingTimeCalculator;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Default implementation of ICalculatorFactory. Directly creates calculators, with minimal overhead
 * for other concerns. Therefore, the factory methods only check for valid probe inputs (checks for
 * non-null and correct number of probes).
 *
 * @see ICalculatorFactory
 *
 * @author Steffen Becker, Sebastian Lehrig, Matthias Becker
 */
@Deprecated
public class DefaultCalculatorFactory implements ICalculatorFactory {

    /**
     * Default constructor. Nothing special.
     */
    public DefaultCalculatorFactory() {
        super();
    }

    /**
     * Ensures that the given list of probes contains exactly two probes. Otherwise, it throws an
     * IllegalArgumentException.
     *
     * TODO Unite with other method (ensureValidProbe)
     *
     * @param probes
     *            The list of probes
     */
    private void ensureTwoProbes(final List<Probe> probes) {
        if (probes == null || probes.size() != 2 || probes.get(0) == null || probes.get(1) == null) {
            throw new IllegalArgumentException("You have to provide exactly two probes for this calcultor.");
        }
    }

    /**
     * Ensures that the given probe is a valid probe, i.e., not null. Otherwise, it throws an
     * IllegalArgumentException.
     *
     * @param probe
     *            The probe
     */
    private void ensureValidProbe(final Probe probe) {
        if (probe == null) {
            throw new IllegalArgumentException("You have to provide exactly one valid probes for this calcultor.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildResponseTimeCalculator(final MeasuringPoint measuringPoint, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new ResponseTimeCalculator(measuringPoint, probes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildDemandBasedWaitingTimeCalculator(final MeasuringPoint measuringPoint,
            final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new DemandBasedWaitingTimeCalculator(measuringPoint, probes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildWaitingTimeCalculator(final MeasuringPoint measuringPoint, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new WaitingTimeCalculator(measuringPoint, probes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildHoldingTimeCalculator(final MeasuringPoint measuringPoint, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new HoldingTimeCalculator(measuringPoint, probes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildStateOfActiveResourceCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE, measuringPoint, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildOverallStateOfActiveResourceCalculator(final MeasuringPoint measuringPoint,
            final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE, measuringPoint, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildStateOfPassiveResourceCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(STATE_OF_PASSIVE_RESOURCE_METRIC_TUPLE, measuringPoint, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildOverallStateOfPassiveResourceCalculator(final MeasuringPoint measuringPoint,
            final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(OVERALL_STATE_OF_PASSIVE_RESOURCE_METRIC, measuringPoint, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildResourceDemandCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(RESOURCE_DEMAND_METRIC_TUPLE, measuringPoint, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildExecutionResultCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(measuringPoint, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildNumberOfResourceContainersCalculator(final MeasuringPoint measuringPoint,
            final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(NUMBER_OF_RESOURCE_CONTAINERS_OVER_TIME, measuringPoint, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildReconfigurationTimeCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(RECONFIGURATION_TIME_METRIC_TUPLE, measuringPoint, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildCostOverTimeCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(COST_OVER_TIME, measuringPoint, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildAggregatedCostOverTimeCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(AGGREGATED_COST_OVER_TIME, measuringPoint, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildOptimisationTimeCalculator(final MeasuringPoint measuringPoint, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(OPTIMISATION_TIME_METRIC_TUPLE, measuringPoint, probe);
    }
}
