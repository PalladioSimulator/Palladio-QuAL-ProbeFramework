package org.palladiosimulator.probeframework.probes.example;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.Unit;

import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.BasicObjectStateProbe;

/**
 * Measures a passive resource state metric (dimensionless) by taking the number of free
 * resources within a passive resource (observed state object), e.g., a connection pool. This class
 * uses <code>ASimplePassiveResource</code> as an example passive resource.
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public class ExampleTakePassiveResourceStateProbe extends BasicObjectStateProbe<ASimplePassiveResource, Long, Dimensionless> {

    /**
     * Default constructor.
     * 
     * @param passiveResource
     *            The observer object is a passive resource, thus, allowing to request its free
     *            resources.
     */
    public ExampleTakePassiveResourceStateProbe(final ASimplePassiveResource passiveResource) {
        super(passiveResource, MetricDescriptionConstants.PASSIVE_RESOURCE_STATE_METRIC);
    }

    /**
     * Measures the free resources as requested from the passive resource (observed state object).
     * 
     * @param measurementContext
     *            The measurement context for this probe.
     * @return The new measure.
     */
    @Override
    protected Measure<Long, Dimensionless> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf((long) getStateObject().getFree(), Unit.ONE);
    }

}
