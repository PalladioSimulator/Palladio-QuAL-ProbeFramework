package de.uka.ipd.sdq.probespec.framework;

import java.util.List;

import javax.measure.quantity.Quantity;

import de.uka.ipd.sdq.probespec.framework.matching.IMatchRule;
import de.uka.ipd.sdq.probespec.framework.utils.ProbeSpecUtils;

/**
 * Represents a sample which is taken for a ProbeSet in a {@link RequestContext}
 * .
 * <p>
 * The probe set sample is the result of a probe set measurement. It contains
 * one or more probe samples; one for each probe assigned to the underlying
 * probe set. In other words: The contained probe samples constitute the
 * combined sample for the annotated model element which is named probe set
 * sample.
 * <p>
 * A probe set (notice: not the resulting sample) encapsulates one or more
 * probes whose results are taken for the identical model element which is
 * annotated by the probe set.
 * 
 * @author pmerkle
 * @author Faber
 */
public class ProbeSetSample {

	private List<ProbeSample<?, ? extends Quantity>> probeSamples;

	private RequestContext requestContext;
	
	private int probeSetID;

	/**
	 * Class constructor specifying the encapsulated probe samples, the context
	 * id, the model element id and the probe set id.
	 * 
	 * @param probeSamples
	 *            the probe samples to be encapsulated within this probe set
	 *            sample
	 * @param ctxID
	 *            the identifier for the context in which the contained probe
	 *            samples have been taken
	 * @param modelElementID
	 *            the id of the model element which is annotated by the
	 *            underlying probe set
	 * @param probeSetID
	 *            the id of the probe set according to the underlying model
	 * @see RequestContext
	 */
	public ProbeSetSample(
			List<ProbeSample<?, ? extends Quantity>> probeSamples,
			RequestContext ctxID, Integer probeSetID) {
		super();

		this.probeSamples = probeSamples;
		this.requestContext = ctxID;
		this.probeSetID = probeSetID;
	}

	/**
	 * Returns the encapsulated probe samples satisfying the specified rule set.
	 * 
	 * @param matchingRules
	 *            the rule set
	 * @return
	 * @see ProbeSample
	 */
	public List<ProbeSample<?, ? extends Quantity>> getProbeSamples(
			IMatchRule[] matchingRules) {
		
		return ProbeSpecUtils.getProbeSamples(probeSamples, matchingRules);
	}
	
	public List<ProbeSample<?, ? extends Quantity>> getProbeSamples() {
		return probeSamples;
	}

	/**
	 * This method returns the RequestContext for this sample.
	 * It indicates
	 * <ul>
	 * <li>the {@link RequestContext} in which the sample has been taken</li>
	 * </ul>
	 * 
	 * @return the originating {@link RequestContext}
	 */
	public RequestContext getRequestContext() {
		return requestContext;
	}
	
	/**
	 * This method returns the ProbeSet-ID for this sample.
	 * It indicates
	 * <ul>
	 * <li>from which ProbeSet the sample originates</li>
	 * </ul>
	 * 
	 * @return the originating ProbeSet
	 */
	public int getProbeSetID() {
		return this.probeSetID;
	}

}
