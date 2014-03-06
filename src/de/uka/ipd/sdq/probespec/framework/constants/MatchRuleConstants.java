package de.uka.ipd.sdq.probespec.framework.constants;

import de.uka.ipd.sdq.probespec.framework.ProbeType;
import de.uka.ipd.sdq.probespec.framework.matching.IMatchRule;
import de.uka.ipd.sdq.probespec.framework.matching.ProbeTypeMatchRule;

public final class MatchRuleConstants {
	
	/** Filters probes for current time measurements, e.g., for calculating response time. */
	public final static IMatchRule CURRENT_TIME_MATCH_RULE = createNewMatchRule(ProbeType.CURRENT_TIME);

	/** Filters probes for execution results, e.g., for occured, simulated failures. */
	public final static IMatchRule EXECUTION_RESULTS_MATCH_RULE = createNewMatchRule(ProbeType.EXECUTION_RESULT);

	/** Filters probes for resource demands, e.g., for CPU demands. */
	public final static IMatchRule RESOURCE_DEMAND_MATCH_RULE = createNewMatchRule(ProbeType.RESOURCE_DEMAND);

	/** Filters probes for resource states, e.g., CPU utilization. */
	public final static IMatchRule RESOURCE_STATE_MATCH_RULE = createNewMatchRule(ProbeType.RESOURCE_STATE);
	
	/**
	 * Creates a new match rule for filtering probes for the given ProbeType.
	 * For example, one could define a matching rule for CURRENT_TIME for
	 * determining the time of an event.
	 * 
	 * @param probeType The ProbeType to filter for.
	 * @return A match rule for the given ProbeType.
	 */
	public static IMatchRule createNewMatchRule(ProbeType probeType) {
		return new ProbeTypeMatchRule(probeType);
	}
	
	private MatchRuleConstants() {
	}
}
