package org.palladiosimulator.probeframework.calculator.internal;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.WAITING_TIME_METRIC;

import java.util.Arrays;
import java.util.List;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Calculates a time span representing the waiting time as defined by the WAITING_TIME_METRIC. It
 * expects a probe giving the start of waiting and a probe giving the end of waiting, e.g., at a
 * passive resource pool.
 * 
 * @author Sebastian Lehrig, Steffen Becker
 * @see TimeSpanCalculator
 */
public class WaitingTimeCalculator extends TimeSpanCalculator {

    /**
     * Default Constructor.
     * 
     * @param ctx
     *            the {@link ProbeFrameworkContext}
     * @param startWaitingProbeSetID
     *            references the ProbeSet which represents the starting point for the waiting time
     *            measurement
     * @param stopWaitingProbeSetID
     *            references the ProbeSet which represents the final point for the waiting time
     *            measurement
     */
    public WaitingTimeCalculator(final ProbeFrameworkContext ctx, final String metricName,
            final String metricDescription, final List<Probe> probes) {
        super(ctx, Calculator.createMetricSetDescription(metricName, metricDescription,
                Arrays.asList(WAITING_TIME_METRIC)), probes);
    }
}
