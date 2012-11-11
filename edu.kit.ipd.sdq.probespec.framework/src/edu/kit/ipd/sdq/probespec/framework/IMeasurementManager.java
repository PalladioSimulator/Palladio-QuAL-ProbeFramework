package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.Probe;

public interface IMeasurementManager<O> {

    public void addMeasurement(O measurement, Probe<O> probe);
    
    public void addMeasurement(O measurement, Probe<O> probe, IMeasurementContext context);
    
    public O getLatestMeasurement(Probe<O> probe);
    
    public O getLatestMeasurement(Probe<O> probe, IMeasurementContext context);
    
    public void deleteMeasurements(IMeasurementContext context);
    
}
