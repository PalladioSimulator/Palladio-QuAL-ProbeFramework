package de.uka.ipd.sdq.probespec.framework.measurements;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage;
import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;

public abstract class MeasurementSource {

    /** Metric derived by this measurement source
     */
    protected final MetricDescription metricDesciption;

    /**
     * @param metricDesciption
     */
    protected MeasurementSource(final MetricDescription metricDesciption) {
        super();
        if (metricDesciption == null) {
            throw new IllegalArgumentException("Metric description has to be a valid instance.");
        }

        checkValidString(metricDesciption.getName());
        checkValidString(metricDesciption.getTextualDescription());
        checkValidString(metricDesciption.getUuid());

        this.metricDesciption = metricDesciption;
        this.metricDesciption.eAdapters().add(new AdapterImpl() {
            @Override
            public void notifyChanged(final Notification notification) {
                if (notification.getEventType() != Notification.REMOVING_ADAPTER && notification.getEventType() != Notification.RESOLVE) {
                    if (notification.getFeature() != ExperimentDataPackage.eINSTANCE.getDescription_Repository()) {
                        throw new RuntimeException("Metric description altered after initializing. This is not allowed.");
                    }
                }
            }
        });
    }

    private void checkValidString(final String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("Parameter must not be null or empty");
        }
    }

    /**
     * @return the metricDesciption
     */
    public final MetricDescription getMetricDesciption() {
        return metricDesciption;
    }

    /**
     * @param other
     * @return
     */
    public final boolean isCompatibleWith(final MetricDescription other) {
        // TODO
        return true;
    }

    public boolean isCompatibleMeasurement(final Measurement measurement) {
        // TODO
        return false;
    }
}
