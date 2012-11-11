package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.HashMap;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;

public class SimpleBlackboardRegion<T> implements IBlackboardRegion<T> {

    private Class<T> type;
    
    private IBlackboard blackboard;

    private ListenerSupport<T> listenerSupport;
    
    private Map<IMeasurementContext, Map<Probe<T>, T>> measurements;

    public SimpleBlackboardRegion(IBlackboard blackboard, Class<T> type) {
        this.type = type;
        this.blackboard = blackboard;
        listenerSupport = new ListenerSupport<T>();
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
        listenerSupport.notifyMeasurementListeners(measurement, probe, blackboard);
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

    @Override
    public void addMeasurementListener(IBlackboardListener<T> l) {
        listenerSupport.addMeasurementListener(l);
    }

    @Override
    public void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe) {
        listenerSupport.addMeasurementListener(l, probe);
    }
    
    @Override
    public Class<T> getGenericType() {
        return type;
    }

}
