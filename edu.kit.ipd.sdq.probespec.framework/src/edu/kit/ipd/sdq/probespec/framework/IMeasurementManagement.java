package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.Probe;

public interface IMeasurementManagement<T> {

    public void addMeasurement(T measurement, Probe<T> probe);

    public void addMeasurement(T measurement, Probe<T> probe, IMeasurementContext context);

}
