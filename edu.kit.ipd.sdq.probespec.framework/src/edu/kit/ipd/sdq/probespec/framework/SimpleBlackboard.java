package edu.kit.ipd.sdq.probespec.framework;

import java.util.HashMap;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;

public class SimpleBlackboard implements IBlackboard {

    private Map<Class<?>, IBlackboardRegion<?>> regions;

    public SimpleBlackboard() {
        this.regions = new HashMap<Class<?>, IBlackboardRegion<?>>();
        this.regions.put(Integer.class, new SimpleBlackboardRegion<Integer>());
        this.regions.put(Double.class, new SimpleBlackboardRegion<Double>());
    }

//    public <X extends Object> void addMeasurement(X measurement, Probe<X> probe) {
//        @SuppressWarnings("unchecked")
//        IBlackboardRegion<X> r = (IBlackboardRegion<X>) getRegion(measurement.getClass());
//
//        r.addMeasurement(measurement, probe);
//    }
    
//    public void addIntegerMeasurement(Integer measurement, Probe<Integer> probe, IMeasurementContext context) {
//        
//        throw new RuntimeException("Not yet implemented");
//    }

    public <O> void addMeasurementListener(IBlackboardListener<O> l, Probe<O> probe) {
        IBlackboardRegion<O> r = (IBlackboardRegion<O>) getRegion(l.getGenericType());
        r.addMeasurementListener(l, probe);
    }

    public <O> void addMeasurementListener(IBlackboardListener<O> l) {
        IBlackboardRegion<O> r = (IBlackboardRegion<O>) getRegion(l.getGenericType());
        r.addMeasurementListener(l);
    }

    @Override
    public <O> IBlackboardRegion<O> getRegion(Class<O> clazz) {
        @SuppressWarnings("unchecked")
        IBlackboardRegion<O> r = (IBlackboardRegion<O>) this.regions.get(clazz);
        if (r == null) {
            throw new IllegalStateException("No blackboard region could be found to store a measurement of type "
                    + clazz);
        }
        return r;
    }

}
