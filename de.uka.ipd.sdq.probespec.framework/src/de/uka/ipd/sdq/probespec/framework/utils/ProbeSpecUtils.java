package de.uka.ipd.sdq.probespec.framework.utils;

import java.util.ArrayList;
import java.util.List;

import javax.measure.quantity.Quantity;

import de.uka.ipd.sdq.probespec.framework.ProbeSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.RequestContext;
import de.uka.ipd.sdq.probespec.framework.matching.IMatchRule;

public class ProbeSpecUtils {

	public static ProbeSetSample buildProbeSetSample(
			ProbeSample<?, ? extends Quantity> sample,
			RequestContext requestContextID, Integer probeSetId) {
		List<ProbeSample<?, ? extends Quantity>> probeSampleVector = new ArrayList<ProbeSample<?, ? extends Quantity>>();
		probeSampleVector.add(sample);

		return buildProbeSetSample(probeSampleVector, requestContextID, probeSetId);
	}
	
	public static ProbeSetSample buildProbeSetSample(List<ProbeSample<?, ? extends Quantity>> samples,
			RequestContext requestContextID, Integer probeSetId) {

		ProbeSetSample pss = new ProbeSetSample(samples,
				requestContextID, probeSetId);
		return pss;
	}
	
	/**
	 * Returns the encapsulated probe samples satisfying the specified rule set.
	 * 
	 * @param matchingRules
	 *            the rule set
	 * @return
	 * @see ProbeSample
	 */
	public static List<ProbeSample<?, ? extends Quantity>> getProbeSamples(List<ProbeSample<?, ? extends Quantity>> probeSamples, 
			IMatchRule[] matchingRules) {
		List<ProbeSample<?, ? extends Quantity>> res = new ArrayList<ProbeSample<?, ? extends Quantity>>();

		for (ProbeSample<?, ? extends Quantity> sample : probeSamples) {
			boolean match = true;
			for (IMatchRule rule : matchingRules) {
				match = match && rule.match(sample);
			}
			if (match)
				res.add(sample);
		}

		return res;
	}
	
	public static String ProbeSetIdToString(Integer probeSetId, ProbeSpecContext ctx) {
        return probeSetId + " <" + ctx.obtainOriginalProbeSetId(probeSetId) + ">";
	}
	
}
