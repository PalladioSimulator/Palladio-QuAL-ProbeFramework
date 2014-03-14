package de.uka.ipd.sdq.probespec.framework.calculator;

import static de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants.WAITING_TIME_METRIC;

import java.util.Arrays;

import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;

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
    public WaitingTimeCalculator(ProbeSpecContext ctx, Integer startWaitingProbeSetID, Integer stopWaitingProbeSetID) {
        super(ctx, Arrays.asList(WAITING_TIME_METRIC), startWaitingProbeSetID, stopWaitingProbeSetID);
    }
}
