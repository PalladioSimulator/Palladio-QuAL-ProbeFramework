package org.palladiosimulator.probeframework.probes;

import java.util.List;

import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.util.builder.MetricSetDescriptionBuilder;

/**
 * @author Steffen Becker, Sebastian Lehrig
 */
public final class ProbeSetHelper {

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
