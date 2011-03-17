package de.uka.ipd.sdq.probespec.framework;

import de.uka.ipd.sdq.probespec.framework.concurrency.ConcurrentSampleBlackboard;

/**
 * This factory creates instances of {@link ISampleBlackboard} for the various
 * {@link BlackboardType}s.
 * 
 * @author Philipp Merkle
 * 
 */
public class BlackboardFactory {

    /**
     * Creates a blackboard of the specified type.
     * 
     * @param type
     *            the type of the blackboard that is to be created
     * @return the created blackboard
     */
    public static ISampleBlackboard createBlackboard(BlackboardType type) {
        switch (type) {
        case SIMPLE:
            return new SampleBlackboard();
        case CONCURRENT:
            return new ConcurrentSampleBlackboard();
        case NONE:
            return new NullSampleBlackboard();
        }

        throw new RuntimeException("Could not create a blackboard of type " + type.toString()
                + ", as it is unknown how to handle this type.");
    }

}
