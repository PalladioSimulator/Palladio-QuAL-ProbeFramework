package de.uka.ipd.sdq.probespec.framework.test.probes;

import static org.junit.Assert.assertTrue;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.measurements.BasicMeasurement;
import de.uka.ipd.sdq.probespec.framework.measurements.IMeasurementSourceListener;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.probes.example.ExampleTakeCPUDemandStrategy;
import de.uka.ipd.sdq.probespec.framework.probes.example.ExampleTakeCPUStateStrategy;
import de.uka.ipd.sdq.probespec.framework.probes.example.ExampleTakeCurrentTimeStrategy;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleCPUResource;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleSimulationContext;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

@RunWith(JUnit4.class)
public class BasicProbeTests {

    private SimpleSimulationContext simCtx;

    @Before
    public void setUp() {
        simCtx = new SimpleSimulationContext();
    }

    @After
    public void tearDown() throws Exception {
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testResponseTimeProbe() {
        final ExampleTakeCurrentTimeStrategy probe = new ExampleTakeCurrentTimeStrategy(simCtx);
        final RequestContext ctxID = new RequestContext("1");
        simCtx.setSimulatedTime(100d);

        final Measurement probeMeasurement = probe.takeMeasurement(ctxID);
        assertTrue(probeMeasurement.getMeasurementSource() == probe);
        assertTrue(probeMeasurement.getMetricDesciption() == MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        assertTrue(probeMeasurement.getRequestContext() == ctxID);

        assertTrue(probeMeasurement instanceof BasicMeasurement<?,?>);
        final BasicMeasurement<Double, Duration> basicMeasurement = (BasicMeasurement<Double, Duration>) probeMeasurement;
        final Measure<Double,Duration> measure = basicMeasurement.getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);

        assertTrue(measure.getUnit().isCompatible(SI.SECOND));
        assertTrue(measure.compareTo(Measure.valueOf(100d,SI.SECOND)) == 0);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCPUStateProbe() {
        final RequestContext ctxID = new RequestContext("1");
        final SimpleCPUResource cpuResource = new SimpleCPUResource();
        simCtx.addActiveResource("Test CPU", cpuResource);
        final ExampleTakeCPUStateStrategy probe = new ExampleTakeCPUStateStrategy(cpuResource);

        cpuResource.setJobs(2);
        final Measurement probeMeasurement = probe.takeMeasurement(ctxID);

        assertTrue(probeMeasurement.getMeasurementSource() == probe);
        assertTrue(probeMeasurement.getMetricDesciption() == MetricDescriptionConstants.CPU_STATE_METRIC);
        assertTrue(probeMeasurement.getRequestContext() == ctxID);

        assertTrue(probeMeasurement instanceof BasicMeasurement<?,?>);
        final BasicMeasurement<Double, Dimensionless> basicMeasurement = (BasicMeasurement<Double, Dimensionless>) probeMeasurement;

        final Measure<Double,Dimensionless> measure = basicMeasurement.getMeasureForMetric(MetricDescriptionConstants.CPU_STATE_METRIC);
        assertTrue(measure.getUnit().isCompatible(Unit.ONE));
        assertTrue(measure.compareTo(Measure.valueOf(2l,Unit.ONE)) == 0);
    }

    Measurement lastMeasurement;

    @Test
    public void testDemandProbe() {
        lastMeasurement = null;
        final SimpleCPUResource cpuResource = new SimpleCPUResource();
        simCtx.addActiveResource("Test CPU", cpuResource);
        final ExampleTakeCPUDemandStrategy probe = new ExampleTakeCPUDemandStrategy(cpuResource);
        probe.registerMeasurementSourceListener(new IMeasurementSourceListener() {

            @Override
            public void newMeasurementAvailable(final Measurement measurement) {
                lastMeasurement = measurement;
            }
        });
        cpuResource.setJobs(1);
        cpuResource.demand(10);

        assertTrue(lastMeasurement != null);
        assertTrue(lastMeasurement instanceof BasicMeasurement);
        assertTrue(lastMeasurement.getMetricDesciption() == MetricDescriptionConstants.RESOURCE_DEMAND_METRIC);

        @SuppressWarnings("unchecked")
        final BasicMeasurement<Double, Duration> result = (BasicMeasurement<Double, Duration>) lastMeasurement;
        final Measure<Double,Duration> resultMeasure = result.getMeasureForMetric(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC);
        assertTrue(resultMeasure.compareTo(Measure.valueOf(10.0d, SI.SECOND))==0);
    }

}
