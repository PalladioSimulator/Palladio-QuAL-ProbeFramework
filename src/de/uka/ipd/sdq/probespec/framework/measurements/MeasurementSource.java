package de.uka.ipd.sdq.probespec.framework.measurements;

import java.util.Collection;

import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.edp2.metricentity.MetricEntity;
import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;

public abstract class MeasurementSource extends MetricEntity implements IMeasurementSource {

    private final AbstractObservable<IMeasurementSourceListener> observableDelegate = new AbstractObservable<IMeasurementSourceListener>() {};

    /**
     * @param observer
     * @see org.palladiosimulator.commons.designpatterns.IAbstractObservable#addObserver(java.lang.Object)
     */
    @Override
    public void addObserver(final IMeasurementSourceListener observer) {
        observableDelegate.addObserver(observer);
    }

    /**
     * @param observer
     * @see org.palladiosimulator.commons.designpatterns.IAbstractObservable#removeObserver(java.lang.Object)
     */
    @Override
    public void removeObserver(final IMeasurementSourceListener observer) {
        observableDelegate.removeObserver(observer);
    }

    public MeasurementSource(final MetricDescription metricDesciption) {
        super(metricDesciption);
    }

    public boolean isCompatibleMeasurement(final Measurement measurement) {
        if (!isCompatibleWith(measurement.getMetricDesciption())) {
            return false;
        }
        return true;
    }

    /**
     * @param measurement
     */
    protected void notifyMeasurementSourceListener(final Measurement measurement) {
        if (!isCompatibleMeasurement(measurement)) {
            throw new IllegalArgumentException("Taken measurement has an incompatible metric");
        }
        observableDelegate.getEventDispatcher().newMeasurementAvailable(measurement);
    }

    protected Collection<IMeasurementSourceListener> getMeasurementSourceListeners() {
        return observableDelegate.getObservers();
    }
}
