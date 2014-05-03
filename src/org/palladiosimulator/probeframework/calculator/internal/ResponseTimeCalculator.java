package org.palladiosimulator.probeframework.calculator.internal;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.RESPONSE_TIME_METRIC;

import java.util.Arrays;
import java.util.List;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Calculates a time span representing the response time as defined by the
 * RESPONSE_TIME_METRIC. It expects a probe giving the start and a probe giving
 * the end point in time of an operation call.
 * 
 * @author Sebastian Lehrig, Steffen Becker
 * @see TimeSpanCalculator
 */
public class ResponseTimeCalculator extends TimeSpanCalculator {

	/**
	 * Default Constructor.
	 * 
	 * @param ctx
	 *            the {@link ProbeFrameworkContext}
	 * @param startProbeSetID
	 *            ID of the start probe set element from the model
	 * @param endProbeSetID
	 *            ID of the end probe set element from the model
	 */
	public ResponseTimeCalculator(final ProbeFrameworkContext ctx,
			final String metricName, final String metricDescription,
			final List<Probe> probes) {
		super(ctx, Calculator.createMetricSetDescription(metricName,
				metricDescription, Arrays.asList(RESPONSE_TIME_METRIC)), probes);
	}
}
