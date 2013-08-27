package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.framework.MeasurementListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public class ListenerSupport<V, T> {

    private List<MeasurementListener<V, T>> listeners;

    private Map<Probe<?>, List<MeasurementListener<V, T>>> probeListeners;

    public ListenerSupport() {
        listeners = new ArrayList<MeasurementListener<V, T>>();
        probeListeners = new HashMap<Probe<?>, List<MeasurementListener<V, T>>>();
    }

    public void notifyMeasurementListeners(Blackboard<T> blackboard, Measurement<V, T> measurement, Probe<V> probe,
            MeasurementContext... contexts) {
        for (MeasurementListener<V, T> l : listeners) {
            l.measurementArrived(measurement, probe, contexts);
        }

        if (probeListeners.containsKey(probe)) {
            for (MeasurementListener<V, T> l : probeListeners.get(probe)) {
                l.measurementArrived(measurement, probe, contexts);
            }
        }
    }

    public void addMeasurementListener(Blackboard<T> blackboard, MeasurementListener<V, T> l) {
        listeners.add(l);
    }

    public void addMeasurementListener(Blackboard<T> blackboard, MeasurementListener<V, T> l, Probe<V> probe) {
        if (!probeListeners.containsKey(probe)) {
            probeListeners.put(probe, new ArrayList<MeasurementListener<V, T>>());
        }
        probeListeners.get(probe).add(l);
    }

    public void removeMeasurementListener(MeasurementListener<?, T> l) {
        probeListeners.remove(l);
    }

    public void removeMeasurementListener(MeasurementListener<?, T> l, Probe<?> probe) {
        if (probeListeners.containsKey(probe)) {
            probeListeners.get(probe).remove(l);
        }
    }

}
