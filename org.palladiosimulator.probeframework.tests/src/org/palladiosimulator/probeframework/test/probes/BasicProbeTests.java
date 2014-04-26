package org.palladiosimulator.probeframework.test.probes;

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
import org.palladiosimulator.measurementspec.BasicMeasurement;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.example.ExampleTakeCPUDemandStrategy;
import org.palladiosimulator.probeframework.probes.example.ExampleTakeCPUStateStrategy;
import org.palladiosimulator.probeframework.probes.example.ExampleTakeCurrentTimeStrategy;
import org.palladiosimulator.probeframework.probes.example.SimpleCPUResource;
import org.palladiosimulator.probeframework.probes.example.SimpleSimulationContext;
import org.palladiosimulator.probeframework.probes.listener.IProbeListener;

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

        final ProbeMeasurement probeMeasurement = probe.takeMeasurement(ctxID);
        assertTrue(probeMeasurement.getSourceAndContext().getMeasurementSource() == probe);
        assertTrue(probeMeasurement.getMeasurement().getMetricDesciption() == MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        assertTrue(probeMeasurement.getSourceAndContext().getRequestContext() == ctxID);

        assertTrue(probeMeasurement.getMeasurement() instanceof BasicMeasurement<?,?>);
        final BasicMeasurement<Double, Duration> basicMeasurement = (BasicMeasurement<Double, Duration>) probeMeasurement.getMeasurement();
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
        final ProbeMeasurement probeMeasurement = probe.takeMeasurement(ctxID);

        assertTrue(probeMeasurement.getSourceAndContext().getMeasurementSource() == probe);
        assertTrue(probeMeasurement.getMeasurement().getMetricDesciption() == MetricDescriptionConstants.CPU_STATE_METRIC);
        assertTrue(probeMeasurement.getSourceAndContext().getRequestContext() == ctxID);

        assertTrue(probeMeasurement.getMeasurement() instanceof BasicMeasurement<?,?>);
        final BasicMeasurement<Double, Dimensionless> basicMeasurement = (BasicMeasurement<Double, Dimensionless>) probeMeasurement.getMeasurement();

        final Measure<Double,Dimensionless> measure = basicMeasurement.getMeasureForMetric(MetricDescriptionConstants.CPU_STATE_METRIC);
        assertTrue(measure.getUnit().isCompatible(Unit.ONE));
        assertTrue(measure.compareTo(Measure.valueOf(2l,Unit.ONE)) == 0);
    }

    ProbeMeasurement lastMeasurement;

    @Test
    public void testDemandProbe() {
        lastMeasurement = null;
        final SimpleCPUResource cpuResource = new SimpleCPUResource();
        simCtx.addActiveResource("Test CPU", cpuResource);
        final ExampleTakeCPUDemandStrategy probe = new ExampleTakeCPUDemandStrategy(cpuResource);
        probe.addObserver(new IProbeListener() {

            @Override
            public void newProbeMeasurementAvailable(final ProbeMeasurement measurement) {
                lastMeasurement = measurement;
            }

        });
        cpuResource.setJobs(1);
        cpuResource.demand(10);

        assertTrue(lastMeasurement != null);
        assertTrue(lastMeasurement.getMeasurement() instanceof BasicMeasurement);
        assertTrue(lastMeasurement.getMeasurement().getMetricDesciption() == MetricDescriptionConstants.RESOURCE_DEMAND_METRIC);

        @SuppressWarnings("unchecked")
        final BasicMeasurement<Double, Duration> result = (BasicMeasurement<Double, Duration>) lastMeasurement.getMeasurement();
        final Measure<Double,Duration> resultMeasure = result.getMeasureForMetric(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC);
        assertTrue(resultMeasure.compareTo(Measure.valueOf(10.0d, SI.SECOND))==0);
    }

}
