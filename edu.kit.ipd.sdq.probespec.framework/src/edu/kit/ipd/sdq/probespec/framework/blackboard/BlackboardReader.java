package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;

public class BlackboardReader<V, T> implements IBlackboardReader<V, T> {

    private Probe<V> probe;
    
    private IBlackboard<T> blackboard;

    public BlackboardReader(Probe<V> probe, IBlackboard<T> blackboard) {
        this.probe = probe;
        this.blackboard = blackboard;
    }
    
    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardReader#getLatestMeasurement()
     */
    @Override
    public Measurement<V, T> getLatestMeasurement() {
        return blackboard.getLatestMeasurement(probe);
    }
    
    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardReader#getLatestMeasurement(edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext)
     */
    @Override
    public Measurement<V, T> getLatestMeasurement(IMeasurementContext... contexts) {
        return blackboard.getLatestMeasurement(probe, contexts);
    }
    
    @SuppressWarnings("hiding")
    public <V> void addMeasurement(V value, Probe<V> probe) {
        blackboard.addMeasurement(value, probe);
    }

    @SuppressWarnings("hiding")
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts) {
        blackboard.addMeasurement(value, probe, contexts);
    }

    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardReader#getProbe()
     */
    @Override
    public Probe<V> getProbe() {
        return probe;
    }
    
}
