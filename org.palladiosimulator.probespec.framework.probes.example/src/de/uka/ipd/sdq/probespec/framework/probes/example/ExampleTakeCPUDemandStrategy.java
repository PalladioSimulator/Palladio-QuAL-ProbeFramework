package de.uka.ipd.sdq.probespec.framework.probes.example;

import java.util.Observable;
import java.util.Observer;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.probes.BasicEventProbe;

public class ExampleTakeCPUDemandStrategy extends BasicEventProbe<ASimpleActiveResource, Double, Duration> implements Observer {

    public ExampleTakeCPUDemandStrategy(final ASimpleActiveResource myResource) {
        super(myResource,MetricDescriptionConstants.RESOURCE_DEMAND_METRIC);
    }

    @Override
    protected void registerListener() {
        this.eventSource.addObserver(this);
    }

    @Override
    public void update(final Observable eventSource, final Object event) {
        final double demand = (Double) event;
        this.notify(Measure.valueOf(demand, SI.SECOND));
    }

}