package de.uka.ipd.sdq.probespec.framework.test.probes;

import static org.junit.Assert.assertTrue;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.measurements.BasicMeasurement;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.probes.example.ExampleTakeCurrentTimeStrategy;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleSimulationContext;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

@RunWith(JUnit4.class)
public class ProbeSetSampleTest {

    private SimpleSimulationContext simCtx;
    private ExampleTakeCurrentTimeStrategy probe;

    @Before
    public void setUp() {
        simCtx = new SimpleSimulationContext();
        probe = new ExampleTakeCurrentTimeStrategy(simCtx);
    }

    @After
    public void tearDown() throws Exception {
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testResponseTimeProbe() {
        final RequestContext ctxID = new RequestContext("1");
        simCtx.setSimulatedTime(100d);

        final Measurement probeMeasurement = probe.takeMeasurement(ctxID);
        assertTrue(probeMeasurement.getMeasuredProbe() == probe);
        assertTrue(probeMeasurement.getMetricDesciption() == MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        assertTrue(probeMeasurement.getRequestContext() == ctxID);

        assertTrue(probeMeasurement instanceof BasicMeasurement<?,?>);
        final BasicMeasurement<Double, Duration> basicMeasurement = (BasicMeasurement<Double, Duration>) probeMeasurement;

        assertTrue(basicMeasurement.getMeasure().getUnit().isCompatible(SI.SECOND));
        assertTrue(basicMeasurement.getMeasure().compareTo(Measure.valueOf(100d,SI.SECOND)) == 0);
    }

}
