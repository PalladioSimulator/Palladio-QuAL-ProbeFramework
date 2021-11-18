package org.palladiosimulator.probeframework.probes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.measureprovider.IMeasureProvider;
import org.palladiosimulator.measurementframework.measureprovider.MeasurementListMeasureProvider;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.probes.listener.IProbeListener;

/**
 * This probe allows to attach supplementary measurements to a basic probe. Whenever the primary
 * probe produces a measurement all of the supplementary probes are triggered. The probe
 * consequently requires a MetricSetDescription which composes all of the subsumed metrics,
 * especially the measurement of the primary probe.
 * 
 * This probe is build to facilitate the attachment of PointInTime measurements to any other kind of
 * probe.
 * 
 * @author Sebastian Krach
 *
 */
public class SupplementaryMeasurementAttachingProbe extends Probe implements IProbeListener {

    private List<TriggeredProbe> supplementaryProbes;
    private long primaryPosition = 0;

    /**
     * Constructs a new probe.
     * 
     * @param metricDesciption
     *            the composite metric comprising the metric of all probes, primary and
     *            supplementary.
     * @param primaryProbe
     *            the primary probe, which triggers measurements
     * @param supplementaryProbes
     *            the probes which are triggered whenever the primary probe produces a measurement.
     */
    public SupplementaryMeasurementAttachingProbe(MetricSetDescription metricDesciption, Probe primaryProbe,
            List<TriggeredProbe> supplementaryProbes) {
        super(metricDesciption);
        this.supplementaryProbes = supplementaryProbes;
        if (!metricDesciption.getSubsumedMetrics()
            .stream()
            .map(MetricDescription::getId)
            .anyMatch(id -> primaryProbe.getMetricDesciption()
                .getId()
                .equals(id))) {
            throw new IllegalArgumentException(
                    "The metric of the primary probe needs to be contained in the resulting metric set description");
        }
        if (metricDesciption.getSubsumedMetrics()
            .size() != supplementaryProbes.size() + 1) {
            throw new IllegalArgumentException("Expected " + (metricDesciption.getSubsumedMetrics()
                .size() - 1) + " supplementary probes to be provided, got " + supplementaryProbes.size() + ".");
        }
        this.primaryPosition = metricDesciption.getSubsumedMetrics()
            .stream()
            .takeWhile(desc -> !desc.getId().equals(primaryProbe.getMetricDesciption().getId()))
            .count();

        primaryProbe.addObserver(this);
    }

    /**
     * This method should not be called directly, as it is used by the primary probe to notify us of
     * new primary measurements. Please use your primary probe to trigger measurements.
     */
    @Override
    public void newProbeMeasurementAvailable(ProbeMeasurement probeMeasurement) {
        var context = probeMeasurement.getProbeAndContext()
            .getRequestContext();

        final var size = ((MetricSetDescription) getMetricDesciption()).getSubsumedMetrics()
            .size();
        final List<MeasuringValue> childMeasurements = new ArrayList<>(size);

        var supplIter = supplementaryProbes.iterator();
        for (int i = 0; i < size; i++) {
            ProbeMeasurement pm = probeMeasurement;
            if (i != this.primaryPosition) {
                pm = supplIter.next()
                    .doMeasure(probeMeasurement.getProbeAndContext()
                        .getRequestContext());
            }
            var mp = pm.getMeasureProvider();

            if (!(mp instanceof MeasuringValue)) {
                throw new IllegalArgumentException("Subsumed measure providers have to be measurements");
            }

            childMeasurements.add(((MeasuringValue) mp));
        }
        final IMeasureProvider measureProvider = new MeasurementListMeasureProvider(childMeasurements);
        notifyMeasurementSourceListener(new ProbeMeasurement(measureProvider, this, context));
    }

}
