package edu.kit.ipd.sdq.probespec.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;

public class SimpleBlackboardRegion<T> implements IBlackboardRegion<T> {
    
    private List<IBlackboardListener<T>> listeners;

    private Map<Probe<?>, List<IBlackboardListener<T>>> probeListeners;

    
    public SimpleBlackboardRegion() {
        listeners = new ArrayList<IBlackboardListener<T>>();
        probeListeners = new HashMap<Probe<?>, List<IBlackboardListener<T>>>();
    }
    
    @Override
    public void addMeasurement(T measurement, Probe<T> probe) {
        notifyBlackboardListeners(measurement, probe);
    }
    
    @Override
    public void addMeasurement(T measurement, Probe<T> probe, IMeasurementContext context) {
        // TODO
        notifyBlackboardListeners(measurement, probe);
    }
    
    public void notifyBlackboardListeners(T measurement, Probe<T> probe) {
        for (IBlackboardListener<T> l : listeners) {
            l.measurementArrived(measurement, probe);
        }

        if (probeListeners.containsKey(probe)) {
            for (IBlackboardListener<T> l : probeListeners.get(probe)) {
                l.measurementArrived(measurement, probe);
            }
        }
    }

    @Override
    public void addBlackboardListener(IBlackboardListener<T> l) {
        listeners.add(l);
    }

    @Override
    public void addBlackboardListener(IBlackboardListener<T> l, Probe<T> probe) {
        if (!probeListeners.containsKey(probe)) {
            probeListeners.put(probe, new ArrayList<IBlackboardListener<T>>());
        }
        probeListeners.get(probe).add(l);
    }
    
}
