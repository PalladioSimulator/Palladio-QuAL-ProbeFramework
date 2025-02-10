package org.palladiosimulator.probeframework.probes.example;

import jakarta.measure.Measure;
import jakarta.measure.quantity.Duration;
import jakarta.measure.unit.SI;

import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.BasicObjectStateProbe;

/**
 * Measures a point in time metric (in {@link SI#SECOND}) by taking the current simulation time of a
 * simulation (observed state object). This class uses <code>SimpleSimulationContext</code> as an
 * example simulation state object.
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public class ExampleTakeCurrentTimeProbe extends BasicObjectStateProbe<SimpleSimulationContext, Double, Duration> {

    /**
     * Default constructor.
     * 
     * @param simulationContext
     *            The observer object is a simulation state object, thus, allowing to request its
     *            current simulation time.
     */
    public ExampleTakeCurrentTimeProbe(final SimpleSimulationContext simulationContext) {
        super(simulationContext, MetricDescriptionConstants.POINT_IN_TIME_METRIC);
    }

    /**
     * Measures the current simulation time as requested from the simulation context (observed state
     * object).
     * 
     * @param measurementContext
     *            The measurement context for this probe.
     * @return The new measure.
     */
    @Override
    protected Measure<Double, Duration> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf(getStateObject().getSimulatedTime(), SI.SECOND);
    }

}
