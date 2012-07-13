package de.uka.ipd.sdq.probespec.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.measure.quantity.Quantity;

import de.uka.ipd.sdq.probespec.framework.utils.ProbeSpecUtils;

/**
 * The blackboard is a mediator between entities producing samples (
 * {@link ProbeSetSample}s) and entities consuming samples. Producers offer
 * their samples by calling {@link #addSample(ProbeSetSample)}. Consumers
 * implement the {@link IBlackboardListener} interface and register themselves
 * at the blackboard. Afterwards they receive all samples they are interested
 * in.
 * <p>
 * Consumers can express their interest by passing one or more topics while
 * registering as observer. When no topics are passed, the consumer gets
 * notified of all arriving samples.
 * <p>
 * Published samples can be stored at the blackboard. When a consumer gets
 * notified of a new samples, it has to vote whether the blackboard is supposed
 * to keep (store) the sample. A single {@link BlackboardVote#RETAIN}-vote is
 * sufficient to store the sample. Only when all consumers vote
 * {@link BlackboardVote#DISCARD} the sample gets discarded.
 * <p>
 * Samples are unique identified by a pair of a ProbeSetID and a
 * {@link RequestContext}, encapsulated by a {@link ProbeSetAndRequestContext}.
 * Thereby the blackboard can store several samples originating from the same
 * ProbeSet, one for each RequestContext.
 * 
 * @author Faber
 * @author Philipp Merkle
 * 
 */
public class SampleBlackboard implements ISampleBlackboard {

	private List<IBlackboardListener> listeners;

	private HashMap<Integer, ArrayList<IBlackboardListener>> topicToListenersMap;

	// stores the samples
	// maps RequestContext to map (ProbeSetAndRequestContext -> ProbeSetSample)
	private HashMap<RequestContext, HashMap<Integer, ProbeSetSample>> sampleMap = new HashMap<RequestContext, HashMap<Integer, ProbeSetSample>>();

	public SampleBlackboard() {
		listeners = new ArrayList<IBlackboardListener>();
		topicToListenersMap = new HashMap<Integer, ArrayList<IBlackboardListener>>();
	}

	public void addSample(ProbeSample<?, ? extends Quantity> sample,
			RequestContext requestContextID, Integer probeSetId) {
		 List<ProbeSample<?, ? extends Quantity>> samples = new ArrayList<ProbeSample<?,? extends Quantity>>();
		 samples.add(sample);
		 addSample(samples, requestContextID, probeSetId);
	}

	public void addSample(List<ProbeSample<?, ? extends Quantity>> samples,
			RequestContext requestContextID, Integer probeSetId) {
		// notify listeners and obtain deletion vote
		BlackboardVote deletionVote = fireSampleArrived(samples, requestContextID, probeSetId);

		// retain sample if at least one RETAIN-vote exists
		if (deletionVote.equals(BlackboardVote.RETAIN)) {
			HashMap<Integer, ProbeSetSample> contextMap = sampleMap.get(requestContextID);
			// create hash map for request context, if not done yet
			if (contextMap == null) {
				contextMap = new HashMap<Integer, ProbeSetSample>();
				sampleMap.put(requestContextID, contextMap);
			}
			
			//Improvement:
			//ProbeSetSamples and ProbeSetAndRequestContext are only created if they are really needed. 
			ProbeSetSample pss = ProbeSpecUtils.buildProbeSetSample(samples, requestContextID, probeSetId);
			contextMap.put(pss.getProbeSetID(), pss);
		}
	}

	@Override
	public void deleteSampleSet(RequestContext requestContext, Integer probeSetID) {
		sampleMap.get(requestContext).remove(probeSetID);
	}



	public void deleteSamplesInRequestContext(RequestContext requestContext) {
		// delete samples in child contexts, if there are any
		if (requestContext.getChildContexts() != null) {
			for (RequestContext child : requestContext.getChildContexts()) {
				deleteSamplesInRequestContext(child);
			}
		}
		
		HashMap<Integer, ProbeSetSample> contextMap = sampleMap.get(requestContext);
		if (contextMap != null) {
			
			contextMap.clear();
			contextMap = sampleMap.remove(requestContext);
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * If no ProbeSetSample can be found for the RequestContext and the
	 * RequestContext has a parent context, the search will be performed for
	 * that parent context too. This continues recursively until a
	 * RequestContext is reached that does not have a parent context.
	 * <p>
	 * This recursive search is useful for e.g. finding the start ProbeSetSample
	 * (taken before a fork) for a given end ProbeSetSample (taken within a
	 * fork).
	 * 
	 * @param requestContext 
	 * @param probeSetID
	 * @return the ProbeSetSample for the requestContext and probeSetID, if there is any;
	 *         else null.
	 */
	@Override
	public ProbeSetSample getSample(RequestContext requestContext, Integer probeSetID) {
		// try to find the ProbeSetSample in the specified context
		ProbeSetSample sample = obtainSample(requestContext, probeSetID);
		if (sample != null) {
			return sample;
		}

		// try to find the ProbeSetSample in a parent context
		RequestContext ctx = requestContext.getParentContext();
		while (ctx != null) {
			
			ProbeSetSample pss = obtainSample(ctx, probeSetID);
			if (pss != null) {
				return pss;
			}
			ctx = ctx.getParentContext();
		}
		return null;
	}

	/**
	 * Returns the {@link ProbeSetSample} for the specified
	 * {@link ProbeSetAndRequestContext}.
	 * 
	 * @param probeSetSampleID
	 * @return
	 */
	private ProbeSetSample obtainSample(RequestContext requestContext, Integer probeSetID) {
		HashMap<Integer, ProbeSetSample> contextMap = sampleMap.get(requestContext);
		if (contextMap != null) {
			return contextMap.get(probeSetID);
		}
		return null;
	}

	public int size() {
		int i = 0;
		for (Entry<RequestContext, HashMap<Integer, ProbeSetSample>> e : sampleMap
				.entrySet()) {
			i += e.getValue().size();
		}
		return i;
	}

	public void addBlackboardListener(IBlackboardListener l, Integer... topics) {
		if (topics.length == 0) {
			listeners.add(l);
		} else {
			// add listener for each topic
			for (Integer t : topics) {
				ArrayList<IBlackboardListener> listeners = topicToListenersMap
						.get(t);
				if (listeners == null) {
					listeners = new ArrayList<IBlackboardListener>();
					topicToListenersMap.put(t, listeners);
				}
				listeners.add(l);
			}
		}

	}

	/**
	 * Notifies all specified listeners of a new {@link ProbeSetSample}.
	 * 
	 * @param pss
	 * @param listeners
	 *            the listeners that are to be notified
	 * @return {@link BlackboardVote#RETAIN} when the sample has to be stored;
	 *         {@link BlackboardVote#DISCARD} else.
	 */
	private BlackboardVote fireSampleArrived(List<ProbeSample<?, ? extends Quantity>> samples,
			RequestContext requestContextID, Integer probeSetId, List<IBlackboardListener> listeners) {
		// private BlackboardVote fireSampleArrived(ProbeSetSample pss) {
		BlackboardVote deletionVote = BlackboardVote.DISCARD;
		for (IBlackboardListener l : listeners) {
			if (l.sampleArrived(samples, requestContextID, probeSetId).equals(BlackboardVote.RETAIN)) {
				deletionVote = BlackboardVote.RETAIN;
			}
		}
		return deletionVote;
	}

	/**
	 * Notifies all listeners of a new {@link ProbeSetSample}. Both listeners
	 * that are not registered for a specific topic and listeners interested in
	 * one or more topics get notified of the new ProbeSetSample.
	 * 
	 * @param pss
	 * @return {@link BlackboardVote#RETAIN} when the sample has to be stored;
	 *         {@link BlackboardVote#DISCARD} else.
	 */
	private BlackboardVote fireSampleArrived(List<ProbeSample<?, ? extends Quantity>> samples,
			RequestContext requestContextID, Integer probeSetId) {
		// notify listeners that are not registered for a specific topic
		BlackboardVote firstDeletionVote = fireSampleArrived(samples, requestContextID, probeSetId, listeners);

		// notify listeners that are registered for the sample's topic
		List<IBlackboardListener> listeners = topicToListenersMap.get(probeSetId);
		BlackboardVote secondDeletionVote = null;
		if (listeners != null) {
			secondDeletionVote = fireSampleArrived(samples, requestContextID, probeSetId, listeners);
		}

		if (firstDeletionVote.equals(BlackboardVote.DISCARD)
				&& (secondDeletionVote == null || secondDeletionVote
						.equals(BlackboardVote.DISCARD))) {
			return BlackboardVote.DISCARD;
		}
		return BlackboardVote.RETAIN;
	}
}
