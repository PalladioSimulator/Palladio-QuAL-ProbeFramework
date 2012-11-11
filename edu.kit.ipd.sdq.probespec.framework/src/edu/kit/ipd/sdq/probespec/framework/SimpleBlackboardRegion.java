package edu.kit.ipd.sdq.probespec.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;

public class SimpleBlackboardRegion<T> implements IBlackboardRegion<T> {

    private List<IBlackboardListener<T>> listeners;

    private Map<Probe<?>, List<IBlackboardListener<T>>> probeListeners;

    private Map<IMeasurementContext, Map<Probe<T>, T>> measurements;

    public SimpleBlackboardRegion() {
        listeners = new ArrayList<IBlackboardListener<T>>();
        probeListeners = new HashMap<Probe<?>, List<IBlackboardListener<T>>>();
        measurements = new HashMap<IMeasurementContext, Map<Probe<T>, T>>();
    }

    @Override
    public void addMeasurement(T measurement, Probe<T> probe) {
        addMeasurement(measurement, probe, IMeasurementContext.NULL);
    }

    @Override
    public void addMeasurement(T measurement, Probe<T> probe, IMeasurementContext context) {
        System.out.println("Measurement added for probe " + probe.getName() + ": " + measurement);

        // create hash map for measurement context, if not done yet
        if (!measurements.containsKey(IMeasurementContext.NULL)) {
            Map<Probe<T>, T> m = new HashMap<Probe<T>, T>();
            measurements.put(IMeasurementContext.NULL, m);
        }

        // add measurement
        Map<Probe<T>, T> measurementsInContext = measurements.get(IMeasurementContext.NULL);
        measurementsInContext.put(probe, measurement);

        // notify listeners
        notifyMeasurementListeners(measurement, probe);
    }

    public void notifyMeasurementListeners(T measurement, Probe<T> probe) {
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
    public void addMeasurementListener(IBlackboardListener<T> l) {
        listeners.add(l);
    }

    @Override
    public void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe) {
        if (!probeListeners.containsKey(probe)) {
            probeListeners.put(probe, new ArrayList<IBlackboardListener<T>>());
        }
        probeListeners.get(probe).add(l);
    }

    @Override
    public T getLatestMeasurement(Probe<T> probe) {
        return getLatestMeasurement(probe, IMeasurementContext.NULL);
    }

    @Override
    public T getLatestMeasurement(Probe<T> probe, IMeasurementContext context) {
        Map<Probe<T>, T> measurementsInContext = measurements.get(context);
        if (measurementsInContext == null) {
            throw new RuntimeException("No such context found: " + context);
        }

        T measurement = measurementsInContext.get(probe);
        if (measurement == null) {
            throw new RuntimeException("No measurement found for probe " + probe + " in context " + context);
        }

        return measurement;
    }

    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        // TODO Auto-generated method stub
        throw new RuntimeException("Not yet implemented");
    }

}
