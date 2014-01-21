package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.framework.TimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ConcurrentBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ThreadManager;

/**
 * This factory creates instances of {@link ISampleBlackboard} for the various
 * {@link BlackboardType}s.
 * 
 * @author Philipp Merkle
 * @since 1.0
 * 
 */
public class BlackboardFactory {

    /**
     * Creates a blackboard of the specified type. If a {@link BlackboardType#CONCURRENT} blackboard
     * is to be created, a <code>threadManager</code> is expected; otherwise, this parameter may be
     * <code>null</code>.
     * 
     * @param type
     *            the type of the blackboard to create
     * @param threadManager
     *            the {@link ThreadManager}, if creating a {@link BlackboardType#CONCURRENT}
     *            blackboard; <code>null</code> else
     * @return the created blackboard
     */
    public static <T> Blackboard<T> createBlackboard(BlackboardType type, TimestampGenerator<T> timestampBuilder,
            ThreadManager threadManager) {
        switch (type) {
        case SIMPLE:
            return new SimpleBlackboard<T>(timestampBuilder);
        case CONCURRENT:
            return new ConcurrentBlackboard<T>(timestampBuilder, threadManager);
        case NONE:
            return new NullBlackboard<T>();
        }

        throw new RuntimeException("Could not create a blackboard of type " + type.toString()
                + ", as it is unknown how to handle this type.");
    }

}
