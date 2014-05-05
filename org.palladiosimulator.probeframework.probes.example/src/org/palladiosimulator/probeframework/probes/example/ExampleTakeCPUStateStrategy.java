package org.palladiosimulator.probeframework.probes.example;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.Unit;

import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.BasicObjectStateProbe;

/**
 * Measures an active resource state metric (dimensionless) by taking the current number of jobs
 * within an active resource (observed state object), e.g., a CPU. This class uses
 * <code>ASimpleActiveResource</code> as an example active resource.
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public class ExampleTakeCPUStateStrategy extends BasicObjectStateProbe<ASimpleActiveResource, Long, Dimensionless> {

    /**
     * Default constructor.
     * 
     * @param myCpu
     *            The observer object is a CPU, thus, allowing to request its current jobs.
     */
    public ExampleTakeCPUStateStrategy(final ASimpleActiveResource myCpu) {
        super(myCpu, MetricDescriptionConstants.CPU_STATE_METRIC);
    }

    /**
     * Measures the current jobs as requested from the active CPU resource (observed state object).
     * 
     * @param measurementContext
     *            The measurement context for this probe.
     * @return The new measure.
     */
    @Override
    protected Measure<Long, Dimensionless> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf((long) getStateObject().getJobs(), Unit.ONE);
    }

}
