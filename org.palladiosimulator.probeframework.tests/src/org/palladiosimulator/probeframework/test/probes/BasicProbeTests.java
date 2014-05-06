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
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.measurement.ProbeAndRequestContext;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.example.ExampleTakeCPUDemandProbe;
import org.palladiosimulator.probeframework.probes.example.ExampleTakeCPUStateProbe;
import org.palladiosimulator.probeframework.probes.example.ExampleTakeCurrentTimeProbe;
import org.palladiosimulator.probeframework.probes.example.SimpleCPUResource;
import org.palladiosimulator.probeframework.probes.example.SimpleSimulationContext;
import org.palladiosimulator.probeframework.probes.listener.IProbeListener;

/**
 * JUnit tests for basic probes (for both types, basic triggered probes and basic event probes) of
 * the Probe Framework. The tests use the simple example simulator, CPU, and probes from the
 * <code>org.palladiosimulator.probeframework.probes.example</code> package.
 * 
 * @author Sebastian Lehrig, Steffen Becker
 */
@RunWith(JUnit4.class)
public class BasicProbeTests {

    /** The simulation context used to access simulation state such as simulation time */
    private SimpleSimulationContext simContext;

    /**
     * Initializes the simulation context.
     */
    @Before
    public void setUp() {
        simContext = new SimpleSimulationContext();
    }

    /**
     * Nothing to do.
     * 
     * {@inheritDoc}
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test case for the {@link ExampleTakeCurrentTimeProbe} that takes the current simulation time
     * of a simulation in seconds (i.e., it measures a point in time metric). The test lets the
     * simulation run for 100 seconds and then probes.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testCurrentTimeProbe() {
        final ExampleTakeCurrentTimeProbe probe = new ExampleTakeCurrentTimeProbe(simContext);
        final RequestContext contextID = new RequestContext("1");

        simContext.setSimulatedTime(100d);
        final ProbeMeasurement probeMeasurement = probe.takeMeasurement(contextID);

        final Measurement measurement = probeMeasurement.getMeasurement();
        final ProbeAndRequestContext probeAndContext = probeMeasurement.getProbeAndContext();
        assertTrue(probeAndContext.getProbe() == probe);
        assertTrue(measurement.getMetricDesciption() == MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        assertTrue(probeAndContext.getRequestContext() == contextID);
        assertTrue(measurement instanceof BasicMeasurement<?, ?>);

        final BasicMeasurement<Double, Duration> basicMeasurement = (BasicMeasurement<Double, Duration>) measurement;
        final Measure<Double, Duration> measure = basicMeasurement
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        assertTrue(measure.getUnit().isCompatible(SI.SECOND));
        assertTrue(measure.compareTo(Measure.valueOf(100d, SI.SECOND)) == 0);
    }

    /**
     * Test case for the {@link ExampleTakeCPUStateProbe} that takes the dimensionless current
     * number of jobs within an active resource, which is in this case a CPU (i.e., it measures a
     * CPU state metric). The test creates a test CPU, adds 2 jobs to its scheduler, and then
     * probes.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testCPUStateProbe() {
        final RequestContext contextID = new RequestContext("1");
        final SimpleCPUResource cpuResource = new SimpleCPUResource();
        simContext.addActiveResource("Test CPU", cpuResource);
        final ExampleTakeCPUStateProbe probe = new ExampleTakeCPUStateProbe(cpuResource);

        cpuResource.setJobs(2);
        final ProbeMeasurement probeMeasurement = probe.takeMeasurement(contextID);

        final Measurement measurement = probeMeasurement.getMeasurement();
        final ProbeAndRequestContext probeAndContext = probeMeasurement.getProbeAndContext();
        assertTrue(probeAndContext.getProbe() == probe);
        assertTrue(measurement.getMetricDesciption() == MetricDescriptionConstants.CPU_STATE_METRIC);
        assertTrue(probeAndContext.getRequestContext() == contextID);

        assertTrue(measurement instanceof BasicMeasurement<?, ?>);
        final BasicMeasurement<Double, Dimensionless> basicMeasurement = (BasicMeasurement<Double, Dimensionless>) measurement;

        final Measure<Double, Dimensionless> measure = basicMeasurement
                .getMeasureForMetric(MetricDescriptionConstants.CPU_STATE_METRIC);
        assertTrue(measure.getUnit().isCompatible(Unit.ONE));
        assertTrue(measure.compareTo(Measure.valueOf(2L, Unit.ONE)) == 0);
    }

    /** Stores the last received measurement for the <code>testDemandProbe</code> test case. */
    private ProbeMeasurement lastMeasurement;

    /**
     * Test case for the {@link ExampleTakeCPUDemandProbe} that takes the demand emitted from an
     * active resource (event source), which is in this case a CPU (i.e., it measures a resource
     * demand metric). The test creates a test CPU and adds a job to its scheduler that demands 10
     * seconds of CPU. The probe is registered to react on this demand event and stores the received
     * probe measurement in the <code>lastMeasurement</code> member variable.
     */
    @Test
    public void testDemandProbe() {
        lastMeasurement = null;
        final SimpleCPUResource cpuResource = new SimpleCPUResource();
        simContext.addActiveResource("Test CPU", cpuResource);

        final ExampleTakeCPUDemandProbe probe = new ExampleTakeCPUDemandProbe(cpuResource);
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
        final BasicMeasurement<Double, Duration> result = (BasicMeasurement<Double, Duration>) lastMeasurement
                .getMeasurement();
        final Measure<Double, Duration> resultMeasure = result
                .getMeasureForMetric(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC);
        assertTrue(resultMeasure.compareTo(Measure.valueOf(10.0d, SI.SECOND)) == 0);
    }

}
