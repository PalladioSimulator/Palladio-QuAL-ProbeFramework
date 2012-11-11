package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;

public class ListenerSupport<T> {

    private List<IBlackboardListener<T>> listeners;

    private Map<Probe<?>, List<IBlackboardListener<T>>> probeListeners;

    public ListenerSupport() {
        listeners = new ArrayList<IBlackboardListener<T>>();
        probeListeners = new HashMap<Probe<?>, List<IBlackboardListener<T>>>();
    }

    public void notifyMeasurementListeners(T measurement, Probe<T> probe, IBlackboard blackboard) {
        for (IBlackboardListener<T> l : listeners) {
            l.measurementArrived(measurement, probe, blackboard);
        }

        if (probeListeners.containsKey(probe)) {
            for (IBlackboardListener<T> l : probeListeners.get(probe)) {
                l.measurementArrived(measurement, probe, blackboard);
            }
        }
    }

    public void addMeasurementListener(IBlackboardListener<T> l) {
        listeners.add(l);
    }

    public void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe) {
        if (!probeListeners.containsKey(probe)) {
            probeListeners.put(probe, new ArrayList<IBlackboardListener<T>>());
        }
        probeListeners.get(probe).add(l);
    }

    public void removeMeasurementListener(IBlackboardListener<T> l) {
        // TODO implement method
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
