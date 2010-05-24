package de.uka.ipd.sdq.probespec.framework;

import java.util.Vector;

import javax.measure.quantity.Quantity;

public class ProbeSpecUtils {

	public static ProbeSetSample buildProbeSetSample(
			ProbeSample<?, ? extends Quantity> sample,
			RequestContext requestContextID, String modelElementId,
			String probeSetId) {
		Vector<ProbeSample<?, ? extends Quantity>> probeSampleVector = new Vector<ProbeSample<?, ? extends Quantity>>();
		probeSampleVector.add(sample);

		ProbeSetSample pss = new ProbeSetSample(probeSampleVector,
				requestContextID, modelElementId, probeSetId);
		return pss;
	}

	public static ProbeSetSample buildProbeSetSample(
			ProbeSample<?, ? extends Quantity> sample1,
			ProbeSample<?, ? extends Quantity> sample2,
			RequestContext requestContextID, String modelElementId,
			String probeSetId) {
		Vector<ProbeSample<?, ? extends Quantity>> probeSampleVector = new Vector<ProbeSample<?, ? extends Quantity>>();
		probeSampleVector.add(sample1);
		probeSampleVector.add(sample2);

		ProbeSetSample pss = new ProbeSetSample(probeSampleVector,
				requestContextID, modelElementId, probeSetId);
		return pss;
	}

}
