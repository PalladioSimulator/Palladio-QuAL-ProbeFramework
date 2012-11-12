package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.HashMap;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;

public class SimpleBlackboard implements IBlackboard {

    private Map<Class<?>, IBlackboardRegion<?>> regions;

    public SimpleBlackboard() {
        this.regions = new HashMap<Class<?>, IBlackboardRegion<?>>();
        this.regions.put(Integer.class, new MultiMapBlackboardRegion<Integer>(this, Integer.class));
        this.regions.put(Double.class, new MultiMapBlackboardRegion<Double>(this, Double.class));
    }

    @Override
    public <T> void addMeasurement(T measurement, Probe<T> probe) {
        addMeasurement(measurement, probe, IMeasurementContext.NULL);
    }

    @Override
    public <T> void addMeasurement(T measurement, Probe<T> probe, IMeasurementContext... context) {
        // TODO
        getRegion(probe.getGenericClass()).addMeasurement(measurement, probe);
    }

    @Override
    public <T> T getLatestMeasurement(Probe<T> probe) {
        return getRegion(probe.getGenericClass()).getLatestMeasurement(probe);
    }

    @Override
    public <T> T getLatestMeasurement(Probe<T> probe, IMeasurementContext context) {
        return getRegion(probe.getGenericClass()).getLatestMeasurement(probe, context);
    }

    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public <T> void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe) {
        getRegion(probe.getGenericClass()).addMeasurementListener(l, probe);
    }

    @Override
    public <T> void addMeasurementListener(IBlackboardListener<T> l) {
        getRegion(l.getGenericType()).addMeasurementListener(l);
    }
    
    private <T> IBlackboardRegion<T> getRegion(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        IBlackboardRegion<T> r = (IBlackboardRegion<T>) this.regions.get(clazz);
        if (r == null) {
            throw new IllegalStateException("No blackboard region could be found for type " + clazz);
        }
        return r;
    }

    @Override
    public <T> void removeMeasurementListener(IBlackboardListener<T> l) {
        getRegion(l.getGenericType()).removeMeasurementListener(l);
    }

}
