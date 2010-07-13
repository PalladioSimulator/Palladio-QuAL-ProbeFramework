package de.uka.ipd.sdq.probespec.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
 * notified of a new samples, it has to vote whether to keep (store) the sample.
 * A single {@link BlackboardVote#RETAIN}-vote is sufficient to store the
 * sample. Only when all consumers vote {@link BlackboardVote#DISCARD} the
 * sample gets discarded.
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
public class SampleBlackboard {

	private List<IBlackboardListener> listeners;

	private HashMap<Integer, ArrayList<IBlackboardListener>> topicToListenersMap;

	// ProbeSetSampleID -> ProbeSetSample
	private HashMap<RequestContext, HashMap<ProbeSetAndRequestContext, ProbeSetSample>> sampleMap = new HashMap<RequestContext, HashMap<ProbeSetAndRequestContext, ProbeSetSample>>();

	public SampleBlackboard() {
		listeners = new ArrayList<IBlackboardListener>();
		topicToListenersMap = new HashMap<Integer, ArrayList<IBlackboardListener>>();
	}

	/**
	 * This method allows to add a ProbeSetSample to the SampleBlackboard. It is
	 * only added to the HashMap, if the TTL field is still greater than zero.
	 * See class description for detailed information about the storage
	 * structure and its garbage collection.
	 * 
	 * @param pss
	 *            ProbeSetSample which is added to the SampleBlackboard and then
	 *            distributed to all Calculators (observer pattern).
	 */
	public void addSample(ProbeSetSample pss) {
		// notify listeners and obtain deletion vote
		BlackboardVote deletionVote = fireSampleArrived(pss);

		// retain sample if at least one retain-vote exists
		if (deletionVote.equals(BlackboardVote.RETAIN)) {
			RequestContext context = pss.getProbeSetAndRequestContext()
					.getCtxID();
			HashMap<ProbeSetAndRequestContext, ProbeSetSample> contextMap = sampleMap
					.get(context);
			// create hash map for request context if not done yet
			if (contextMap == null) {
				contextMap = new HashMap<ProbeSetAndRequestContext, ProbeSetSample>();
				sampleMap.put(context, contextMap);
			}
			contextMap.put(pss.getProbeSetAndRequestContext(), pss);
		}
	}

	/**
	 * Deletes the probe sample specified by the
	 * {@link ProbeSetAndRequestContext}.
	 * 
	 * @param pss
	 */
	public void deleteSample(ProbeSetAndRequestContext pss) {
		sampleMap.get(pss.getCtxID()).remove(pss);
	}

	/**
	 * Deletes all samples contained in the specified request context.
	 * 
	 * @param requestContext
	 *            the request context whose samples will be deleted.
	 */
	public void deleteSamplesInRequestContext(RequestContext requestContext) {
		HashMap<ProbeSetAndRequestContext, ProbeSetSample> contextMap = sampleMap
				.get(requestContext);
		if (contextMap != null) {
			contextMap.clear();
			sampleMap.remove(requestContext);
		}
	}

	/**
	 * Returns the ProbeSetSample for the specified pair of ProbeSetId and
	 * {@link RequestContext}, if there is any. The ProbeSetId and
	 * RequestContext are encapsulated by a {@link ProbeSetAndRequestContext}.
	 * <p>
	 * If no ProbeSetSample can be found for the RequestContext and the
	 * RequestContext has a parent context, the search will be performed for
	 * that parent context too. This continues recursively until a
	 * RequestContext with missing parent context is reached.
	 * <p>
	 * This recursive search is useful for e.g. finding the start ProbeSetSample
	 * (taken before a fork) for a given end ProbeSetSample (taken within a
	 * fork).
	 * 
	 * @param probeSetSampleID
	 *            the encapsulated probeId and RequestContextID
	 * @return the ProbeSetSample for the probeSetSampleID, if there is any;
	 *         else null.
	 */
	public ProbeSetSample getSample(ProbeSetAndRequestContext probeSetSampleID) {
		// try to find the ProbeSetSample in the specified context
		ProbeSetSample sample = obtainSample(probeSetSampleID);
		if (sample != null) {
			return sample;
		}

		// try to find the ProbeSetSample in a parent context
		RequestContext ctx = probeSetSampleID.getCtxID().getParentContext();
		Integer probeSetID = probeSetSampleID.getProbeSetID();
		while (ctx != null) {
			ProbeSetAndRequestContext pssID = new ProbeSetAndRequestContext(
					probeSetID, ctx);
			ProbeSetSample pss = obtainSample(pssID);
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
	private ProbeSetSample obtainSample(
			ProbeSetAndRequestContext probeSetSampleID) {
		HashMap<ProbeSetAndRequestContext, ProbeSetSample> contextMap = sampleMap
				.get(probeSetSampleID.getCtxID());
		if (contextMap != null) {
			return contextMap.get(probeSetSampleID);
		}
		return null;
	}

	/**
	 * Returns the number of {@link ProbeSetSample}s in this blackboard.
	 * 
	 * @return
	 */
	public int size() {
		int i = 0;
		// TODO
		// Enumeration<ConcurrentHashMap<ProbeSetAndRequestContext,
		// ProbeSetSample>> e = sampleMap
		// .elements();
		// while (e.hasMoreElements()) {
		// ConcurrentHashMap<ProbeSetAndRequestContext, ProbeSetSample>
		// contextMap = e
		// .nextElement();
		// i += contextMap.size();
		// }
		return i;
	}

	/**
	 * Registers a listener for blackboard events.
	 * 
	 * @param l
	 *            the listener
	 */
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
	private BlackboardVote fireSampleArrived(ProbeSetSample pss,
			List<IBlackboardListener> listeners) {
		// private BlackboardVote fireSampleArrived(ProbeSetSample pss) {
		BlackboardVote deletionVote = BlackboardVote.DISCARD;
		for (IBlackboardListener l : listeners) {
			if (l.sampleArrived(pss).equals(BlackboardVote.RETAIN)) {
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
	private BlackboardVote fireSampleArrived(ProbeSetSample pss) {
		// notify listeners that are not registered for a specific topic
		BlackboardVote firstDeletionVote = fireSampleArrived(pss, listeners);

		// notify listeners that are registered for the sample's topic
		Integer topic = pss.getProbeSetAndRequestContext().getProbeSetID();
		List<IBlackboardListener> listeners = topicToListenersMap.get(topic);
		BlackboardVote secondDeletionVote = null;
		if (listeners != null) {
			secondDeletionVote = fireSampleArrived(pss, listeners);
		}

		if (firstDeletionVote.equals(BlackboardVote.DISCARD)
				&& (secondDeletionVote == null || secondDeletionVote
						.equals(BlackboardVote.DISCARD))) {
			return BlackboardVote.DISCARD;
		}
		return BlackboardVote.RETAIN;
	}

}
