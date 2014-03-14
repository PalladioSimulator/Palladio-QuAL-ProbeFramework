package de.uka.ipd.sdq.probespec.framework.calculator;

import static de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants.RESPONSE_TIME_METRIC;

import java.util.Arrays;

import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.ProbeType;

/**
 * Calculates a time span representing the response time. It expects two
 * ProbeSets each containing at least a {@link ProbeType#CURRENT_TIME} probe.
 * 
 * @author Faber, Philipp Merkle, Sebastian Lehrig, Steffen Becker
 * @see BinaryCalculator
 * @see Calculator
 */
public class ResponseTimeCalculator extends TimeSpanCalculator {
	
	/**
     * Default Constructor.
     * 
     * @param ctx
     *            the {@link ProbeSpecContext}
     * @param startProbeSetID
     *            ID of the start probe set element from the model
     * @param endProbeSetID
     *            ID of the end probe set element from the model
     */
    public ResponseTimeCalculator(ProbeSpecContext ctx, Integer startProbeSetID, Integer endProbeSetID) {
        super(ctx, Arrays.asList(RESPONSE_TIME_METRIC), startProbeSetID, endProbeSetID);
    }
}
