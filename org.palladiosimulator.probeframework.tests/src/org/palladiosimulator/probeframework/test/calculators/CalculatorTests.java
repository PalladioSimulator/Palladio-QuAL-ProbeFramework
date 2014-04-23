package org.palladiosimulator.probeframework.test.calculators;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.palladiosimulator.measurementspec.IMeasurementSourceListener;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.requestcontext.RequestContext;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.DefaultCalculatorFactory;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.probeframework.probes.example.ExampleTakeCurrentTimeStrategy;
import org.palladiosimulator.probeframework.probes.example.SimpleSimulationContext;

@RunWith(JUnit4.class)
public class CalculatorTests {

    private TriggeredProbe startProbe;
    private TriggeredProbe endProbe;
    private SimpleSimulationContext simCtx;
    private ProbeFrameworkContext probeFrameworkContext;
    protected Measurement lastMeasurement;

    @Before
    public void setUp() {
        probeFrameworkContext = new ProbeFrameworkContext(new DefaultCalculatorFactory());
        simCtx = new SimpleSimulationContext();
        startProbe = new ExampleTakeCurrentTimeStrategy(simCtx);
        endProbe = new ExampleTakeCurrentTimeStrategy(simCtx);
    }

    @Test
    public void testResponseTimeCalculator() {
        final Calculator rtCalculator = this.probeFrameworkContext.getCalculatorFactory().buildResponseTimeCalculator("Test ResponseTime", Arrays.asList((Probe)startProbe,(Probe)endProbe));

        rtCalculator.addObserver(new IMeasurementSourceListener() {

            @Override
            public void newMeasurementAvailable(final Measurement measurement) {
                lastMeasurement = measurement;
            }

            @Override
            public void preUnregister() {
            }
        });

        simCtx.setSimulatedTime(0.0d);
        final RequestContext requestContext = new RequestContext("1");

        startProbe.takeMeasurement(requestContext);

        simCtx.setSimulatedTime(100.d);
        assertTrue(lastMeasurement == null);

        endProbe.takeMeasurement(requestContext);

        assertTrue(lastMeasurement != null);
        assertTrue(lastMeasurement.getMeasurementSource() == rtCalculator);
        assertTrue(lastMeasurement.getMetricDesciption() == rtCalculator.getMetricDesciption());
    }
}
