package de.uka.ipd.sdq.probespec.framework;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class realizes the blackboard pattern to store all ProbeSetSamples and
 * allows the Calculators to access this data. This makes it to a central class
 * in the ProbeSpec framework. An observer pattern is used to notify the
 * Calculator about the arrival of a new ProbeSetSample.
 * <p>
 * Internally the storage is structured as HashMaps which maps the
 * ProbeSetSampleID to the ProbeSetSample (ProbeSetSampleID -> ProbeSetSample).
 * <p>
 * TODO This SampleBlackboard also realizes a simple implementation of a garbage
 * collection to delete ProbeSetSamples which are not needed any more. For now
 * this is done by a counter for each ProbeSetSample which can be seen as a
 * "time to life" field (To set the field correctly see the ProbeSetSample
 * documentation). Every time a ProbeSetSample is read, the counter is
 * decremented and finally the ProbeSetSample is deleted, if the counter is
 * equal or less than zero. Note that the first time the TTL field is
 * decremented is, when the ProbeSetSample is added to the blackboard. This is
 * because the Calculators then receive the ProbeSetSample for the first time
 * (Observer pattern).
 * 
 * @author Faber
 * @author Philipp Merkle
 * 
 */
public class SampleBlackboard {
	
	// copy on write enables listeners to unregister during event processing.
	private List<IBlackboardListener> listeners;
	
	private HashMap<String, ArrayList<IBlackboardListener>> topicToListenersMap;
	
	// ProbeSetSampleID -> ProbeSetSample
	private HashMap<RequestContext, HashMap<ProbeSetAndRequestContext, ProbeSetSample>> sampleMap = new HashMap<RequestContext, HashMap<ProbeSetAndRequestContext, ProbeSetSample>>();

	public SampleBlackboard() {
		listeners = new ArrayList<IBlackboardListener>();
		topicToListenersMap = new HashMap<String, ArrayList<IBlackboardListener>>();
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
	 * Returns the ProbeSetSample for the specified pair of probeID and
	 * {@link RequestContext}, if there is any. The probeId and
	 * RequestContextID are encapsulated by a {@link ProbeSetAndRequestContext}.
	 * <p>
	 * If no ProbeSetSample can be found for the RequestContextID and the
	 * RequestContextID has a parent context, the search will be performed for
	 * this parent context too. More precisely, for the pair of probeID and
	 * parent ProbeSetSampleID. This continues recursively until a
	 * RequestContextID with missing parent context is reached.
	 * <p>
	 * This recursive search is useful for e.g. finding the start ProbeSetSample
	 * (taken before a fork) for a given end ProbeSetSample (taken within a
	 * fork).
	 * 
	 * TODO After the extraction the tryCleanUp method is called to eventually
	 * remove the ProbeSetSample from the HashMap.
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
		String probeSetID = probeSetSampleID.getProbeSetID();
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
	 * Provides synchronised access to the map containing the probe set samples.
	 * Hereby potential race conditions are avoided.
	 * 
	 * @param probeSetSampleID
	 * @return
	 */
	private ProbeSetSample obtainSample(ProbeSetAndRequestContext probeSetSampleID) {
		HashMap<ProbeSetAndRequestContext, ProbeSetSample> x = sampleMap.get(probeSetSampleID.getCtxID());
		if (x != null) {
			return x.get(probeSetSampleID);		
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
//		Enumeration<ConcurrentHashMap<ProbeSetAndRequestContext, ProbeSetSample>> e = sampleMap
//				.elements();
//		while (e.hasMoreElements()) {
//			ConcurrentHashMap<ProbeSetAndRequestContext, ProbeSetSample> contextMap = e
//					.nextElement();
//			i += contextMap.size();
//		}
		return i;
	}
	
	/**
	 * Registers a listener which gets notified of blackboard events.
	 * 
	 * @param l
	 */
	public void addBlackboardListener(IBlackboardListener l, String... topics) {
		if (topics.length == 0) {
			listeners.add(l);
		}
		else {
			// add listener for each topic
			for (String t : topics) {
				ArrayList<IBlackboardListener> listeners = topicToListenersMap.get(t);
				if (listeners == null) {
					listeners = new ArrayList<IBlackboardListener>();
					topicToListenersMap.put(t, listeners);
				}
				listeners.add(l);
			}
		}
		
	}
	
	/**
	 * Notifies all registered listeners of a new {@link ProbeSetSample}.
	 * 
	 * @param pss
	 * @return
	 */
	private BlackboardVote fireSampleArrived(ProbeSetSample pss, List<IBlackboardListener> listeners) {
//	private BlackboardVote fireSampleArrived(ProbeSetSample pss) {
		BlackboardVote deletionVote = BlackboardVote.DISCARD;
		for (IBlackboardListener l : listeners) {
			if (l.sampleArrived(pss).equals(BlackboardVote.RETAIN)) {
				deletionVote = BlackboardVote.RETAIN;
			}
		}
		return deletionVote;
	}
	
	private BlackboardVote fireSampleArrived(ProbeSetSample pss) {
		// notify listeners that are not registered for a specific topic
		BlackboardVote firstDeletionVote = fireSampleArrived(pss, listeners);

		// notify listeners that are registered for the sample's topic
		String topic = pss.getProbeSetAndRequestContext().getProbeSetID();
		List<IBlackboardListener> listeners = topicToListenersMap
				.get(topic);
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
