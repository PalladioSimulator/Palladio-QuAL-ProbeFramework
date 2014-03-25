package de.uka.ipd.sdq.probespec.framework.test.calculators;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.calculator.DefaultCalculatorFactory;
import de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorListener;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.probes.Probe;
import de.uka.ipd.sdq.probespec.framework.probes.example.ExampleTakeCurrentTimeStrategy;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleSimulationContext;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

@RunWith(JUnit4.class)
public class CalculatorTests {

    private Probe startProbe;
    private Probe endProbe;
    private SimpleSimulationContext simCtx;
    private ProbeSpecContext probeSpecContext;
    protected Measurement lastMeasurement;

    @Before
    public void setUp() {
        probeSpecContext = new ProbeSpecContext(new DefaultCalculatorFactory());
        simCtx = new SimpleSimulationContext();
        startProbe = new ExampleTakeCurrentTimeStrategy(simCtx);
        endProbe = new ExampleTakeCurrentTimeStrategy(simCtx);
    }

    @Test
    public void testResponseTimeCalculator() {
        final Calculator rtCalculator = this.probeSpecContext.getCalculatorFactory().buildResponseTimeCalculator("Test ResponseTime", Arrays.asList(startProbe,endProbe));

        rtCalculator.registerMeasurementSourceListener(new ICalculatorListener() {

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