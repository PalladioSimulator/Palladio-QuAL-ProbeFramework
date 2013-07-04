package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.ITimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.ProbeManager;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementMetadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;

public class JavaProbeManager extends ProbeManager<Long> implements IBlackboard<Long> {

    private static final ITimestampGenerator<Long> TIMESTAMP_GENERFATOR = new JavaTimestampBuilder();

    public JavaProbeManager() {
        super(TIMESTAMP_GENERFATOR);
    }

    public JavaProbeManager(BlackboardType blackboardType) {
        super(TIMESTAMP_GENERFATOR, blackboardType);
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe) {
        getBlackboard().addMeasurement(value, probe);
    }
    
    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementMetadata metadata) {
        getBlackboard().addMeasurement(value, probe, metadata);
    }
    
    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts) {
        getBlackboard().addMeasurement(value, probe, contexts);
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementMetadata metadata, IMeasurementContext... contexts) {
        getBlackboard().addMeasurement(value, probe, metadata, contexts);
    }
    
    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        getBlackboard().deleteMeasurements(context);
    }

    @Override
    public <V> void deleteMeasurement(Probe<V> probe, IMeasurementContext... contexts) {
        getBlackboard().deleteMeasurement(probe, contexts);
    }

    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, Long> l, Probe<V> probe) {
        getBlackboard().addMeasurementListener(l, probe);
    }

    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, Long> l) {
        getBlackboard().addMeasurementListener(l);
    }
    
    @Override
    public <V> void removeMeasurementListener(IBlackboardListener<V, Long> l) {
        getBlackboard().removeMeasurementListener(l);
    }
    
    @Override
    public <V> IBlackboardReader<V, Long> getReader(Probe<V> probe) {
    	return getBlackboard().getReader(probe);
    }
    
    @Override
    public <V> IBlackboardWriter<V> getWriter(Probe<V> probe) {
        return getBlackboard().getWriter(probe);
    }

}
