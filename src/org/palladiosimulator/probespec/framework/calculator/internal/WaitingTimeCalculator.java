package org.palladiosimulator.probespec.framework.calculator.internal;

import static org.palladiosimulator.metricspec.MetricDescriptionConstants.WAITING_TIME_METRIC;

import java.util.Arrays;
import java.util.List;

import org.palladiosimulator.probespec.framework.ProbeSpecContext;
import org.palladiosimulator.probespec.framework.calculator.Calculator;
import org.palladiosimulator.probespec.framework.probes.Probe;

/**
 * Calculates a time span representing the waiting time.
 * 
 * @author Faber, Philipp Merkle, Sebastian Lehrig, Steffen Becker
 * @see UnaryCalculator
 * @see Calculator
 */
public class WaitingTimeCalculator extends TimeSpanCalculator {

    /**
     * Default Constructor.
     * 
     * @param ctx
     *            the {@link ProbeSpecContext}
     * @param startWaitingProbeSetID
     *            references the ProbeSet which represents the starting point
     *            for the waiting time measurement
     * @param stopWaitingProbeSetID
     *            references the ProbeSet which represents the final point for
     *            the waiting time measurement
     */
    public WaitingTimeCalculator(final ProbeSpecContext ctx, final String metricName, final String metricDescription, final List<Probe> probes) {
        super(ctx, Calculator.createMetricSetDescription(metricName, metricDescription, Arrays.asList(WAITING_TIME_METRIC)), probes);
    }
}
