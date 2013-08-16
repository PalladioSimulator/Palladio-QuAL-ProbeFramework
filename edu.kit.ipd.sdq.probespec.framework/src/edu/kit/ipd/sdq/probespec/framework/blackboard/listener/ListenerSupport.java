package edu.kit.ipd.sdq.probespec.framework.blackboard.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

public class ListenerSupport<V, T> {

    private List<IBlackboardListener<V, T>> listeners;

    private Map<Probe<?>, List<IBlackboardListener<V, T>>> probeListeners;

    public ListenerSupport() {
        listeners = new ArrayList<IBlackboardListener<V, T>>();
        probeListeners = new HashMap<Probe<?>, List<IBlackboardListener<V, T>>>();
    }

    public void notifyMeasurementListeners(IBlackboard<T> blackboard, Measurement<V, T> measurement, Probe<V> probe,
            IMeasurementContext... contexts) {
        for (IBlackboardListener<V, T> l : listeners) {
            l.measurementArrived(measurement, probe, contexts);
        }

        if (probeListeners.containsKey(probe)) {
            for (IBlackboardListener<V, T> l : probeListeners.get(probe)) {
                l.measurementArrived(measurement, probe, contexts);
            }
        }
    }

    public void addMeasurementListener(IBlackboard<T> blackboard, IBlackboardListener<V, T> l) {
        listeners.add(l);
    }

    public void addMeasurementListener(IBlackboard<T> blackboard, IBlackboardListener<V, T> l, Probe<V> probe) {
        if (!probeListeners.containsKey(probe)) {
            probeListeners.put(probe, new ArrayList<IBlackboardListener<V, T>>());
        }
        probeListeners.get(probe).add(l);
    }

    public void removeMeasurementListener(IBlackboardListener<?, T> l) {
        probeListeners.remove(l);
    }

    public void removeMeasurementListener(IBlackboardListener<?, T> l, Probe<?> probe) {
        if (probeListeners.containsKey(probe)) {
            probeListeners.get(probe).remove(l);
        }
    }

}
