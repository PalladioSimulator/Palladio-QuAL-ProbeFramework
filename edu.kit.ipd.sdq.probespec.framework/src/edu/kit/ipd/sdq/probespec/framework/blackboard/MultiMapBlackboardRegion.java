package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import edu.kit.ipd.sdq.probespec.Probe;

public class MultiMapBlackboardRegion<T> implements IBlackboardRegion<T> {

    private Class<T> type;

    private IBlackboard blackboard;

    private ListenerSupport<T> listenerSupport;

    private Map<Probe<T>, Measurement<T>> contextlessMeasurements;

    private Map<Class<?>, Multimap<Probe<T>, Measurement<T>>> measurements;

    public MultiMapBlackboardRegion(IBlackboard blackboard, Class<T> type) {
        this.type = type;
        this.blackboard = blackboard;
        listenerSupport = new ListenerSupport<T>();
        contextlessMeasurements = new HashMap<Probe<T>, Measurement<T>>();
        measurements = new HashMap<Class<?>, Multimap<Probe<T>, Measurement<T>>>();
    }

    @Override
    public void addMeasurement(T measurement, Probe<T> probe) {
        System.out.println("Measurement added for probe " + probe.getName() + ": " + measurement);

        Measurement<T> mm = new Measurement<T>(0, measurement);

        // add measurement
        contextlessMeasurements.put(probe, mm);

        // notify listeners
        listenerSupport.notifyMeasurementListeners(measurement, probe, blackboard);
    }

    @Override
    public void addMeasurement(T measurement, Probe<T> probe, IMeasurementContext<T>... context) {
        System.out.println("Measurement added for probe " + probe.getName() + ": " + measurement);

        Measurement<T> mm = new Measurement<T>(0, measurement);
        for (IMeasurementContext<T> ctx : context) {
            if (!measurements.containsKey(ctx.getClass())) {
                // Object o = HashMultimap<Probe<T>, Measurement<T>>.create();
                Multimap<Probe<T>, Measurement<T>> m = HashMultimap.create();

                measurements.put(ctx.getClass(), m);
            }

            Multimap<Probe<T>, Measurement<T>> m = measurements.get(ctx.getClass());
            // TODO timestamp == 0

            m.put(probe, mm);
        }

        // notify listeners
        listenerSupport.notifyMeasurementListeners(measurement, probe, blackboard);
    }

    @Override
    public T getLatestMeasurement(Probe<T> probe) {
        return contextlessMeasurements.get(probe).getValue();
    }

    @Override
    public T getLatestMeasurement(Probe<T> probe, IMeasurementContext<T> context) {
        // Map<Probe<T>, T> measurementsInContext = measurements.get(context);
        // if (measurementsInContext == null) {
        // throw new RuntimeException("No such context found: " + context);
        // }
        //
        // T measurement = measurementsInContext.get(probe);
        // if (measurement == null) {
        // throw new RuntimeException("No measurement found for probe " + probe + " in context " +
        // context);
        // }

        return null;
    }

    @Override
    public void deleteMeasurements(IMeasurementContext<T> context) {
        // TODO Auto-generated method stub
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void addMeasurementListener(IBlackboardListener<T> l) {
        listenerSupport.addMeasurementListener(l);
    }

    @Override
    public void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe) {
        listenerSupport.addMeasurementListener(l, probe);
    }

    @Override
    public void removeMeasurementListener(IBlackboardListener<T> l) {
        listenerSupport.removeMeasurementListener(l);
    }

    @Override
    public Class<T> getGenericType() {
        return type;
    }

}
