/**
 * 
 */
package de.uka.ipd.sdq.probespec.framework.probes;

import java.util.LinkedList;
import java.util.List;

import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataFactory;
import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;

import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSource;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

/**
 * @author snowball
 * 
 */
public class ProbeSet extends Probe {

    private static final ExperimentDataFactory experimentDataFactory = ExperimentDataFactory.eINSTANCE;

    private final List<Probe> subsumedProbes = new LinkedList<Probe>();

    /**
     * 
     */
    public ProbeSet(final List<Probe> subsumedProbes, final String metricName) {
        super(getMetricSetDescription(subsumedProbes, metricName));

        this.subsumedProbes.addAll(subsumedProbes);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uka.ipd.sdq.probespec.framework.probes.Probe#takeMeasuremnt()
     */
    @Override
    public MeasurementSet doMeasure(final RequestContext measurementContext) {
        final List<Measurement> childMeasurements = new LinkedList<Measurement>();

        for (final Probe childProbe : subsumedProbes) {
            childMeasurements.add(childProbe.doMeasure(measurementContext));
        }

        final MeasurementSet result = new MeasurementSet(childMeasurements, (MetricSetDescription) getMetricDesciption(), this);
        return result;
    }

    private static MetricDescription getMetricSetDescription(final List<Probe> subsumedProbes, final String metricName) {
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
