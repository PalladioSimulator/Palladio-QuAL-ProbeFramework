/**
 * 
 */
package org.palladiosimulator.probeframework.probes;

import java.util.List;

import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataFactory;
import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;
import org.palladiosimulator.measurementspec.MeasurementSource;

/**
 * @author snowball
 * 
 */
public final class ProbeSetHelper {

    private static final ExperimentDataFactory experimentDataFactory = ExperimentDataFactory.eINSTANCE;

    static MetricDescription getMetricSetDescription(final List<Probe> subsumedProbes, final String metricName) {
        final StringBuilder textualDescriptionBuilder = new StringBuilder("Subsumed metrics: ");
        for (final MeasurementSource probe : subsumedProbes) {
            textualDescriptionBuilder.append("<");
            textualDescriptionBuilder.append(probe.getMetricDesciption().getName());
            textualDescriptionBuilder.append(">");
        }
        final MetricSetDescription result = experimentDataFactory.createMetricSetDescription(metricName,
                textualDescriptionBuilder.toString());
        for (final MeasurementSource probe : subsumedProbes) {
            result.getSubsumedMetrics().add(probe.getMetricDesciption());
        }
        return result;
    }

}
