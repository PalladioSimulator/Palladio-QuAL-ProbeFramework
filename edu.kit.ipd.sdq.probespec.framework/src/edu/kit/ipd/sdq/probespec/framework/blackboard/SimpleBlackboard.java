package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.IMetadata;
import edu.kit.ipd.sdq.probespec.framework.TimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.BlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public class SimpleBlackboard<T> implements Blackboard<T> {

    private static final Logger logger = Logger.getLogger(SimpleBlackboard.class);

    private Map<Class<?>, BlackboardRegion<?, T>> regions;

    private TimestampGenerator<T> timestampBuilder;

    public SimpleBlackboard(TimestampGenerator<T> timestampBuilder) {
        this.regions = new HashMap<Class<?>, BlackboardRegion<?, T>>();
        this.timestampBuilder = timestampBuilder;
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe) {
        createOrFindRegion(probe.getGenericClass()).addMeasurement(value, probe);
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMetadata metadata) {
        createOrFindRegion(probe.getGenericClass()).addMeasurement(value, probe, metadata);
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, MeasurementContext... contexts) {
        createOrFindRegion(probe.getGenericClass()).addMeasurement(value, probe, contexts);
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMetadata metadata,
            MeasurementContext... contexts) {
        createOrFindRegion(probe.getGenericClass()).addMeasurement(value, probe, metadata, contexts);
    }

    @Override
    public void deleteMeasurements(MeasurementContext context) {
        for (BlackboardRegion<?, T> r : regions.values()) {
            r.deleteMeasurements(context);
        }
    }

    @Override
    public <V> void deleteMeasurement(Probe<V> probe, MeasurementContext... contexts) {
        createOrFindRegion(probe.getGenericClass()).deleteMeasurement(probe, contexts);
    }

    @Override
    public <V> void addMeasurementListener(BlackboardListener<V, T> l, Probe<V> probe) {
        createOrFindRegion(probe.getGenericClass()).addMeasurementListener(l, probe);
    }

    @Override
    public <V> void addMeasurementListener(BlackboardListener<V, T> l) {
        createOrFindRegion(l.getGenericType()).addMeasurementListener(l);
    }

    @Override
    public void removeMeasurementListener(BlackboardListener<?, T> l) {
        createOrFindRegion(l.getGenericType()).removeMeasurementListener(l);
    }
    
    @Override
    public void removeMeasurementListener(BlackboardListener<?, T> l, Probe<?> probe) {
        createOrFindRegion(l.getGenericType()).removeMeasurementListener(l, probe);
    }

    @Override
    public <V> BlackboardReader<V, T> getReader(Probe<V> probe) {
        return createOrFindRegion(probe.getGenericClass()).getReader(probe);
    }

    @Override
    public <V> BlackboardWriter<V> getWriter(Probe<V> probe) {
        return createOrFindRegion(probe.getGenericClass()).getWriter(probe);
    }

    private <V> BlackboardRegion<V, T> createOrFindRegion(Class<V> clazz) {
        @SuppressWarnings("unchecked")
        BlackboardRegion<V, T> r = (BlackboardRegion<V, T>) this.regions.get(clazz);
        if (r == null) {
            r = new SimpleBlackboardRegion<V, T>(this, clazz, timestampBuilder);
            this.regions.put(clazz, r);
            logger.debug("Created blackboard region for " + clazz);
        }
        return r;
    }

}
