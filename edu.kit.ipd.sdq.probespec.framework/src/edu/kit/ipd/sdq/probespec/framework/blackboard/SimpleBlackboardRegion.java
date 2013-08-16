package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.IMetadata;
import edu.kit.ipd.sdq.probespec.framework.ITimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.index.IndexManager;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.ListenerSupport;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReaderSupport;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;

public class SimpleBlackboardRegion<V, T> implements IBlackboardRegion<V, T> {

    private static final Logger logger = Logger.getLogger(SimpleBlackboardRegion.class);

    private Class<V> type;

    private IBlackboard<T> blackboard;

    private ListenerSupport<V, T> listenerSupport;

    private BlackboardReaderSupport<V, T> readerSupport;

    private Map<String, Measurement<V, T>> measurements;

    private IndexManager indices;

    private KeyBuilder keyBuilder;

    private ITimestampGenerator<T> timestampBuilder;

    public SimpleBlackboardRegion(IBlackboard<T> blackboard, Class<V> type, ITimestampGenerator<T> timestampBuilder) {
        this.type = type;
        this.blackboard = blackboard;
        this.timestampBuilder = timestampBuilder;

        listenerSupport = new ListenerSupport<V, T>();
        readerSupport = new BlackboardReaderSupport<V, T>();
        measurements = new HashMap<String, Measurement<V, T>>();
        indices = new IndexManager();
        keyBuilder = new KeyBuilder();
    }

    @Override
    public void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts) {
        addMeasurement(value, probe, IMetadata.EMPTY_METADATA, contexts);
    }

    @Override
    public void addMeasurement(V value, Probe<V> probe, IMetadata metadata, IMeasurementContext... contexts) {    
        // wrap value into measurement object
        Measurement<V, T> measurement = new Measurement<V, T>(value, timestampBuilder.now(), probe);

        // create composite key
        String key = keyBuilder.createKey(probe, contexts);

        // store wrapped measurement
        if (readerSupport.hasConsumers(probe)) {
            measurements.put(key, measurement);
        } else {
            // logger.debug("Discarding " + measurement + " for probe " + probe.getName());
        }

        // update indices
        if (contexts.length > 0) {
            indices.add(key, contexts);
        }

        // notify listeners
        listenerSupport.notifyMeasurementListeners(blackboard, measurement, probe, contexts);
    }

    @Override
    public Measurement<V, T> getLatestMeasurement(Probe<V> probe, IMeasurementContext... contexts) {
        // create composite key
        String key = keyBuilder.createKey(probe, contexts);

        Measurement<V, T> measurement = measurements.get(key);
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
    public void deleteMeasurement(Probe<V> probe, IMeasurementContext... contexts) {
        // create composite key
        String key = keyBuilder.createKey(probe, contexts);

        // delete measurement identified by composite key
        measurements.remove(key);
    }

    @Override
    public void addMeasurementListener(IBlackboardListener<V, T> l) {
        listenerSupport.addMeasurementListener(blackboard, l);
    }

    @Override
    public void addMeasurementListener(IBlackboardListener<V, T> l, Probe<V> probe) {
        listenerSupport.addMeasurementListener(blackboard, l, probe);
    }

    @Override
    public void removeMeasurementListener(IBlackboardListener<?, T> l) {
        listenerSupport.removeMeasurementListener(l);
    }
    
    @Override
    public void removeMeasurementListener(IBlackboardListener<?, T> l, Probe<?> probe) {
        listenerSupport.removeMeasurementListener(l, probe);
    }

    @Override
    public Class<V> getGenericType() {
        return type;
    }

    @Override
    public IBlackboardReader<V, T> getReader(Probe<V> probe) {
        return readerSupport.getReader(probe, this);
    }
    
    @Override
    public IBlackboardWriter<V> getWriter(Probe<V> probe) {
        return new BlackboardWriter<V, T>(probe, this);
    }

}
