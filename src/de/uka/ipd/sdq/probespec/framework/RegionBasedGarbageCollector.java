package de.uka.ipd.sdq.probespec.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;

/**
 * A garbage collector for the {@link SampleBlackboard}. It cleans up obsolete
 * samples stored at the blackboard.
 * <p>
 * Generally a sample is obsolete if there are no {@link Calculator}s interested
 * in the sample anymore. Assuming that calculators are solely interested in
 * samples from the same region, all samples belonging to a particular region
 * can be cleaned up as soon as it is assured that there will be no new samples
 * within that region.
 * <p>
 * A region can be represented by a thread, a process or something else like
 * specified by the class parameter T.
 * <p>
 * The cleanup starts as soon as the population within a region turns to 0. Then
 * it is assumed that there will be no new samples within that region. The
 * population is increased by calling {@link #enterRegion(Object)} and decreased
 * by calling {@link #leaveRegion(Object)}.
 * 
 * @param <T>
 *            denotes the type representing regions.
 * 
 * @author Philipp Merkle
 * 
 */
public abstract class RegionBasedGarbageCollector<T> implements
		IRegionBasedGarbageCollector<T>, Observer {

	// Counts how often a regions has been entered but not left yet (the
	// region's population)
	// Maps regionId to process count within region.
	private Map<T, Integer> regionCountMap;

	// Contains the probe samples taken within a particular region.
	// Maps regionId to (ProbeSet, RequestContext)-pairs within region.
	private Map<T, List<ProbeSetAndRequestContext>> regionProbeSetsMap;

	private SampleBlackboard blackboard;

	/**
	 * Default constructor. Constructs a garbage collector for the specified
	 * blackboard.
	 * 
	 * @param blackboard
	 *            the blackboard which is to keep clean by the garbage
	 *            collector.
	 */
	public RegionBasedGarbageCollector(SampleBlackboard blackboard) {
		this.blackboard = blackboard;

		// initialise maps
		regionCountMap = new HashMap<T, Integer>();
		regionProbeSetsMap = new HashMap<T, List<ProbeSetAndRequestContext>>();

		// observe the blackboard for new probe samples
		blackboard.addObserver(this);
	}

	/**
	 * Extracts the region id for the specified
	 * {@link ProbeSetAndRequestContext}.
	 * 
	 * @param c
	 *            the {@link ProbeSetAndRequestContext}
	 * @return the extracted region representative
	 */
	public abstract T obtainRegionId(ProbeSetAndRequestContext c);

	/**
	 * Informs the garbage collector that the specified region has been entered.
	 * 
	 * @param regionId
	 *            the id representing the entered region
	 */
	@Override
	public void enterRegion(T regionId) {
		if (increasePopulation(regionId) == 1) {
			System.out.println("Region " + regionId + " opened.");
		}
	}

	/**
	 * Informs the garbage collector that the specified region has been left.
	 * 
	 * @param regionId
	 *            the id representing the left region
	 */
	@Override
	public void leaveRegion(T regionId) {
		if (decreasePopulation(regionId) == 0) {
			collectRegionSamples(regionId);
			System.out.println("Region " + regionId + " closed.");
		}
	}

	/**
	 * Informs the garbage collector that a new sample has been arrived that
	 * eventually needs to be collected. Only the observed blackboard should
	 * call this method.
	 */
	@Override
	public void update(Observable obs, Object obj) {
		ProbeSetSample pss = (ProbeSetSample) obj;
		insertIntoRegion(pss.getProbeSetAndRequestContext());
	}

	/**
	 * Deletes all probe samples taken within the specified region from the
	 * blackboard.
	 * 
	 * @param regionId
	 *            the region id whose probe samples are to be deleted
	 */
	private void collectRegionSamples(T regionId) {
		if (regionProbeSetsMap.containsKey(regionId)) {
			// remove probe samples from blackboard
			List<ProbeSetAndRequestContext> probeSampleList = regionProbeSetsMap
					.get(regionId);
			for (ProbeSetAndRequestContext p : probeSampleList) {
				blackboard.deleteProbeSetSample(p);
			}

			regionProbeSetsMap.remove(regionId);
		}
	}

	/**
	 * Adds the specified {@link ProbeSetAndRequestContext} to the appropriate
	 * region.
	 * 
	 * @param c
	 */
	private void insertIntoRegion(ProbeSetAndRequestContext c) {
		List<ProbeSetAndRequestContext> probeSetSampleList = null;
		// does the map already contain the region id?
		if (!regionProbeSetsMap.containsKey(obtainRegionId(c))) {
			probeSetSampleList = new ArrayList<ProbeSetAndRequestContext>();
			// add new region to map
			regionProbeSetsMap.put(obtainRegionId(c), probeSetSampleList);
		} else {
			// obtain existing region from map
			probeSetSampleList = regionProbeSetsMap.get(obtainRegionId(c));
		}

		probeSetSampleList.add(c);
	}

	/**
	 * Changes the population count of the specified region.
	 * 
	 * @param regionId
	 *            the id representing the region
	 * @param amount
	 *            the amount to add (positive value) or subtract (negative
	 *            value)
	 * @return the changed population count
	 */
	private int changePopulationCounter(T regionId, int amount) {
		int counter = 0;
		if (regionCountMap.containsKey(regionId)) {
			counter = regionCountMap.get(regionId);
		}
		counter += amount;
		regionCountMap.put(regionId, counter);
		return counter;
	}

	/**
	 * Increases the population count of the specified region by one.
	 * 
	 * @param regionId
	 *            the id representing the region
	 * @return the increased population count
	 */
	private int increasePopulation(T regionId) {
		return changePopulationCounter(regionId, 1);
	}

	/**
	 * Decreases the population count of the specified region by one.
	 * 
	 * @param regionId
	 *            the id representing the region
	 * @return the decreased population count
	 */
	private int decreasePopulation(T regionId) {
		return changePopulationCounter(regionId, -1);
	}

}
