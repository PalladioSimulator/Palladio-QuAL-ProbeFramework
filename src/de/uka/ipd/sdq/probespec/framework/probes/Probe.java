package de.uka.ipd.sdq.probespec.framework.probes;

import java.util.HashSet;
import java.util.Set;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;

import de.uka.ipd.sdq.probespec.framework.measurements.IMeasurementSourceListener;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSource;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

public abstract class Probe extends MeasurementSource {

    private final Set<IMeasurementSourceListener> probeListeners = new HashSet<IMeasurementSourceListener>();

    protected Probe(final MetricDescription metricDesciption) {
        super(metricDesciption);
    }

    public void registerProbeListener(final IMeasurementSourceListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("A valid listener has to be supplied");
        }
        probeListeners.add(listener);
    }

    public void unregisterProbeListener(final IMeasurementSourceListener listener) {
        if (!probeListeners.contains(listener)) {
            throw new IllegalArgumentException("A registered listener has to be supplied");
        }
        probeListeners.remove(listener);
    }

    public final Measurement takeMeasurement(final RequestContext measurementContext) {
        final Measurement measurement = doMeasure(measurementContext);
        if (probeListeners.size() > 0) {
            for (final IMeasurementSourceListener listener : probeListeners) {
                listener.measurementTaken(measurement);
            }
        }
        return measurement;
    }

    protected abstract Measurement doMeasure(RequestContext measurementContext);
}
