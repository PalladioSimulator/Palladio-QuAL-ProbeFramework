package de.uka.ipd.sdq.probespec.framework;

import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
public class SampleBlackboard extends Observable {
	
	// ProbeSetSampleID -> ProbeSetSample
	private HashMap<ProbeSetSampleID, ProbeSetSample> pssMap = new HashMap<ProbeSetSampleID, ProbeSetSample>();

	// Lock for the map containing the probe set samples
	private Lock mapLock = new ReentrantLock();
	
	private IGarbageCollector garbageCollector;
	
	public SampleBlackboard() {
		this(new BasicGarbageCollector());
	}
	
	public SampleBlackboard(IGarbageCollector garbageCollector) {
		this.garbageCollector = garbageCollector;
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
	public void addProbeSetSample(ProbeSetSample pss) {
//		// only add this ProbeSetSample if the TTL field is greater than 0
//		if (!reduceLifetime(pss))
		
		// ensure synchronised access to the map
		mapLock.lock();
		try {
			pssMap.put(pss.getProbeSetSampleID(), pss);
		} finally {
			mapLock.unlock();
		}
		
		// inform garbage collector
		garbageCollector.provide(pss.getProbeSetSampleID()
				.getCtxID(), pss.getProbeSetSampleID());

		// notify listeners
		setChanged();
		notifyObservers(pss);
	}

	/**
	 * Returns the ProbeSetSample for the specified pair of probeID and
	 * {@link RequestContext}, if there is any. The probeId and
	 * RequestContextID are encapsulated by a {@link ProbeSetSampleID}.
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
	public ProbeSetSample getProbeSetSample(ProbeSetSampleID probeSetSampleID) {
		// try to find the ProbeSetSample in the specified context
		ProbeSetSample sample = obtainSample(probeSetSampleID);
		if (sample != null) {
			return sample;
		}
		
		// try to find the ProbeSetSample in a parent context
		RequestContext ctx = probeSetSampleID.getCtxID().getParentContext();
		String probeSetID = probeSetSampleID.getProbeSetID();
		while (ctx != null) {
			ProbeSetSampleID pssID = new ProbeSetSampleID(probeSetID, ctx);
			ProbeSetSample pss = obtainSample(pssID);
			// tryCleanUp(pss);
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
	private ProbeSetSample obtainSample(ProbeSetSampleID probeSetSampleID) {
		mapLock.lock();
		ProbeSetSample sample = null;
		try {
			sample = pssMap.get(probeSetSampleID);
		} finally {
			mapLock.unlock();
		}
		return sample;
	}

//	/**
//	 * This method calls the notAlive and then removes the ProbeSetSample from
//	 * the HashMap, if notAlive returns true.
//	 * 
//	 * @param pss
//	 *            The ProbeSetSample which eventually will be removed.
//	 */
//	private void tryCleanUp(ProbeSetSample pss) {
//		if (reduceLifetime(pss)) {
//			pssMap.remove(pss.getProbeSetSampleID());
//		}
//	}

//	/**
//	 * This method decrements the TTL field for the ProbeSetSample and then
//	 * returns true, if it is equal or less than zero.
//	 * 
//	 * @param pss
//	 *            ProbeSetSample whose TTL counter is decremented
//	 * @return true if the ProbeSetSample is not alive; else false
//	 * 
//	 */
//	private boolean reduceLifetime(ProbeSetSample pss) {
//		if (pss != null) {
//			pss.decrementTimeToLive();
//			return (pss.getTimeToLive() <= 0);
//		}
//		return false;
//	}

	/**
	 * Returns the size of the HashMap which stores all ProbeSetSamples. This is
	 * mainly used by TestCases for the garbage collection. Later it could also
	 * be used to supervise the state/utilization of the blackboard.
	 * 
	 * @return Integer which represents the number of ProbeSetSamples which are
	 *         stored in the HashMap
	 */
	public int getHashMapSize() {
		return pssMap.size();
	}
	

}
