package de.uka.ipd.sdq.probespec.framework.probes.example;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.Unit;

import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.probes.BasicProbe;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

public class ExampleTakePassiveResourceState extends BasicProbe<Long, Dimensionless> {

    private final ASimplePassiveResource myResource;

    public ExampleTakePassiveResourceState(final ASimplePassiveResource myResource) {
        super(MetricDescriptionConstants.PASSIVE_RESOURCE_STATE_METRIC);
        this.myResource = myResource;
    }

    @Override
    protected Measure<Long, Dimensionless> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf((long)myResource.getFree(), Unit.ONE);
    }

}
