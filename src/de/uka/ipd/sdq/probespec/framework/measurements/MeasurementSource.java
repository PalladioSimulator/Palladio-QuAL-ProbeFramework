package de.uka.ipd.sdq.probespec.framework.measurements;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;

public abstract class MeasurementSource extends MetricEntity implements IMeasurementSource {

    private final Set<IMeasurementSourceListener> measurementSourceListeners = new HashSet<IMeasurementSourceListener>();

    public MeasurementSource(final MetricDescription metricDesciption) {
        super(metricDesciption);
    }

    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.probespec.framework.measurements.IMeasurementSource#registerMeasurementSourceListener(de.uka.ipd.sdq.probespec.framework.measurements.IMeasurementSourceListener)
     */
    @Override
    public void registerMeasurementSourceListener(final IMeasurementSourceListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("A valid listener has to be supplied");
        }
        measurementSourceListeners.add(listener);
    }

    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.probespec.framework.measurements.IMeasurementSource#unregisterMeasurementSourceListener(de.uka.ipd.sdq.probespec.framework.measurements.IMeasurementSourceListener)
     */
    @Override
    public void unregisterMeasurementSourceListener(final IMeasurementSourceListener listener) {
        if (!measurementSourceListeners.contains(listener)) {
            throw new IllegalArgumentException("A registered listener has to be supplied");
        }
        measurementSourceListeners.remove(listener);
    }

    /**
     * @param measurement
     */
    protected void notifyMeasurementSourceListener(final Measurement measurement) {
        if (!isCompatibleMeasurement(measurement)) {
            throw new IllegalArgumentException("Taken measurement has an incompatible metric");
        }
        if (measurementSourceListeners.size() > 0) {
            for (final IMeasurementSourceListener listener : measurementSourceListeners) {
                listener.newMeasurementAvailable(measurement);
            }
        }
    }

    protected Collection<IMeasurementSourceListener> getMeasurementSourceListeners() {
        return Collections.unmodifiableCollection(this.measurementSourceListeners);
    }
}
