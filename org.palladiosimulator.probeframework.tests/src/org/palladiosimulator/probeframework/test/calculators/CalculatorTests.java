package org.palladiosimulator.probeframework.test.calculators;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.listener.IMeasurementSourceListener;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.DefaultCalculatorFactory;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.probeframework.probes.example.ExampleTakeCurrentTimeProbe;
import org.palladiosimulator.probeframework.probes.example.SimpleSimulationContext;

/**
 * JUnit tests for calculators of the Probe Framework. The tests use the simple example simulator
 * and probes from the <code>org.palladiosimulator.probeframework.probes.example</code> package.
 * 
 * TODO Add more tests besides the response time calculator. 
 * 
 * @author Sebastian Lehrig, Steffen Becker
 */
@RunWith(JUnit4.class)
public class CalculatorTests {

    /** The simulation context used to access simulation state such as simulation time. */
    private SimpleSimulationContext simContext;

    /**
     * The probe framework context is needed to create calculators because it provides the access to
     * the calculator factory.
     */
    private ProbeFrameworkContext probeFrameworkContext;

    /** Measures the start of an operation call as needed for response time calculation. */
    private TriggeredProbe startProbe;

    /** Measures the end of an operation call as needed for response time calculation. */
    private TriggeredProbe endProbe;

    /**
     * Stores the last received measurement for the <code>testResponseTimeCalculator</code> test
     * case.
     */
    private Measurement lastMeasurement;

    /**
     * Initializes the member variables for simulation context, probe framework context, and probes.
     */
    @Before
    public void setUp() {
        simContext = new SimpleSimulationContext();
        probeFrameworkContext = new ProbeFrameworkContext(new DefaultCalculatorFactory());

        startProbe = new ExampleTakeCurrentTimeProbe(simContext);
        endProbe = new ExampleTakeCurrentTimeProbe(simContext);
    }

    /**
     * Test case for the {@link ResponseTimeCalculator} that calculates the response time of an
     * operation call in seconds (i.e., it measures a response time metric). As soon as a new
     * response time measurement is available, an observer registered at the calculator stores it in
     * the <code>lastMeasurement</code> member variable.
     * 
     * The test lets the simulation start at 0 seconds, take the start probe measurement, run for
     * 100 seconds, and take the end probe measurement. The end probe measurement particularly
     * triggers the response time calculator.
     */
    @Test
    public void testResponseTimeCalculator() {
        final Calculator rtCalculator = this.probeFrameworkContext.getCalculatorFactory().buildResponseTimeCalculator(
                "Test ResponseTime", Arrays.asList((Probe) startProbe, (Probe) endProbe));

        rtCalculator.addObserver(new IMeasurementSourceListener() {

            @Override
            public void newMeasurementAvailable(final Measurement measurement) {
                lastMeasurement = measurement;
            }

            @Override
            public void preUnregister() {
            }
        });

        simContext.setSimulatedTime(0.0d);
        final RequestContext requestContext = new RequestContext("1");

        startProbe.takeMeasurement(requestContext);

        simContext.setSimulatedTime(100.d);
        assertTrue(lastMeasurement == null);

        endProbe.takeMeasurement(requestContext);

        assertTrue(lastMeasurement != null);
        assertTrue(lastMeasurement.getMetricDesciption() == rtCalculator.getMetricDesciption());
    }
}
