package edu.kit.ipd.sdq.probespec.framework.blackboard.writer;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementMetadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.SimpleBlackboardRegion;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

public class BlackboardWriter<V, T> implements IBlackboardWriter<V> {

    private Probe<V> probe;

    private SimpleBlackboardRegion<V, T> blackboardRegion;

    public BlackboardWriter(Probe<V> probe, SimpleBlackboardRegion<V, T> blackboardRegion) {
        this.probe = probe;
        this.blackboardRegion = blackboardRegion;
    }

    @Override
    public void addMeasurement(V value) {
        blackboardRegion.addMeasurement(value, probe);
    }

    @Override
    public void addMeasurement(V value, IMeasurementMetadata metadata) {
        blackboardRegion.addMeasurement(value, probe, metadata);
    }

    @Override
    public void addMeasurement(V value, IMeasurementContext... contexts) {
        blackboardRegion.addMeasurement(value, probe, contexts);
    }

    @Override
    public void addMeasurement(V value, IMeasurementMetadata metadata, IMeasurementContext... contexts) {
        blackboardRegion.addMeasurement(value, probe, metadata, contexts);
    }
    
}
