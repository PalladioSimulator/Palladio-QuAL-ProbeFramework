package de.uka.ipd.sdq.probespec.framework.probes.example;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.Unit;

import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.probes.BasicObjectStateProbe;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

public class ExampleTakeCPUStateStrategy extends BasicObjectStateProbe<ASimpleActiveResource, Long, Dimensionless>{

    public ExampleTakeCPUStateStrategy(final ASimpleActiveResource myCpu) {
        super(myCpu,MetricDescriptionConstants.CPU_STATE_METRIC);
    }

    @Override
    protected Measure<Long, Dimensionless> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf((long)getStateObject().getJobs(), Unit.ONE);
    }

}
