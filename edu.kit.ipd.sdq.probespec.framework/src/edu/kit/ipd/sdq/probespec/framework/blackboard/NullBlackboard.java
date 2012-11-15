package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;
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
public class NullBlackboard implements IBlackboard {

    @Override
    public <T> void addMeasurement(T measurement, Probe<T> probe) {
        // nothing to do
    }

    @Override
    public <T> void addMeasurement(T measurement, Probe<T> probe, IMeasurementContext... context) {
        // nothing to do

    }

    @Override
    public <T> T getLatestMeasurement(Probe<T> probe) {
        // nothing to do
        return null;
    }

    @Override
    public <T> T getLatestMeasurement(Probe<T> probe, IMeasurementContext... context) {
        // nothing to do
        return null;
    }

    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        // nothing to do
    }

    @Override
    public <T> void deleteMeasurement(Probe<T> probe, IMeasurementContext... context) {
        // nothing to do
    }

    @Override
    public <T> void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe) {
        // nothing to do
    }

    @Override
    public <T> void addMeasurementListener(IBlackboardListener<T> l) {
        // nothing to do
    }

    @Override
    public <T> void removeMeasurementListener(IBlackboardListener<T> l) {
        // nothing to do
    }

}
