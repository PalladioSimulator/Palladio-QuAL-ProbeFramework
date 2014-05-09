package org.palladiosimulator.probeframework.calculator.internal;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.HOLD_TIME_METRIC;

import java.util.Arrays;
import java.util.List;

import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Calculates a time span representing the hold time as defined by the HOLD_TIME_METRIC. It expects
 * a probe giving the start of holding and a probe giving the end of holding, e.g., in a passive
 * resource pool. The final result is a (start point in time, hold time)-tuple.
 * 
 * TODO Rename to holding time (would be more consistent with the waiting time calculator)? [Lehrig] 
 * 
 * @author Sebastian Lehrig, Steffen Becker
 */
public class HoldTimeCalculator extends TimeSpanCalculator {

    /**
     * Default Constructor.
     * 
     * @param metricName
     *            Name of the metric to be calculated. Dynamically created as it depends on the
     *            referred resource.
     * @param metricDescription
     *            Textual description of the metric.
     * @param probes
     *            The two probes for starting point of hold time and final point of hold time.
     */
    public HoldTimeCalculator(final String metricName,
            final String metricDescription, final List<Probe> probes) {
        super(Calculator.createMetricSetDescription(metricName, metricDescription,
                Arrays.asList(HOLD_TIME_METRIC)), probes);
    }
}
