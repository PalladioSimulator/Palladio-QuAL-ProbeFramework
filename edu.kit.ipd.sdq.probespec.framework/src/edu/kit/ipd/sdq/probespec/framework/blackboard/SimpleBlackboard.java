package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.ITimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public class SimpleBlackboard<T> implements IBlackboard<T> {

    private static final Logger logger = Logger.getLogger(SimpleBlackboard.class);

    private Map<Class<?>, IBlackboardRegion<?, T>> regions;

    private ITimestampGenerator<T> timestampBuilder;

    public SimpleBlackboard(ITimestampGenerator<T> timestampBuilder) {
        this.regions = new HashMap<Class<?>, IBlackboardRegion<?, T>>();
        this.timestampBuilder = timestampBuilder;
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe) {
        createOrFindRegion(probe.getGenericClass()).addMeasurement(value, probe);
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts) {
        createOrFindRegion(probe.getGenericClass()).addMeasurement(value, probe, contexts);
    }

    @Override
    public <V> Measurement<V, T> getLatestMeasurement(Probe<V> probe) {
        return createOrFindRegion(probe.getGenericClass()).getLatestMeasurement(probe);
    }

    @Override
    public <V> Measurement<V, T> getLatestMeasurement(Probe<V> probe, IMeasurementContext... contexts) {
        return createOrFindRegion(probe.getGenericClass()).getLatestMeasurement(probe, contexts);
    }

    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        for (IBlackboardRegion<?, T> r : regions.values()) {
            r.deleteMeasurements(context);
        }
    }

    @Override
    public <V> void deleteMeasurement(Probe<V> probe, IMeasurementContext... contexts) {
        createOrFindRegion(probe.getGenericClass()).deleteMeasurement(probe, contexts);
    }

    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l, Probe<V> probe) {
        createOrFindRegion(probe.getGenericClass()).addMeasurementListener(l, probe);
    }

    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l) {
        createOrFindRegion(l.getGenericType()).addMeasurementListener(l);
    }

    @Override
    public <V> void removeMeasurementListener(IBlackboardListener<V, T> l) {
        createOrFindRegion(l.getGenericType()).removeMeasurementListener(l);
    }

    @Override
    public <V> IBlackboardReader<V, T> getReader(Probe<V> probe) {
        return createOrFindRegion(probe.getGenericClass()).getReader(probe);
    }
    
    private <V> IBlackboardRegion<V, T> createOrFindRegion(Class<V> clazz) {
        @SuppressWarnings("unchecked")
        IBlackboardRegion<V, T> r = (IBlackboardRegion<V, T>) this.regions.get(clazz);
        if (r == null) {
            r = new SimpleBlackboardRegion<V, T>(this, clazz, timestampBuilder);
            this.regions.put(clazz, r);
            logger.debug("Created blackboard region for " + clazz);
        }
        return r;
    }

}
