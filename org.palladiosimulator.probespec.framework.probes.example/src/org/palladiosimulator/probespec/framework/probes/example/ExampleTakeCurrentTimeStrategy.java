package org.palladiosimulator.probespec.framework.probes.example;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.probespec.framework.constants.MetricDescriptionConstants;
import org.palladiosimulator.probespec.framework.probes.BasicObjectStateProbe;
import org.palladiosimulator.probespec.framework.requestcontext.RequestContext;

/**
 * ProbeStrategy which is able to measure the current simulated time. The
 * simulated time's unit is assumed to be {@link SI#SECOND}.
 * 
 * @author Philipp Merkle
 * 
 */
public class ExampleTakeCurrentTimeStrategy extends BasicObjectStateProbe<SimpleSimulationContext, Double,Duration> {

    public ExampleTakeCurrentTimeStrategy(final SimpleSimulationContext simCtx) {
        super(simCtx, MetricDescriptionConstants.POINT_IN_TIME_METRIC);
    }

    @Override
    protected Measure<Double,Duration> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf(getStateObject().getSimulatedTime(), SI.SECOND);
    }

}
