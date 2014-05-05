package org.palladiosimulator.probeframework.probes.example;

import java.util.Observable;
import java.util.Observer;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.probes.BasicEventProbe;

/**
 * Measures a resource demand metric (in {@link SI#SECOND}) by taking the demand emitted from an
 * active resource (event source), e.g., a CPU. This class uses <code>ASimpleActiveResource</code>
 * as an example active resource.
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public class ExampleTakeCPUDemandStrategy extends BasicEventProbe<ASimpleActiveResource, Double, Duration> implements
        Observer {

    /**
     * Default constructor.
     * 
     * @param activeResource
     *            The event source is an active resource (e.g., a CPU), thus, able to emit demand
     *            events.
     */
    public ExampleTakeCPUDemandStrategy(final ASimpleActiveResource activeResource) {
        super(activeResource, MetricDescriptionConstants.RESOURCE_DEMAND_METRIC);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void registerListener() {
        this.eventSource.addObserver(this);
    }

    /**
     * Receives a new demand event from the active resource (event source). Afterwards, notifies all
     * observers about this new measurement.
     * 
     * @param eventSource
     *            The observed event source.
     * @param event
     *            The emitted event, including the demand measurement.
     */
    @Override
    public void update(final Observable eventSource, final Object event) {
        final double demand = (Double) event;
        this.notify(Measure.valueOf(demand, SI.SECOND));
    }

}
