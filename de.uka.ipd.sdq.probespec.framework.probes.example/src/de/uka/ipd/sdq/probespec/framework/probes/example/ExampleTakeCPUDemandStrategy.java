package de.uka.ipd.sdq.probespec.framework.probes.example;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.probes.BasicProbe;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

public class ExampleTakeCPUDemandStrategy extends BasicProbe<Double, Duration> {

    private final ASimpleActiveResource myResource;
    private final ISimpleDemanding demandComputer;

    public ExampleTakeCPUDemandStrategy(final ASimpleActiveResource myResource, final ISimpleDemanding demandComputer) {
        super(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC);
        this.myResource = myResource;
        this.demandComputer = demandComputer;
    }

    @Override
    protected Measure<Double, Duration> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf(demandComputer.getDemand(myResource), SI.SECOND);
    }

}
