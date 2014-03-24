package de.uka.ipd.sdq.probespec.framework.measurements;

public interface IMeasurementSource {

    public abstract void registerMeasurementSourceListener(IMeasurementSourceListener listener);

    public abstract void unregisterMeasurementSourceListener(IMeasurementSourceListener listener);

}