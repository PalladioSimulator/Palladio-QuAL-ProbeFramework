package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.List;

import edu.kit.ipd.sdq.probespec.Probe;

public class BlackboardReader<V, T> implements IBlackboardReader<V, T> {

    private Probe<V> probe;

    private IBlackboard<T> blackboard;

    public BlackboardReader(Probe<V> probe, IBlackboard<T> blackboard) {
        this.probe = probe;
        this.blackboard = blackboard;
    }

    @Override
    public Measurement<V, T> getLatestMeasurement() {
        return blackboard.getLatestMeasurement(probe);
    }

    @Override
    public Measurement<V, T> getLatestMeasurement(IMeasurementContext... contexts) {
        return blackboard.getLatestMeasurement(probe, contexts);
    }
    
    @Override
    public Measurement<V, T> getLatestMeasurement(ILookupStrategy lookupStrategy, IMeasurementContext... contexts) {
        for (List<IMeasurementContext> c : lookupStrategy.lookup(contexts)) {
            Measurement<V, T> mm = getLatestMeasurement(c.toArray(new IMeasurementContext[c.size()]));
            if (mm != null) {
                return mm;
            }
        }
        return null;
    }

    @SuppressWarnings("hiding")
    public <V> void addMeasurement(V value, Probe<V> probe) {
        blackboard.addMeasurement(value, probe);
    }

    @SuppressWarnings("hiding")
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts) {
        blackboard.addMeasurement(value, probe, contexts);
    }

    @Override
    public Probe<V> getProbe() {
        return probe;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

}
