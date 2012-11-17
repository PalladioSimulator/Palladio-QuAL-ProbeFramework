package edu.kit.ipd.sdq.probespec.framework.blackboard.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;

public class ListenerSupport<T, U> {

    private List<IBlackboardListener<T, U>> listeners;

    private Map<Probe<?>, List<IBlackboardListener<T, U>>> probeListeners;

    public ListenerSupport() {
        listeners = new ArrayList<IBlackboardListener<T, U>>();
        probeListeners = new HashMap<Probe<?>, List<IBlackboardListener<T, U>>>();
    }

    public void notifyMeasurementListeners(IBlackboard<U> blackboard, Measurement<T, U> measurement, Probe<T> probe, IMeasurementContext... contexts) {
        for (IBlackboardListener<T, U> l : listeners) {
            l.measurementArrived(blackboard, measurement, probe, contexts);
        }

        if (probeListeners.containsKey(probe)) {
            for (IBlackboardListener<T, U> l : probeListeners.get(probe)) {
                l.measurementArrived(blackboard, measurement, probe, contexts);
            }
        }
    }

    public void addMeasurementListener(IBlackboardListener<T, U> l) {
        listeners.add(l);
    }

    public void addMeasurementListener(IBlackboardListener<T, U> l, Probe<T> probe) {
        if (!probeListeners.containsKey(probe)) {
            probeListeners.put(probe, new ArrayList<IBlackboardListener<T, U>>());
        }
        probeListeners.get(probe).add(l);
    }

    public void removeMeasurementListener(IBlackboardListener<T, U> l) {
        // TODO implement method
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
