package de.uka.ipd.sdq.probespec.framework.probes.example;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.probes.BasicProbe;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

/**
 * ProbeStrategy which is able to measure the current simulated time. The
 * simulated time's unit is assumed to be {@link SI#SECOND}.
 * 
 * @author Philipp Merkle
 * 
 */
public class ExampleTakeCurrentTimeStrategy extends BasicProbe<Double,Duration> {

    private final SimpleSimulationContext simulationContext;

    public ExampleTakeCurrentTimeStrategy(final SimpleSimulationContext simCtx) {
        super(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        this.simulationContext = simCtx;
    }

    @Override
    protected Measure<Double,Duration> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf(simulationContext.getSimulatedTime(), SI.SECOND);
    }

}
