package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.ITimestampBuilder;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public class SimpleBlackboard<U> implements IBlackboard<U> {

    private static final Logger logger = Logger.getLogger(SimpleBlackboard.class);

    private Map<Class<?>, IBlackboardRegion<?, U>> regions;

    private ITimestampBuilder<U> timestampBuilder;
    
    public SimpleBlackboard(ITimestampBuilder<U> timestampBuilder) {
        this.regions = new HashMap<Class<?>, IBlackboardRegion<?, U>>();
        this.timestampBuilder = timestampBuilder;
    }

    @Override
    public <T> void addMeasurement(T measurement, Probe<T> probe) {
        createOrFindRegion(probe.getGenericClass()).addMeasurement(measurement, probe);
    }

    @Override
    public <T> void addMeasurement(T measurement, Probe<T> probe, IMeasurementContext... contexts) {
        createOrFindRegion(probe.getGenericClass()).addMeasurement(measurement, probe, contexts);
    }

    @Override
    public <T> Measurement<T, U> getLatestMeasurement(Probe<T> probe) {
        return createOrFindRegion(probe.getGenericClass()).getLatestMeasurement(probe);
    }

    @Override
    public <T> Measurement<T, U> getLatestMeasurement(Probe<T> probe, IMeasurementContext... contexts) {
        return createOrFindRegion(probe.getGenericClass()).getLatestMeasurement(probe, contexts);
    }

    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        for (IBlackboardRegion<?, U> r : regions.values()) {
            r.deleteMeasurements(context);
        }
    }

    @Override
    public <T> void deleteMeasurement(Probe<T> probe, IMeasurementContext... contexts) {
        createOrFindRegion(probe.getGenericClass()).deleteMeasurement(probe, contexts);
    }

    @Override
    public <T> void addMeasurementListener(IBlackboardListener<T, U> l, Probe<T> probe) {
        createOrFindRegion(probe.getGenericClass()).addMeasurementListener(l, probe);
    }

    @Override
    public <T> void addMeasurementListener(IBlackboardListener<T, U> l) {
        createOrFindRegion(l.getGenericType()).addMeasurementListener(l);
    }

    @Override
    public <T> void removeMeasurementListener(IBlackboardListener<T, U> l) {
        createOrFindRegion(l.getGenericType()).removeMeasurementListener(l);
    }

    private <T> IBlackboardRegion<T, U> createOrFindRegion(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        IBlackboardRegion<T, U> r = (IBlackboardRegion<T, U>) this.regions.get(clazz);
        if (r == null) {
            r = new SimpleBlackboardRegion<T, U>(this, clazz, timestampBuilder);
            this.regions.put(clazz, r);
            logger.debug("Created blackboard region for " + clazz);
        }
        return r;
    }

}
