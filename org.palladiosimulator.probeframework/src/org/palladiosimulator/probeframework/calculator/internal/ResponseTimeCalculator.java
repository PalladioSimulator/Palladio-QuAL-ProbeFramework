package org.palladiosimulator.probeframework.calculator.internal;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.RESPONSE_TIME_METRIC;

import java.util.Arrays;
import java.util.List;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Calculates a time span representing the response time as defined by the RESPONSE_TIME_METRIC. It
 * expects a probe giving the start and a probe giving the end point in time of an operation call.
 * 
 * @author Sebastian Lehrig, Steffen Becker
 * @see TimeSpanCalculator
 */
public class ResponseTimeCalculator extends TimeSpanCalculator {

    /**
     * Default Constructor.
     * 
     * @param context
     *            ProbeFrameworkContext as needed by the superclass.
     * @param metricName
     *            Name of the metric to be calculated. Dynamically created as it depends on the
     *            referred operation call.
     * @param metricDescription
     *            Textual description of the metric.
     * @param probes
     *            The two probes for starting point of the operation call and final point of the
     *            operation call.
     */
    public ResponseTimeCalculator(final ProbeFrameworkContext context, final String metricName,
            final String metricDescription, final List<Probe> probes) {
        super(context, Calculator.createMetricSetDescription(metricName, metricDescription,
                Arrays.asList(RESPONSE_TIME_METRIC)), probes);
    }
}
