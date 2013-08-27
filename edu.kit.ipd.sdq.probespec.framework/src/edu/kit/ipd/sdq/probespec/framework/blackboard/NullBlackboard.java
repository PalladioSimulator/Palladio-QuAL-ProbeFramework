package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.framework.IMetadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.BlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

/**
 * This blackboard discards any measurement that is published at the blackboard. Thus, the methods
 * {@link #getLatestMeasurement(Probe)} and
 * {@link #getLatestMeasurement(Probe, MeasurementContext...)} return always {@code null}.
 * <p>
 * Use this blackboard, when no measurements are supposed to be stored.
 * 
 * @author Philipp Merkle
 * 
 */
public class NullBlackboard<T> implements Blackboard<T> {

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe) {
        // nothing to do
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMetadata metadata) {
        // nothing to do
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, MeasurementContext... contexts) {
        // nothing to do
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMetadata metadata, MeasurementContext... contexts) {
        // nothing to do
    }

    @Override
    public void deleteMeasurements(MeasurementContext context) {
        // nothing to do
    }

    @Override
    public <V> void deleteMeasurement(Probe<V> probe, MeasurementContext... contexts) {
        // nothing to do
    }

    @Override
    public <V> void addMeasurementListener(BlackboardListener<V, T> l, Probe<V> probe) {
        // nothing to do
    }

    @Override
    public <V> void addMeasurementListener(BlackboardListener<V, T> l) {
        // nothing to do
    }

    @Override
    public void removeMeasurementListener(BlackboardListener<?, T> l) {
        // nothing to do
    }

    @Override
    public void removeMeasurementListener(BlackboardListener<?, T> l, Probe<?> probe) {
        // nothing to do
    }

    @Override
    public <V> BlackboardReader<V, T> getReader(Probe<V> probe) {
        return null;
    }

    @Override
    public <V> BlackboardWriter<V> getWriter(Probe<V> probe) {
        return null;
    }

}
