package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.ITimestampBuilder;
import edu.kit.ipd.sdq.probespec.framework.blackboard.index.IndexManager;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.ListenerSupport;

public class SimpleBlackboardRegion<T, U> implements IBlackboardRegion<T, U> {

    private static final Logger logger = Logger.getLogger(SimpleBlackboardRegion.class);

    private Class<T> type;

    private IBlackboard<U> blackboard;

    private ListenerSupport<T, U> listenerSupport;

    private Map<String, Measurement<T, U>> contextlessMeasurements;

    private Map<String, Measurement<T, U>> measurements;

    private IndexManager indices;

    private KeyBuilder keyBuilder;

    private ITimestampBuilder<U> timestampBuilder;

    public SimpleBlackboardRegion(IBlackboard<U> blackboard, Class<T> type, ITimestampBuilder<U> timestampBuilder) {
        this.type = type;
        this.blackboard = blackboard;
        this.timestampBuilder = timestampBuilder;

        listenerSupport = new ListenerSupport<T, U>();
        contextlessMeasurements = new HashMap<String, Measurement<T, U>>();
        measurements = new HashMap<String, Measurement<T, U>>();
        indices = new IndexManager();
        keyBuilder = new KeyBuilder();
    }

    @Override
    public void addMeasurement(T value, Probe<T> probe) {
        // wrap value into measurement object
        Measurement<T, U> measurement = new Measurement<T, U>(timestampBuilder.now(), value);

        // add measurement
        contextlessMeasurements.put(probe.getId(), measurement);

        // notify listeners
        listenerSupport.notifyMeasurementListeners(blackboard, measurement, probe);
    }

    @Override
    public void addMeasurement(T value, Probe<T> probe, IMeasurementContext... contexts) {
        // wrap value into measurement object
        Measurement<T, U> measurement = new Measurement<T, U>(timestampBuilder.now(), value);

        // create composite key
        String key = keyBuilder.createKey(probe, contexts);

        // store wrapped measurement
        measurements.put(key, measurement);

        // update indices
        indices.add(key, contexts);

        // notify listeners
        listenerSupport.notifyMeasurementListeners(blackboard, measurement, probe, contexts);
    }

    @Override
    public Measurement<T, U> getLatestMeasurement(Probe<T> probe) {
        Measurement<T, U> measurement = contextlessMeasurements.get(probe.getId());
        if (measurement != null) {
            return measurement;
        } else {
            return null;
        }
    }

    @Override
    public Measurement<T, U> getLatestMeasurement(Probe<T> probe, IMeasurementContext... contexts) {
        // create composite key
        String key = keyBuilder.createKey(probe, contexts);

        Measurement<T, U> measurement = measurements.get(key);
        if (measurement != null) {
            return measurement;
        } else {
            return null;
        }
    }

    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        // delete measurements
        int counter = 0;
        for (WeakReference<String> keyRef : indices.values(context)) {
            String key = keyRef.get();
            if (key != null) {
                measurements.remove(key);
                ++counter;
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Deleted " + counter + " entries in context " + context);
        }

        // remove deleted measurements from indices
        indices.removeValues(context);
    }

    @Override
    public void deleteMeasurement(Probe<T> probe, IMeasurementContext... contexts) {
        // create composite key
        String key = keyBuilder.createKey(probe, contexts);

        // delete measurement identified by composite key
        measurements.remove(key);
    }

    @Override
    public void addMeasurementListener(IBlackboardListener<T, U> l) {
        listenerSupport.addMeasurementListener(l);
    }

    @Override
    public void addMeasurementListener(IBlackboardListener<T, U> l, Probe<T> probe) {
        listenerSupport.addMeasurementListener(l, probe);
    }

    @Override
    public void removeMeasurementListener(IBlackboardListener<T, U> l) {
        listenerSupport.removeMeasurementListener(l);
    }

    @Override
    public Class<T> getGenericType() {
        return type;
    }

}
