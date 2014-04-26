package org.palladiosimulator.probeframework.calculator.internal;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.HOLD_TIME_METRIC;

import java.util.Arrays;
import java.util.List;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Calculates a time span representing the hold time.
 * 
 * @author Philipp Merkle, Sebastian Lehrig, Steffen Becker
 */
public class HoldTimeCalculator extends TimeSpanCalculator {

    /**
     * Default Constructor.
     * 
     * @param ctx
     *            the {@link ProbeFrameworkContext}
     * @param startHoldProbeSetID
     *            references the ProbeSet which represents the starting point for the hold time
     *            measurement
     * @param stopHoldProbeSetID
     *            references the ProbeSet which represents the final point for the hold time
     *            measurement
     */
    public HoldTimeCalculator(final ProbeFrameworkContext ctx, final String metricName, final String metricDescription, final List<Probe> probes) {
        super(ctx, Calculator.createMetricSetDescription(metricName, metricDescription, Arrays.asList(HOLD_TIME_METRIC)), probes);
    }
}
