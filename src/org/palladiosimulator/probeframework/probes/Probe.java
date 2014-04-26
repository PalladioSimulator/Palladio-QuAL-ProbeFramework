package org.palladiosimulator.probeframework.probes;

import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.commons.designpatterns.IAbstractObservable;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.metricentity.MetricEntity;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.probes.listener.IProbeListener;

public abstract class Probe extends MetricEntity implements IAbstractObservable<IProbeListener> {

    private final AbstractObservable<IProbeListener> observableDelegate = new AbstractObservable<IProbeListener>() {};

    protected Probe(final MetricDescription metricDesciption) {
        super(metricDesciption);
    }

    /**
     * @param observer
     * @see org.palladiosimulator.commons.designpatterns.IAbstractObservable#addObserver(java.lang.Object)
     */
    @Override
    public void addObserver(final IProbeListener observer) {
        observableDelegate.addObserver(observer);
    }

    /**
     * @param observer
     * @see org.palladiosimulator.commons.designpatterns.IAbstractObservable#removeObserver(java.lang.Object)
     */
    @Override
    public void removeObserver(final IProbeListener observer) {
        observableDelegate.removeObserver(observer);
    }

    /**
     * @param newMeasurement
     */
    protected void notifyMeasurementSourceListener(final ProbeMeasurement newMeasurement) {
        if (!isCompatibleWith(newMeasurement.getMeasurement().getMetricDesciption())) {
            throw new IllegalArgumentException("Taken measurement has an incompatible metric");
        }
        observableDelegate.getEventDispatcher().newProbeMeasurementAvailable(newMeasurement);
    }
}
