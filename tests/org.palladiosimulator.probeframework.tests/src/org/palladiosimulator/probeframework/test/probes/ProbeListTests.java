package org.palladiosimulator.probeframework.test.probes;

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
import org.palladiosimulator.measurementframework.measureprovider.MeasurementListMeasureProvider;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.EventProbeList;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.probeframework.probes.TriggeredProbeList;
import org.palladiosimulator.probeframework.probes.example.ExampleTakeCPUDemandProbe;
import org.palladiosimulator.probeframework.probes.example.ExampleTakeCPUStateProbe;
import org.palladiosimulator.probeframework.probes.example.ExampleTakeCurrentTimeProbe;
import org.palladiosimulator.probeframework.probes.example.SimpleCPUResource;
import org.palladiosimulator.probeframework.probes.example.SimpleSimulationContext;
import org.palladiosimulator.probeframework.probes.listener.IProbeListener;

/**
 * JUnit tests for probe lists (for both types, triggered probe lists and event probe lists) of the
 * Probe Framework. The tests use the simple example simulator, CPU, and probes from the
 * <code>org.palladiosimulator.probeframework.probes.example</code> package.
 *
 * @author Sebastian Lehrig, Steffen Becker
 */
@RunWith(JUnit4.class)
public class ProbeListTests {

    /** Number of metrics used in <code>testEventProbeList</code> method. */
    private static final int NUMBER_OF_CONFIGURED_METRICS = 3;

    /** The simulation context used to access simulation state such as simulation time. */
    private SimpleSimulationContext simContext;

    /** An simple example CPU active resource. */
    private SimpleCPUResource cpuResource;

    /** A probe taking the current time of a simulation. */
    private TriggeredProbe currentTimeProbe;

    /** A probe taking the current CPU state of a simulation (number of jobs in this test). */
    private TriggeredProbe currentCPUStateProbe;

    /**
     * A list of triggered probes, used to store the CPU state as (currentTimeProbe,
     * currentCPUStateProbe)-tuples.
     */
    private TriggeredProbeList probeList;

    /** Stores the last received measurement for the <code>testEventProbeList</code> test case. */
    private ProbeMeasurement lastMeasurement;

    /**
     * Initializes all member variables (simulation context, CPU resource, probes).
     */
    @Before
    public void setUp() {
        simContext = new SimpleSimulationContext();
        cpuResource = new SimpleCPUResource();

        currentTimeProbe = new ExampleTakeCurrentTimeProbe(simContext);
        currentCPUStateProbe = new ExampleTakeCPUStateProbe(cpuResource);
        probeList = new TriggeredProbeList(MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_OVER_TIME_METRIC,Arrays.asList(currentTimeProbe, currentCPUStateProbe));
    }

    /**
     * Test case for the {@link TriggeredProbeList} that takes (currentTimeProbe,
     * currentCPUStateProbe)-tuples (i.e., it includes a point in time metric measured in seconds
     * and a dimensionless CPU state metric). The test lets a simulation with 2 jobs run for 100
     * seconds and then triggers the probe list.
     */
    @Test
    public void testTriggeredProbeList() {
        simContext.setSimulatedTime(100.0d);
        cpuResource.setJobs(2);
        final RequestContext requestContext = new RequestContext("1");

        final ProbeMeasurement probeMeasure = probeList.takeMeasurement(requestContext);

        assertTrue(probeMeasure != null);
        assertTrue(probeMeasure.getMeasureProvider() instanceof MeasurementListMeasureProvider);

        final MeasurementListMeasureProvider measureProvider = (MeasurementListMeasureProvider) probeMeasure
                .getMeasureProvider();

        assertEquals(measureProvider.asList().size(), 2);
        assertEquals(measureProvider.getSubsumedMeasurements().get(0).getMetricDesciption(),
                MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        assertEquals(measureProvider.getSubsumedMeasurements().get(1).getMetricDesciption(),
                MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC);
    }

    /**
     * Test case for an {@link EventProbeList} that takes the demand emitted from an active resource
     * (event source), which is in this case a CPU (i.e., it measures a resource demand metric). It
     * also adds the result from triggering a (currentTimeProbe, currentCPUStateProbe)-tuple to its
     * measurement result.
     *
     * The test lets a simulation with 2 jobs run for 100 seconds and demands 20 seconds of CPU. The
     * event probe list is registered to react on this demand event and stores the received probe
     * measurement in the <code>lastMeasurement</code> member variable.
     */
    @Test
    public void testEventProbeList() {
        lastMeasurement = null;
        simContext.setSimulatedTime(100.0d);
        cpuResource.setJobs(2);
        final ExampleTakeCPUDemandProbe probe = new ExampleTakeCPUDemandProbe(cpuResource);

        final EventProbeList eventProbeList = new EventProbeList(
                MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_OVER_TIME_METRIC,
                probe,
                Arrays.asList(currentTimeProbe, currentCPUStateProbe));
        eventProbeList.addObserver(new IProbeListener() {

            @Override
            public void newProbeMeasurementAvailable(final ProbeMeasurement measurement) {
                lastMeasurement = measurement;
            }

        });

        cpuResource.demand(20.0d);

        assertTrue(lastMeasurement != null);
        assertTrue(lastMeasurement.getMeasureProvider() instanceof MeasurementListMeasureProvider);

        final Measure<Double, Duration> demandResult = lastMeasurement.getMeasureProvider().getMeasureForMetric(
                MetricDescriptionConstants.RESOURCE_DEMAND_METRIC);
        final Measure<Double, Duration> timeResult = lastMeasurement.getMeasureProvider().getMeasureForMetric(
                MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        final Measure<Long, Dimensionless> stateResult = lastMeasurement.getMeasureProvider().getMeasureForMetric(
                MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC);

        assertTrue(demandResult != null);
        assertTrue(demandResult.compareTo(Measure.valueOf(20.0d, SI.SECOND)) == 0);

        final MeasurementListMeasureProvider measureProvider = (MeasurementListMeasureProvider) lastMeasurement
                .getMeasureProvider();

        assertEquals(measureProvider.getSubsumedMeasurements().size(), NUMBER_OF_CONFIGURED_METRICS);
        assertEquals(measureProvider.getSubsumedMeasurements().get(1).getMetricDesciption(),
                MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        assertTrue(timeResult.compareTo(Measure.valueOf(100.0d, SI.SECOND)) == 0);
        assertEquals(measureProvider.getSubsumedMeasurements().get(2).getMetricDesciption(),
                MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC);
        assertTrue(stateResult.compareTo(Measure.valueOf(2L, Unit.ONE)) == 0);
    }
}
