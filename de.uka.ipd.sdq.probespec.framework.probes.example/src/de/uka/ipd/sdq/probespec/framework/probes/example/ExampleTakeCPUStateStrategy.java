package de.uka.ipd.sdq.probespec.framework.probes.example;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.Unit;

import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.probes.BasicProbe;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

public class ExampleTakeCPUStateStrategy extends BasicProbe<Long, Dimensionless>{

    private final ASimpleActiveResource myCpu;

    public ExampleTakeCPUStateStrategy(final ASimpleActiveResource myCpu, final ISimpleDemanding demandComputer) {
        super(MetricDescriptionConstants.CPU_STATE_METRIC);
        this.myCpu = myCpu;
    }

    @Override
    protected Measure<Long, Dimensionless> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf((long)myCpu.getJobs(), Unit.ONE);
    }

}
