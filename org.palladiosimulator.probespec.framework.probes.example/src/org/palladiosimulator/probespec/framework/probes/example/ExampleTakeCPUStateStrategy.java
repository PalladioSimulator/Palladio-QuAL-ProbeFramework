package org.palladiosimulator.probespec.framework.probes.example;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.Unit;

import org.palladiosimulator.metricspec.MetricDescriptionConstants;
import org.palladiosimulator.probespec.framework.probes.BasicObjectStateProbe;
import org.palladiosimulator.measurementspec.requestcontext.RequestContext;

public class ExampleTakeCPUStateStrategy extends BasicObjectStateProbe<ASimpleActiveResource, Long, Dimensionless>{

    public ExampleTakeCPUStateStrategy(final ASimpleActiveResource myCpu) {
        super(myCpu,MetricDescriptionConstants.CPU_STATE_METRIC);
    }

    @Override
    protected Measure<Long, Dimensionless> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf((long)getStateObject().getJobs(), Unit.ONE);
    }

}
