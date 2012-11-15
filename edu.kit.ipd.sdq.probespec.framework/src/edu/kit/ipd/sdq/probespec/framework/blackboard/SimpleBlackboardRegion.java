package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.index.IndexManager;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.ListenerSupport;

public class SimpleBlackboardRegion<T> implements IBlackboardRegion<T> {

    private static final Logger logger = Logger.getLogger(SimpleBlackboardRegion.class);

    private Class<T> type;

    private IBlackboard blackboard;

    private ListenerSupport<T> listenerSupport;

    private Map<String, Measurement<T>> contextlessMeasurements;

    private Map<String, Measurement<T>> measurements;

    private IndexManager indices;

    private KeyBuilder keyBuilder;

    public SimpleBlackboardRegion(IBlackboard blackboard, Class<T> type) {
        this.type = type;
        this.blackboard = blackboard;

        listenerSupport = new ListenerSupport<T>();
        contextlessMeasurements = new HashMap<String, Measurement<T>>();
        measurements = new HashMap<String, Measurement<T>>();
        indices = new IndexManager();
        keyBuilder = new KeyBuilder();
    }

    @Override
    public void addMeasurement(T value, Probe<T> probe) {
        // wrap value into measurement object
        Measurement<T> measurement = new Measurement<T>(0, value); // TODO timestamp == 0

        // add measurement
        contextlessMeasurements.put(probe.getId(), measurement);

        // notify listeners
        listenerSupport.notifyMeasurementListeners(blackboard, value, probe);
    }

    @Override
    public void addMeasurement(T value, Probe<T> probe, IMeasurementContext... contexts) {
        // wrap value into measurement object
        Measurement<T> measurement = new Measurement<T>(0, value); // TODO timestamp == 0

        // create composite key
        String key = keyBuilder.createKey(probe, contexts);

        // store wrapped measurement
        measurements.put(key, measurement);

        // update indices
        indices.add(key, contexts);

        // notify listeners
        listenerSupport.notifyMeasurementListeners(blackboard, value, probe, contexts);
    }

    @Override
    public T getLatestMeasurement(Probe<T> probe) {
        Measurement<T> measurement = contextlessMeasurements.get(probe.getId());
        if (measurement != null) {
            return measurement.getValue();
        } else {
            return null;
        }
    }

    @Override
    public T getLatestMeasurement(Probe<T> probe, IMeasurementContext... contexts) {
        // create composite key
        String key = keyBuilder.createKey(probe, contexts);

        Measurement<T> measurement = measurements.get(key);
        if (measurement != null) {
            return measurement.getValue();
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

}
