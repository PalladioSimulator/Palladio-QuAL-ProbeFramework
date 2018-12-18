package org.palladiosimulator.probeframework.test.requestcontext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.measure.quantity.Duration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.palladiosimulator.probeframework.measurement.ProbeAndRequestContext;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.BasicTriggeredProbe;
import org.palladiosimulator.probeframework.probes.example.ExampleTakeCurrentTimeProbe;
import org.palladiosimulator.probeframework.probes.example.SimpleSimulationContext;

/**
 * JUnit tests for probe and request contexts of the Probe Framework. The tests check whether
 * request contexts are uniquely identified by the combination of probe and of request context.
 * 
 * @author Faber, Sebastian Lehrig, Steffen Becker
 */
@RunWith(JUnit4.class)
public class ProbeAndRequestContextTest {

    /** ProbeAndRequestContext for probe 1 and context 1. */
    private ProbeAndRequestContext pssProbe1Context1A;

    /** Another ProbeAndRequestContext for probe 1 and context 1. */
    private ProbeAndRequestContext pssProbe1Context1B;

    /** ProbeAndRequestContext for probe 1 and context 3. */
    private ProbeAndRequestContext pssProbe1Context3;

    /** ProbeAndRequestContext for probe 2 and context 3. */
    private ProbeAndRequestContext pssProbe2Context3;

    /** ProbeAndRequestContext for probe 3 and context 1. */
    private ProbeAndRequestContext pssProbe3Context1;

    /**
     * Initializes all probe and request contexts using 3 different current time probes and two
     * different contexts.
     */
    @Before
    public void setUp() {
        final BasicTriggeredProbe<Double, Duration> probe1 = new ExampleTakeCurrentTimeProbe(
                new SimpleSimulationContext());
        final BasicTriggeredProbe<Double, Duration> probe2 = new ExampleTakeCurrentTimeProbe(
                new SimpleSimulationContext());
        final BasicTriggeredProbe<Double, Duration> probe3 = new ExampleTakeCurrentTimeProbe(
                new SimpleSimulationContext());
        pssProbe1Context1A = new ProbeAndRequestContext(probe1, new RequestContext("1"));
        pssProbe1Context1B = new ProbeAndRequestContext(probe1, new RequestContext("1"));
        pssProbe1Context3 = new ProbeAndRequestContext(probe1, new RequestContext("3"));
        pssProbe2Context3 = new ProbeAndRequestContext(probe2, new RequestContext("3"));
        pssProbe3Context1 = new ProbeAndRequestContext(probe3, new RequestContext("1"));        
    }

    /**
     * Test case for {@link ProbeAndRequestContext} that tests whether the only equal probe and
     * request contexts are indeed recognized to be equal.
     */
    @Test
    public void testSame() {
        assertEquals(pssProbe1Context1A, pssProbe1Context1A);
        assertEquals(pssProbe1Context1A, pssProbe1Context1B);
    }

    /**
     * Test case for {@link ProbeAndRequestContext} that tests that all unequal probe and request
     * contexts are indeed recognized to be unequal.
     */
    @Test
    public void testDifferentFalse() {
        assertFalse(pssProbe1Context1A.equals(pssProbe1Context3));
        assertFalse(pssProbe1Context1A.equals(pssProbe2Context3));        
        assertFalse(pssProbe1Context1A.equals(pssProbe3Context1));
    }

}
