package edu.kit.ipd.sdq.probespec.framework.blackboard.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.framework.blackboard.Blackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public class ListenerSupport<V, T> {

    private List<BlackboardListener<V, T>> listeners;

    private Map<Probe<?>, List<BlackboardListener<V, T>>> probeListeners;

    public ListenerSupport() {
        listeners = new ArrayList<BlackboardListener<V, T>>();
        probeListeners = new HashMap<Probe<?>, List<BlackboardListener<V, T>>>();
    }

    public void notifyMeasurementListeners(Blackboard<T> blackboard, Measurement<V, T> measurement, Probe<V> probe,
            MeasurementContext... contexts) {
        for (BlackboardListener<V, T> l : listeners) {
            l.measurementArrived(measurement, probe, contexts);
        }

        if (probeListeners.containsKey(probe)) {
            for (BlackboardListener<V, T> l : probeListeners.get(probe)) {
                l.measurementArrived(measurement, probe, contexts);
            }
        }
    }

    public void addMeasurementListener(Blackboard<T> blackboard, BlackboardListener<V, T> l) {
        listeners.add(l);
    }

    public void addMeasurementListener(Blackboard<T> blackboard, BlackboardListener<V, T> l, Probe<V> probe) {
        if (!probeListeners.containsKey(probe)) {
            probeListeners.put(probe, new ArrayList<BlackboardListener<V, T>>());
        }
        probeListeners.get(probe).add(l);
    }

    public void removeMeasurementListener(BlackboardListener<?, T> l) {
        probeListeners.remove(l);
    }

    public void removeMeasurementListener(BlackboardListener<?, T> l, Probe<?> probe) {
        if (probeListeners.containsKey(probe)) {
            probeListeners.get(probe).remove(l);
        }
    }

}
