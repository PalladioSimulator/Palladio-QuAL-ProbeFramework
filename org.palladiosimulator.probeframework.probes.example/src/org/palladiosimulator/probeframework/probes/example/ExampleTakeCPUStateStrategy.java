package org.palladiosimulator.probeframework.probes.example;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.Unit;

import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.BasicObjectStateProbe;

public class ExampleTakeCPUStateStrategy extends BasicObjectStateProbe<ASimpleActiveResource, Long, Dimensionless>{

    public ExampleTakeCPUStateStrategy(final ASimpleActiveResource myCpu) {
        super(myCpu,MetricDescriptionConstants.CPU_STATE_METRIC);
    }

    @Override
    protected Measure<Long, Dimensionless> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf((long)getStateObject().getJobs(), Unit.ONE);
    }

}
