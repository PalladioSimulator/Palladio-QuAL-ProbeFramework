package org.palladiosimulator.probeframework.probes;

import java.util.List;

import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.util.builder.MetricSetDescriptionBuilder;

/**
 * Utility class for coping with lists of probes. Currently, only provides a static method to
 * dynamically create a metric description for a list of probes.
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public final class ProbeListHelper {

    /**
     * Helper method to dynamically create a metric description for a given list of probes. This
     * description particularly uses the given metric name.
     * 
     * @param subsumedProbes
     *            The list of subsumed probes.
     * @param metricName
     *            The textual name of the new metric description.
     * @return The newly created metric description.
     */
    static MetricDescription getMetricSetDescription(final List<Probe> subsumedProbes, final String metricName) {
        final StringBuilder textualDescriptionBuilder = new StringBuilder("Subsumed metrics: ");
        for (final Probe probe : subsumedProbes) {
            textualDescriptionBuilder.append("<");
            textualDescriptionBuilder.append(probe.getMetricDesciption().getName());
            textualDescriptionBuilder.append(">");
        }
        final MetricSetDescription result = MetricSetDescriptionBuilder.newMetricSetDescriptionBuilder()
                .name(metricName).textualDescription(textualDescriptionBuilder.toString()).build();
        for (final Probe probe : subsumedProbes) {
            result.getSubsumedMetrics().add(probe.getMetricDesciption());
        }
        return result;
    }

}
