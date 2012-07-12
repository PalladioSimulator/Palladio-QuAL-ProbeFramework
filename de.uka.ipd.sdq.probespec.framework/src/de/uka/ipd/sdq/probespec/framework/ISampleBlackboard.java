package de.uka.ipd.sdq.probespec.framework;

import java.util.List;

import javax.measure.quantity.Quantity;

public interface ISampleBlackboard {

	public void addSample(ProbeSample<?, ? extends Quantity> sample,
			RequestContext requestContextID, Integer probeSetId);
	
	/**
	 * Publishes a sample on the blackboard. Interested observers gets notified
	 * of the published sample.
	 * 
	 * @param pss
	 *            the sample
	 */
	public void addSample(List<ProbeSample<?, ? extends Quantity>> samples,
				RequestContext requestContextID, Integer probeSetId);

	/**
	 * Deletes the sample specified by the {@link RequestContext} and its
	 * probeSet id.
	 * 
	 * @param pss
	 *            the sample
	 */
	public void deleteSampleSet(RequestContext requestContext, Integer probeSetID);

	/**
	 * Deletes all samples contained in the specified requestContext.
	 * 
	 * @param requestContext
	 *            the request context whose samples will be deleted.
	 */
	public void deleteSamplesInRequestContext(RequestContext requestContext);

	/**
	 * Returns the ProbeSetSample for the specified pair of ProbeSetId and
	 * {@link RequestContext}, if there is any. 
	 */
	public ProbeSetSample getSample(RequestContext requestContext, Integer probeSet);

	/**
	 * Returns the number of {@link ProbeSetSample}s contained in this
	 * blackboard
	 */
	public int size();

	/**
	 * Registers a listener for blackboard events.
	 * 
	 * @param l
	 *            the listener
	 */
	public void addBlackboardListener(IBlackboardListener l, Integer... topics);

}
