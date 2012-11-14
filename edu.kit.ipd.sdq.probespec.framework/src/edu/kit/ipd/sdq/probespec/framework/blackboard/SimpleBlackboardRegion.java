package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import edu.kit.ipd.sdq.probespec.Probe;

public class SimpleBlackboardRegion<T> implements IBlackboardRegion<T> {

    private static final Logger logger = Logger.getLogger(SimpleBlackboardRegion.class);

    private Class<T> type;

    private IBlackboard blackboard;

    private ListenerSupport<T> listenerSupport;

    private Map<Probe<T>, Measurement<T>> contextlessMeasurements;

    private Map<String, Measurement<T>> measurements;

    private Map<Class<? extends IMeasurementContext>, Multimap<String, WeakReference<String>>> indices;

    private KeyBuilder keyBuilder;

    public SimpleBlackboardRegion(IBlackboard blackboard, Class<T> type) {
        this.type = type;
        this.blackboard = blackboard;
        listenerSupport = new ListenerSupport<T>();
        contextlessMeasurements = new HashMap<Probe<T>, Measurement<T>>();
        measurements = new HashMap<String, Measurement<T>>();
        indices = new HashMap<Class<? extends IMeasurementContext>, Multimap<String, WeakReference<String>>>();
        keyBuilder = new KeyBuilder();
    }

    @Override
    public void addMeasurement(T value, Probe<T> probe) {
        if (logger.isDebugEnabled()) {
            logger.debug("Adding measurement for probe " + probe.getName() + ": " + value);
        }

        // wrap value into measurement object
        Measurement<T> measurement = new Measurement<T>(0, value);

        // add measurement
        contextlessMeasurements.put(probe, measurement);

        // notify listeners
        listenerSupport.notifyMeasurementListeners(blackboard, value, probe);
    }

    @Override
    public void addMeasurement(T value, Probe<T> probe, IMeasurementContext... contexts) {
        if (logger.isDebugEnabled()) {
            logger.debug("Adding measurement for probe " + probe.getName() + ": " + value);
        }

        // wrap value into measurement object
        Measurement<T> measurement = new Measurement<T>(0, value); // TODO timestamp == 0

        // create composite key
        String key = keyBuilder.createKey(probe, contexts);

        // store wrapped measurement
        measurements.put(key, measurement);

        // make sure there is an index for each context, i.e. create a new index if necessary
        ensureIndicesExist(contexts);

        // update indices
        for (IMeasurementContext c : contexts) {
            Multimap<String, WeakReference<String>> index = indices.get(c.getClass());
            index.put(c.getId(), new WeakReference<String>(key));
        }

        // notify listeners
        listenerSupport.notifyMeasurementListeners(blackboard, value, probe, contexts);
    }

    @Override
    public T getLatestMeasurement(Probe<T> probe) {
        return contextlessMeasurements.get(probe).getValue();
    }

    @Override
    public T getLatestMeasurement(Probe<T> probe, IMeasurementContext... contexts) {
        // make sure there is an index for each context
        ensureIndicesExist(contexts);

        // create composite key
        String key = keyBuilder.createKey(probe, contexts);

        return measurements.get(key).getValue();
    }

    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        // obtain index corresponding to passed context
        Multimap<String, WeakReference<String>> index = indices.get(context.getClass());

        // return if there is not yet an index for the passed context
        if (index == null) {
            return;
        }

        int count = index.get(context.getId()).size();
        System.out.println("About to delete " + count + " entries in context " + context);

        int deletionCounter = 0;
        for (Iterator<WeakReference<String>> it = index.get(context.getId()).iterator(); it.hasNext();) {
            WeakReference<String> ref = it.next();

            // weakly referenced object might be gc'ed already, thus check for null first
            String key = ref.get();
            if (key != null) {
                measurements.remove(key);
                ++deletionCounter;
            }
        }

        System.out.println("Deleted " + deletionCounter + " entries in context " + context);

        // remove deleted measurements from indices
        int removed = count;
        indices.get(context.getClass()).removeAll(context.getId());
        removed += cleanIndices(context.getClass());
        System.out.println("Removed " + removed + " dangling weak references...");

    }

    @Override
    public void deleteMeasurement(Probe<T> probe, IMeasurementContext... contexts) {
        // create composite key
        String key = keyBuilder.createKey(probe, contexts);

        // delete measurement identified by composite key
        measurements.remove(key);
    }

    @Override
    public void addMeasurementListener(IBlackboardListener<T> l) {
        listenerSupport.addMeasurementListener(l);
    }

    @Override
    public void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe) {
        listenerSupport.addMeasurementListener(l, probe);
    }

    @Override
    public void removeMeasurementListener(IBlackboardListener<T> l) {
        listenerSupport.removeMeasurementListener(l);
    }

    @Override
    public Class<T> getGenericType() {
        return type;
    }

    private int cleanIndices(Class<? extends IMeasurementContext> exclude) {
        int deletionCount = 0;
        for (Entry<Class<? extends IMeasurementContext>, Multimap<String, WeakReference<String>>> index : indices
                .entrySet()) {
            if (index.getKey().equals(exclude)) {
                continue;
            }

            for (Iterator<WeakReference<String>> it = index.getValue().values().iterator(); it.hasNext();) {
                WeakReference<String> ref = it.next();
                if (ref.get() == null) {
                    // remove dangling weak reference, whose referenced object has been gc'ed
                    // already
                    it.remove();
                    ++deletionCount;
                }
            }

        }
        return deletionCount;
    }

    private void ensureIndicesExist(IMeasurementContext... contexts) {
        for (IMeasurementContext c : contexts) {
            if (!indices.containsKey(c.getClass())) {
                Multimap<String, WeakReference<String>> m = HashMultimap.create();
                indices.put(c.getClass(), m);
            }
        }
    }

}
