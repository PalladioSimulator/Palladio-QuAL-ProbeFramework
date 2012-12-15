package edu.kit.ipd.sdq.probespec.framework.garbagecollection;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener._IBlackboardReader_;

/**
 * TODO Update documentation!
 * 
 * A garbage collector for the {@link IBlackboard}. It cleans up obsolete measurements stored at the
 * blackboard.
 * <p>
 * Generally a measurement is obsolete if there are no {@link _IBlackboardReader_}s interested in
 * the sample anymore. Assuming that calculators are solely interested in samples from the same
 * region, all samples belonging to a particular region can be cleaned up as soon as it is assured
 * that there will be no new samples within that region.
 * <p>
 * A region can be represented by a thread, a process or something else like specified by the class
 * parameter T.
 * <p>
 * The cleanup starts as soon as the population within a region turns to 0. Then it is assumed that
 * there will be no new samples within that region. The population is increased by calling
 * {@link #enterContext(Object)} and decreased by calling {@link #leaveContext(Object)}.
 * 
 * @param <T>
 *            denotes the type representing regions.
 * 
 * @author Philipp Merkle
 * @since 1.0
 * 
 */
public abstract class RegionBasedGarbageCollector<T extends IMeasurementContext> implements
        IRegionBasedGarbageCollector<T> {

    public static Logger logger = Logger.getLogger(RegionBasedGarbageCollector.class);

    // Counts how often a regions has been entered but not left yet (the region's population)
    // Maps regionId to process count within region.
    private Map<T, Integer> regionCountMap;

    private IBlackboard<?> blackboard;

    /**
     * Default constructor. Constructs a garbage collector for the specified blackboard.
     * 
     * @param blackboard
     *            the blackboard which is to keep clean by the garbage collector.
     */
    public RegionBasedGarbageCollector(IBlackboard<?> blackboard) {
        this.blackboard = blackboard;
        regionCountMap = new HashMap<T, Integer>();
    }

    /**
     * Informs the garbage collector that the specified region has been entered.
     * 
     * @param regionId
     *            the id representing the entered region
     */
    @Override
    public void enterContext(T regionId) {
        if (increasePopulation(regionId) == 1) {
            if (logger.isDebugEnabled()) {
                logger.debug("Region " + regionId + " opened");
            }
        }
    }

    /**
     * Informs the garbage collector that the specified region has been left.
     * 
     * As soon as the population reaches 0, the cleanup starts.
     * 
     * @param regionId
     *            the id representing the left region
     */
    @Override
    public void leaveContext(T regionId) {
        if (decreasePopulation(regionId) == 0) {
            cleanRegion(regionId);
            regionCountMap.remove(regionId);
            if (logger.isDebugEnabled()) {
                logger.debug("Region " + regionId + " closed");
            }
        }
    }

    /**
     * Deletes from the blackboard all measurements taken within the specified region.
     * 
     * @param regionId
     *            the region id whose probe samples are to be deleted
     */
    public abstract void cleanRegion(T regionId);

    protected IBlackboard<?> getBlackboard() {
        return blackboard;
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

    /**
     * Changes the population count of the specified region.
     * 
     * @param regionId
     *            the id representing the region
     * @param amount
     *            the amount to add (positive value) or subtract (negative value)
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

}
