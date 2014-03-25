package de.uka.ipd.sdq.probespec.framework.test.probes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;

import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet;
import de.uka.ipd.sdq.probespec.framework.probes.Probe;
import de.uka.ipd.sdq.probespec.framework.probes.ProbeSet;
import de.uka.ipd.sdq.probespec.framework.probes.example.ExampleTakeCPUStateStrategy;
import de.uka.ipd.sdq.probespec.framework.probes.example.ExampleTakeCurrentTimeStrategy;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleCPUResource;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleSimulationContext;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

@RunWith(JUnit4.class)
public class ProbeSetTests {

    private SimpleSimulationContext simCtx;
    private Probe currentTimeProbe;
    private SimpleCPUResource cpuResource;
    private Probe currentCPUStateProbe;
    private ProbeSet probeSet;

    @Before
    public void setUp() {
        simCtx = new SimpleSimulationContext();
        currentTimeProbe = new ExampleTakeCurrentTimeStrategy(simCtx);
        cpuResource = new SimpleCPUResource();
        currentCPUStateProbe = new ExampleTakeCPUStateStrategy(cpuResource, null);

        probeSet = new ProbeSet(Arrays.asList(currentTimeProbe, currentCPUStateProbe),"CPU State");
    }

    @Test
    public void testProbeSet() {
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
}
