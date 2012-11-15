package edu.kit.ipd.sdq.probespec.framework.blackboard.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;

public class IndexManager {

    private static final Logger logger = Logger.getLogger(IndexManager.class);

    private Map<Class<? extends IMeasurementContext>, WeakIndex> indices;

    public IndexManager() {
        indices = new HashMap<Class<? extends IMeasurementContext>, WeakIndex>();
    }

    public void add(String value, IMeasurementContext... keys) {
        ensureIndicesExist(keys);
        for (IMeasurementContext k : keys) {
            WeakIndex index = indices.get(k.getClass());
            index.add(value, k.getId());
        }
    }

    public List<String> values(IMeasurementContext key) {
        ensureIndicesExist(key);
        return indices.get(key.getClass()).values(key.getId());
    }

    public void removeValues(IMeasurementContext key) {
        ensureIndicesExist(key);
        indices.get(key.getClass()).removeAll(key.getId());
        clean();
    }

    private void clean() {
        int deletionCount = 0;
        for (WeakIndex index : indices.values()) {
            deletionCount += index.clean();
            if (logger.isDebugEnabled()) {
                logger.debug("Cleaning removed " + deletionCount + " dangling weak references from " + index);
            }
        }
    }

    /**
     * make sure there is an index for each context, i.e. create a new index if necessary
     * 
     * @param contexts
     */
    private void ensureIndicesExist(IMeasurementContext... contexts) {
        for (IMeasurementContext c : contexts) {
            if (!indices.containsKey(c.getClass())) {
                indices.put(c.getClass(), new WeakIndex(c.getClass()));
            }
        }
    }

}
