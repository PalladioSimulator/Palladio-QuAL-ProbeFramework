package edu.kit.ipd.sdq.probespec.framework.blackboard.writer;

import edu.kit.ipd.sdq.probespec.framework.IMetadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.SimpleBlackboardRegion;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

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
    public void addMeasurement(V value, IMetadata metadata) {
        blackboardRegion.addMeasurement(value, probe, metadata);
    }

    @Override
    public void addMeasurement(V value, MeasurementContext... contexts) {
        blackboardRegion.addMeasurement(value, probe, contexts);
    }

    @Override
    public void addMeasurement(V value, IMetadata metadata, MeasurementContext... contexts) {
        blackboardRegion.addMeasurement(value, probe, metadata, contexts);
    }
    
}
