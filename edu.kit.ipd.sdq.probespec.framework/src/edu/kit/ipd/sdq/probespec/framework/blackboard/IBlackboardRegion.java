package edu.kit.ipd.sdq.probespec.framework.blackboard;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public interface IBlackboardRegion<V, T> {

    public void addMeasurement(V value, Probe<V> probe);

    public void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts);

    public Measurement<V, T> getLatestMeasurement(Probe<V> probe);

    public Measurement<V, T> getLatestMeasurement(Probe<V> probe, IMeasurementContext... contexts);

    public void deleteMeasurement(Probe<V> probe, IMeasurementContext... contexts);

    public void deleteMeasurements(IMeasurementContext context);

    public void addMeasurementListener(IBlackboardListener<V, T> l, Probe<V> probe);

    public void addMeasurementListener(IBlackboardListener<V, T> l);

    public void removeMeasurementListener(IBlackboardListener<V, T> l);

    public Class<V> getGenericType();

}
