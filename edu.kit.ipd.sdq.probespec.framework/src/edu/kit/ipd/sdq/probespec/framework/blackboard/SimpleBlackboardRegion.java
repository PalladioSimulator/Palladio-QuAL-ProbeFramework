package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.MeasurementListener;
import edu.kit.ipd.sdq.probespec.framework.Metadata;
import edu.kit.ipd.sdq.probespec.framework.TimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.index.IndexManager;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReaderSupport;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriterImpl;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public class SimpleBlackboardRegion<V, T> implements BlackboardRegion<V, T> {

    private static final Logger logger = Logger.getLogger(SimpleBlackboardRegion.class);

    /**
     * Use this metadata-key to dictate the timestamp of a measurement added via the
     * addMeasurement-method (make sure to use a signature that accepts metadata). The value
     * corresponding to the key replaces the actual timestamp. Make sure that the value is of type
     * <code>T</code> (generic class parameter).
     * <p>
     * Doing so should be considered a last resort; usually, it is totally appropriate that
     * timestamps are generated automatically in the background using an {@link TimestampGenerator}.
     */
    public static final Integer TIMESTAMP_OVERRIDE_METADATA_KEY = 0xf00;

    private Class<V> type;

    private Blackboard<T> blackboard;

    private ListenerSupport<V, T> listenerSupport;

    private BlackboardReaderSupport<V, T> readerSupport;

    private Map<String, Measurement<V, T>> measurements;

    private IndexManager indices;

    private KeyBuilder keyBuilder;

    private TimestampGenerator<T> timestampBuilder;

    public SimpleBlackboardRegion(Blackboard<T> blackboard, Class<V> type, TimestampGenerator<T> timestampBuilder) {
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
    public void addMeasurement(V value, Probe<V> probe, MeasurementContext... contexts) {
        addMeasurement(value, probe, Metadata.EMPTY_METADATA, contexts);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addMeasurement(V value, Probe<V> probe, Metadata metadata, MeasurementContext... contexts) {
        // generate timestamp or get timestamp from metadata if one is supplied
        T timestamp;
        if (!metadata.containsKey(TIMESTAMP_OVERRIDE_METADATA_KEY)) {
            timestamp = timestampBuilder.now();
        } else {
            // we assume that the timestamp is of type T
            timestamp = (T) metadata.get(TIMESTAMP_OVERRIDE_METADATA_KEY);
        }

        // wrap value into measurement object
        Measurement<V, T> measurement = new Measurement<V, T>(value, timestamp, probe);

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
    public Measurement<V, T> getLatestMeasurement(Probe<V> probe, MeasurementContext... contexts) {
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
    public void deleteMeasurements(MeasurementContext context) {
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
    public void deleteMeasurement(Probe<V> probe, MeasurementContext... contexts) {
        // create composite key
        String key = keyBuilder.createKey(probe, contexts);

        // delete measurement identified by composite key
        measurements.remove(key);
    }

    @Override
    public void addMeasurementListener(MeasurementListener<V, T> l) {
        listenerSupport.addMeasurementListener(blackboard, l);
    }

    @Override
    public void addMeasurementListener(MeasurementListener<V, T> l, Probe<V> probe) {
        listenerSupport.addMeasurementListener(blackboard, l, probe);
    }

    @Override
    public void removeMeasurementListener(MeasurementListener<?, T> l) {
        listenerSupport.removeMeasurementListener(l);
    }

    @Override
    public void removeMeasurementListener(MeasurementListener<?, T> l, Probe<?> probe) {
        listenerSupport.removeMeasurementListener(l, probe);
    }

    @Override
    public Class<V> getGenericType() {
        return type;
    }

    @Override
    public BlackboardReader<V, T> getReader(Probe<V> probe) {
        return readerSupport.getReader(probe, this);
    }

    @Override
    public BlackboardWriter<V> getWriter(Probe<V> probe) {
        return new BlackboardWriterImpl<V, T>(probe, this);
    }

}
