package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.framework.IMetadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

/**
 * This blackboard discards any measurement that is published at the blackboard. Thus, the methods
 * {@link #getLatestMeasurement(Probe)} and
 * {@link #getLatestMeasurement(Probe, IMeasurementContext...)} return always {@code null}.
 * <p>
 * Use this blackboard, when no measurements are supposed to be stored.
 * 
 * @author Philipp Merkle
 * 
 */
public class NullBlackboard<T> implements IBlackboard<T> {

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe) {
        // nothing to do
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMetadata metadata) {
        // nothing to do
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts) {
        // nothing to do
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMetadata metadata, IMeasurementContext... contexts) {
        // nothing to do
    }

    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        // nothing to do
    }

    @Override
    public <V> void deleteMeasurement(Probe<V> probe, IMeasurementContext... contexts) {
        // nothing to do
    }

    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l, Probe<V> probe) {
        // nothing to do
    }

    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l) {
        // nothing to do
    }

    @Override
    public void removeMeasurementListener(IBlackboardListener<?, T> l) {
        // nothing to do
    }

    @Override
    public void removeMeasurementListener(IBlackboardListener<?, T> l, Probe<?> probe) {
        // nothing to do
    }

    @Override
    public <V> IBlackboardReader<V, T> getReader(Probe<V> probe) {
        return null;
    }

    @Override
    public <V> IBlackboardWriter<V> getWriter(Probe<V> probe) {
        return null;
    }

}
