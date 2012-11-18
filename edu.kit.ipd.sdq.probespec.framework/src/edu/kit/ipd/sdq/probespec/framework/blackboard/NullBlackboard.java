package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardConsumer;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

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
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts) {
        // nothing to do
    }

    @Override
    public <V> Measurement<V, T> getLatestMeasurement(Probe<V> probe) {
        return null;
    }

    @Override
    public <V> Measurement<V, T> getLatestMeasurement(Probe<V> probe, IMeasurementContext... contexts) {
        return null;
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
    public <V> void addMeasurementListener(IBlackboardConsumer<V, T> l, Probe<V> probe) {
        // nothing to do
    }

    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l) {
        // nothing to do
    }

    @Override
    public <V> void removeMeasurementListener(IBlackboardConsumer<V, T> l) {
        // nothing to do
    }

    @Override
    public <V> void removeMeasurementListener(IBlackboardListener<V, T> l) {
        // nothing to do
    }

}
