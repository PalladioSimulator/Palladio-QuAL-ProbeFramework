package org.palladiosimulator.probeframework.calculator.internal;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.WAITING_TIME_METRIC;

import java.util.Arrays;
import java.util.List;

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
     * @param metricName
     *            Name of the metric to be calculated. Dynamically created as it depends on the
     *            referred resource.
     * @param metricDescription
     *            Textual description of the metric.
     * @param probes
     *            The two probes for starting point of waiting time and final point of waiting time.
     */
    public WaitingTimeCalculator(final String metricName,
            final String metricDescription, final List<Probe> probes) {
        super(Calculator.createMetricSetDescription(metricName, metricDescription,
                Arrays.asList(WAITING_TIME_METRIC)), probes);
    }
}
