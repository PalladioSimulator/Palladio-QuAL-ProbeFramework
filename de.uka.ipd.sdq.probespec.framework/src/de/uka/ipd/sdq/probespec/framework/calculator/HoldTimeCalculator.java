package de.uka.ipd.sdq.probespec.framework.calculator;

import static de.uka.ipd.sdq.probespec.framework.constants.MeasurementMetricConstants.HOLD_TIME_METRIC;

import java.util.Arrays;

import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;

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
     *            the {@link ProbeSpecContext}
     * @param startHoldProbeSetID
     *            references the ProbeSet which represents the starting point for the hold time
     *            measurement
     * @param stopHoldProbeSetID
     *            references the ProbeSet which represents the final point for the hold time
     *            measurement
     */
    public HoldTimeCalculator(ProbeSpecContext ctx, Integer startHoldProbeSetID, Integer stopHoldProbeSetID) {
        super(ctx, Arrays.asList(HOLD_TIME_METRIC), startHoldProbeSetID, stopHoldProbeSetID);
    }
}
