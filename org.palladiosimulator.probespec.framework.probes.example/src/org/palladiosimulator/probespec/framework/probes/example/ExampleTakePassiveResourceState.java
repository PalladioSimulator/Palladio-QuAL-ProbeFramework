package org.palladiosimulator.probespec.framework.probes.example;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.Unit;

import org.palladiosimulator.metricspec.MetricDescriptionConstants;
import org.palladiosimulator.probespec.framework.probes.BasicObjectStateProbe;
import org.palladiosimulator.measurementspec.requestcontext.RequestContext;

public class ExampleTakePassiveResourceState extends BasicObjectStateProbe<ASimplePassiveResource, Long, Dimensionless> {

    public ExampleTakePassiveResourceState(final ASimplePassiveResource myResource) {
        super(myResource, MetricDescriptionConstants.PASSIVE_RESOURCE_STATE_METRIC);
    }

    @Override
    protected Measure<Long, Dimensionless> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf((long)getStateObject().getFree(), Unit.ONE);
    }

}
