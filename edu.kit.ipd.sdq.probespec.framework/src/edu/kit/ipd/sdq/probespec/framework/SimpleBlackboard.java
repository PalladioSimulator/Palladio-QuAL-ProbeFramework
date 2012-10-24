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

    public <O> void addMeasurement(O measurement, Probe<O> probe) {
        @SuppressWarnings("unchecked")
        IBlackboardRegion<O> r = (IBlackboardRegion<O>) getRegionForClass(measurement.getClass());

        System.out.println("Measurement added for probe " + probe.getName() + ": " + measurement);

        r.addMeasurement(measurement, probe);
    }

    public <O> void addBlackboardListener(IBlackboardListener<O> l, Probe<O> probe) {
        IBlackboardRegion<O> r = (IBlackboardRegion<O>) getRegionForClass(l.getGenericType());
        r.addBlackboardListener(l, probe);
    }

    public <O> void addBlackboardListener(IBlackboardListener<O> l) {
        IBlackboardRegion<O> r = (IBlackboardRegion<O>) getRegionForClass(l.getGenericType());
        r.addBlackboardListener(l);
    }

    private <O> IBlackboardRegion<O> getRegionForClass(Class<O> clazz) {
        @SuppressWarnings("unchecked")
        IBlackboardRegion<O> r = (IBlackboardRegion<O>) this.regions.get(clazz);
        if (r == null) {
            throw new IllegalStateException("No blackboard region could be found to store a measurement of type "
                    + clazz);
        }
        return r;
    }

}
