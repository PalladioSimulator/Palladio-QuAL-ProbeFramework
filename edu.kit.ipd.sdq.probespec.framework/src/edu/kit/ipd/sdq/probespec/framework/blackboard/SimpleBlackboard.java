package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.HashMap;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;

public class SimpleBlackboard implements IBlackboard {

    private Map<Class<?>, IBlackboardRegion<?>> regions;

    public SimpleBlackboard() {
        this.regions = new HashMap<Class<?>, IBlackboardRegion<?>>();
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
    public <T> T getLatestMeasurement(Probe<T> probe) {
        return createOrFindRegion(probe.getGenericClass()).getLatestMeasurement(probe);
    }

    @Override
    public <T> T getLatestMeasurement(Probe<T> probe, IMeasurementContext... contexts) {
        return createOrFindRegion(probe.getGenericClass()).getLatestMeasurement(probe, contexts);
    }

    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        for (IBlackboardRegion<?> r : regions.values()) {
            r.deleteMeasurements(context);
        }
    }

    @Override
    public <T> void deleteMeasurement(Probe<T> probe, IMeasurementContext... contexts) {
        createOrFindRegion(probe.getGenericClass()).deleteMeasurement(probe, contexts);
    }

    @Override
    public <T> void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe) {
        createOrFindRegion(probe.getGenericClass()).addMeasurementListener(l, probe);
    }

    @Override
    public <T> void addMeasurementListener(IBlackboardListener<T> l) {
        createOrFindRegion(l.getGenericType()).addMeasurementListener(l);
    }

    @Override
    public <T> void removeMeasurementListener(IBlackboardListener<T> l) {
        createOrFindRegion(l.getGenericType()).removeMeasurementListener(l);
    }

    private <T> IBlackboardRegion<T> createOrFindRegion(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        IBlackboardRegion<T> r = (IBlackboardRegion<T>) this.regions.get(clazz);
        if (r == null) {
            r = new SimpleBlackboardRegion<T>(this, clazz);
            this.regions.put(clazz, r);
        }
        return r;
    }

}
