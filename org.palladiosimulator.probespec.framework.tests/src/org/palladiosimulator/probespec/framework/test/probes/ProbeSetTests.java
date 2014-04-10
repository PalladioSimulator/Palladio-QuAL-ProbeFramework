package org.palladiosimulator.probespec.framework.test.probes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;
import org.palladiosimulator.probespec.framework.constants.MetricDescriptionConstants;
import org.palladiosimulator.probespec.framework.measurements.IMeasurementSourceListener;
import org.palladiosimulator.probespec.framework.measurements.Measurement;
import org.palladiosimulator.probespec.framework.measurements.MeasurementSet;
import org.palladiosimulator.probespec.framework.probes.EventProbeSet;
import org.palladiosimulator.probespec.framework.probes.Probe;
import org.palladiosimulator.probespec.framework.probes.TriggeredProbeSet;
import org.palladiosimulator.probespec.framework.probes.example.ExampleTakeCPUDemandStrategy;
import org.palladiosimulator.probespec.framework.probes.example.ExampleTakeCPUStateStrategy;
import org.palladiosimulator.probespec.framework.probes.example.ExampleTakeCurrentTimeStrategy;
import org.palladiosimulator.probespec.framework.probes.example.SimpleCPUResource;
import org.palladiosimulator.probespec.framework.probes.example.SimpleSimulationContext;
import org.palladiosimulator.probespec.framework.requestcontext.RequestContext;

@RunWith(JUnit4.class)
public class ProbeSetTests {

    private SimpleSimulationContext simCtx;
    private Probe currentTimeProbe;
    private SimpleCPUResource cpuResource;
    private Probe currentCPUStateProbe;
    private TriggeredProbeSet probeSet;

    @Before
    public void setUp() {
        simCtx = new SimpleSimulationContext();
        currentTimeProbe = new ExampleTakeCurrentTimeStrategy(simCtx);
        cpuResource = new SimpleCPUResource();
        currentCPUStateProbe = new ExampleTakeCPUStateStrategy(cpuResource);

        probeSet = new TriggeredProbeSet(Arrays.asList(currentTimeProbe, currentCPUStateProbe),"CPU State");
    }

    @Test
    public void testTriggeredProbeSet() {
        simCtx.setSimulatedTime(100.0d);
        cpuResource.setJobs(2);
        final RequestContext requestContext = new RequestContext("1");

        final Measurement probeMeasure = probeSet.takeMeasurement(requestContext);

        assertTrue(probeMeasure != null);
        assertTrue(probeMeasure instanceof MeasurementSet);
        assertTrue(probeMeasure.getMetricDesciption() instanceof MetricSetDescription);

        final MeasurementSet measurementSet = (MeasurementSet) probeMeasure;

        assertEquals(measurementSet.getSubsumedMeasurements().size(), 2);
        assertEquals(measurementSet.getSubsumedMeasurements().get(0).getMetricDesciption(),
                MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        assertEquals(measurementSet.getSubsumedMeasurements().get(1).getMetricDesciption(),
                MetricDescriptionConstants.CPU_STATE_METRIC);
    }

    Measurement lastMeasurement;

    @Test
    public void testEventProbeSet() {
        lastMeasurement = null;
        simCtx.setSimulatedTime(100.0d);
        cpuResource.setJobs(2);
        final ExampleTakeCPUDemandStrategy probe = new ExampleTakeCPUDemandStrategy(cpuResource);

        final EventProbeSet eventProbeSet = new EventProbeSet(probe, Arrays.asList(currentTimeProbe,currentCPUStateProbe), "Composed");
        eventProbeSet.addObserver(new IMeasurementSourceListener() {

            @Override
            public void newMeasurementAvailable(final Measurement measurement) {
                lastMeasurement = measurement;
            }
        });

        cpuResource.demand(20.0d);

        assertTrue(lastMeasurement != null);
        assertTrue(lastMeasurement instanceof MeasurementSet);

        final Measure<Double,Duration> demandResult = lastMeasurement.getMeasureForMetric(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC);
        final Measure<Double,Duration> timeResult = lastMeasurement.getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        final Measure<Long,Dimensionless> stateResult = lastMeasurement.getMeasureForMetric(MetricDescriptionConstants.CPU_STATE_METRIC);

        assertTrue(demandResult.compareTo(Measure.valueOf(20.0d, SI.SECOND))==0);

        final MeasurementSet measurementSet = (MeasurementSet) lastMeasurement;

        assertEquals(measurementSet.getSubsumedMeasurements().size(), 3);
        assertEquals(measurementSet.getSubsumedMeasurements().get(1).getMetricDesciption(),
                MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        assertTrue(timeResult.compareTo(Measure.valueOf(100.0d, SI.SECOND))==0);
        assertEquals(measurementSet.getSubsumedMeasurements().get(2).getMetricDesciption(),
                MetricDescriptionConstants.CPU_STATE_METRIC);
        assertTrue(stateResult.compareTo(Measure.valueOf(2l, Unit.ONE))==0);
    }
}
