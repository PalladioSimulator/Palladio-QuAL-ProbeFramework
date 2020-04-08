package org.palladiosimulator.probeframework.test.calculators;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory;
import org.palladiosimulator.edp2.models.measuringpoint.StringMeasuringPoint;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.DefaultCalculatorProbeSets;
import org.palladiosimulator.probeframework.calculator.ExtensibleCalculatorFactoryDelegatingFactory;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.probeframework.measurement.RequestContext;
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
    private MeasuringValue lastMeasurement;

    /** Default EMF factory for measuring points. */
    private final MeasuringpointFactory measuringpointFactory = MeasuringpointFactory.eINSTANCE;

    /**
     * Initializes the member variables for simulation context, probe framework context, and probes.
     */
    @Before
    public void setUp() {
        simContext = new SimpleSimulationContext();
        probeFrameworkContext = new ProbeFrameworkContext(new RegisterCalculatorFactoryDecorator(
                new ExtensibleCalculatorFactoryDelegatingFactory()));

        startProbe = new ExampleTakeCurrentTimeProbe(simContext);
        endProbe = new ExampleTakeCurrentTimeProbe(simContext);
    }

    /**
     * Test case for a calculator that calculates the response time of an operation call in seconds
     * (i.e., it measures a response time metric). As soon as a new response time measurement is
     * available, an observer registered at the calculator stores it in the
     * <code>lastMeasurement</code> member variable.
     * 
     * The test lets the simulation start at 0 seconds, take the start probe measurement, run for
     * 100 seconds, and take the end probe measurement. The end probe measurement particularly
     * triggers the response time calculator.
     */
    @Test
    public void testResponseTimeCalculator() {
        final StringMeasuringPoint serviceCallAMeasuringPoint = measuringpointFactory.createStringMeasuringPoint();
        serviceCallAMeasuringPoint.setMeasuringPoint("Operation Call A");
        final Calculator rtCalculator = this.probeFrameworkContext.getGenericCalculatorFactory()
                .buildCalculator(
                        MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, 
                        serviceCallAMeasuringPoint,
                        DefaultCalculatorProbeSets.createStartStopProbeConfiguration(startProbe, endProbe));

        rtCalculator.addObserver(new IMeasurementSourceListener() {

            @Override
            public void newMeasurementAvailable(final MeasuringValue measurement) {
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

    /**
     * Equal calculators are only allowed to be created once.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterCalculatorFactory() {
        final StringMeasuringPoint serviceCallAMeasuringPoint = measuringpointFactory.createStringMeasuringPoint();
        serviceCallAMeasuringPoint.setMeasuringPoint("Operation Call A");

        this.probeFrameworkContext.getGenericCalculatorFactory().buildCalculator(
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, serviceCallAMeasuringPoint,
                DefaultCalculatorProbeSets.createStartStopProbeConfiguration(startProbe, endProbe));

        this.probeFrameworkContext.getGenericCalculatorFactory().buildCalculator(
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, serviceCallAMeasuringPoint,
                DefaultCalculatorProbeSets.createStartStopProbeConfiguration(startProbe, endProbe));
    }
}
