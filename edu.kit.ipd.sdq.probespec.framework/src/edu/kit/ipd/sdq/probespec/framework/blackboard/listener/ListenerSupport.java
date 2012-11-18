package edu.kit.ipd.sdq.probespec.framework.blackboard.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.ProbeMeasurementsProxy;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;

public class ListenerSupport<V, T> {

    private List<IBlackboardListener<V, T>> listeners;

    private Map<Probe<?>, List<IBlackboardConsumer<V, T>>> probeListeners;

    public ListenerSupport() {
        listeners = new ArrayList<IBlackboardListener<V, T>>();
        probeListeners = new HashMap<Probe<?>, List<IBlackboardConsumer<V, T>>>();
    }

    public void notifyMeasurementListeners(IBlackboard<T> blackboard, Measurement<V, T> measurement, Probe<V> probe,
            IMeasurementContext... contexts) {
        for (IBlackboardListener<V, T> l : listeners) {
            l.measurementArrived(measurement, probe, contexts);
        }

        if (probeListeners.containsKey(probe)) {
            for (IBlackboardConsumer<V, T> l : probeListeners.get(probe)) {
                l.measurementArrived(contexts);
            }
        }
    }

    public void addMeasurementListener(IBlackboard<T> blackboard, IBlackboardListener<V, T> l) {
        listeners.add(l);
    }

    public void addMeasurementListener(IBlackboard<T> blackboard, IBlackboardConsumer<V, T> l, Probe<V> probe) {
        if (!probeListeners.containsKey(probe)) {
            probeListeners.put(probe, new ArrayList<IBlackboardConsumer<V, T>>());
        }
        probeListeners.get(probe).add(l);

        l.setBlackboardProxy(new ProbeMeasurementsProxy<V, T>(probe, blackboard));
    }

    public void removeMeasurementListener(IBlackboardConsumer<V, T> l) {
        // TODO implement method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void removeMeasurementListener(IBlackboardListener<V, T> l) {
        // TODO implement method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean hasConsumers(Probe<V> probe) {
        return probeListeners.containsKey(probe) && probeListeners.get(probe).size() > 0;
    }

}
