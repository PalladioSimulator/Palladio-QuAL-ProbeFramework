package org.palladiosimulator.probeframework.test.requestcontext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.measure.quantity.Duration;

import org.junit.After;
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
 * 
 * @author Faber, Sebastian Lehrig
 */
@RunWith(JUnit4.class)
public class ProbeSetAndRequestContextTest {

    private ProbeAndRequestContext pssProbe1Context1A;
    private ProbeAndRequestContext pssProbe1Context1B;
    private ProbeAndRequestContext pssProbe1Context3;
    private ProbeAndRequestContext pssProbe2Context3;
    private ProbeAndRequestContext pssProbe3Context1;

    /**
     * Default constructor (empty).
     */
    public ProbeSetAndRequestContextTest() {
    }

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
        pssProbe3Context1 = new ProbeAndRequestContext(probe3, new RequestContext("1"));
        pssProbe2Context3 = new ProbeAndRequestContext(probe2, new RequestContext("3"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSame() {
        assertEquals(pssProbe1Context1A, pssProbe1Context1A);
        assertEquals(pssProbe1Context1A, pssProbe1Context1B);
    }

    @Test
    public void testDifferentTrue() {
        assertFalse(pssProbe1Context1A.equals(pssProbe3Context1));
    }

    @Test
    public void testDifferentFalse() {
        assertFalse(pssProbe1Context1A.equals(pssProbe2Context3));
        assertFalse(pssProbe1Context1A.equals(pssProbe1Context3));
        assertFalse(pssProbe1Context1A.equals(pssProbe3Context1));
    }

}
