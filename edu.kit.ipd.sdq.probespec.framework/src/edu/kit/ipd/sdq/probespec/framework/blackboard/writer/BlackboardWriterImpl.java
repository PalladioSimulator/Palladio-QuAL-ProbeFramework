package edu.kit.ipd.sdq.probespec.framework.blackboard.writer;

import edu.kit.ipd.sdq.probespec.framework.Metadata;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.SimpleBlackboardRegion;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;

public class BlackboardWriterImpl<V, T> implements BlackboardWriter<V> {

    private Probe<V> probe;

    private SimpleBlackboardRegion<V, T> blackboardRegion;

    public BlackboardWriterImpl(Probe<V> probe, SimpleBlackboardRegion<V, T> blackboardRegion) {
        this.probe = probe;
        this.blackboardRegion = blackboardRegion;
    }

    @Override
    public void addMeasurement(V value) {
        blackboardRegion.addMeasurement(value, probe);
    }

    @Override
    public void addMeasurement(V value, Metadata metadata) {
        blackboardRegion.addMeasurement(value, probe, metadata);
    }

    @Override
    public void addMeasurement(V value, MeasurementContext... contexts) {
        blackboardRegion.addMeasurement(value, probe, contexts);
    }

    @Override
    public void addMeasurement(V value, Metadata metadata, MeasurementContext... contexts) {
        blackboardRegion.addMeasurement(value, probe, metadata, contexts);
    }
    
}
