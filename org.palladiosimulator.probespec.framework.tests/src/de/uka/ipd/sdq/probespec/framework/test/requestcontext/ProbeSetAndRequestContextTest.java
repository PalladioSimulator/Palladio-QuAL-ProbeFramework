package de.uka.ipd.sdq.probespec.framework.test.requestcontext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.measure.quantity.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import de.uka.ipd.sdq.probespec.framework.probes.BasicTriggeredProbe;
import de.uka.ipd.sdq.probespec.framework.probes.example.ExampleTakeCurrentTimeStrategy;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleSimulationContext;
import de.uka.ipd.sdq.probespec.framework.requestcontext.MeasurementSourceAndRequestContext;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

/**
 * 
 * @author Faber
 * 
 */
@RunWith(JUnit4.class)
public class ProbeSetAndRequestContextTest {

    private MeasurementSourceAndRequestContext pssID1_1_1;
    private MeasurementSourceAndRequestContext pssID1_1_2;
    private MeasurementSourceAndRequestContext pssID2_3_1;
    private MeasurementSourceAndRequestContext pssID1_3_1;
    private MeasurementSourceAndRequestContext pssID3_1_1;

    public ProbeSetAndRequestContextTest() {
    }

    @Before
    public void setUp() {
        final BasicTriggeredProbe<Double,Duration> probe1 = new ExampleTakeCurrentTimeStrategy(new SimpleSimulationContext());
        final BasicTriggeredProbe<Double,Duration> probe2 = new ExampleTakeCurrentTimeStrategy(new SimpleSimulationContext());
        final BasicTriggeredProbe<Double,Duration> probe3 = new ExampleTakeCurrentTimeStrategy(new SimpleSimulationContext());
        pssID1_1_1 = new MeasurementSourceAndRequestContext(probe1, new RequestContext("1"));
        pssID1_1_2 = new MeasurementSourceAndRequestContext(probe1, new RequestContext("1"));
        pssID1_3_1 = new MeasurementSourceAndRequestContext(probe1, new RequestContext("3"));
        pssID3_1_1 = new MeasurementSourceAndRequestContext(probe3, new RequestContext("1"));
        pssID2_3_1 = new MeasurementSourceAndRequestContext(probe2, new RequestContext("3"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSame() {
        assertEquals(pssID1_1_1,pssID1_1_1);
        assertEquals(pssID1_1_1,pssID1_1_2);
    }

    @Test
    public void testDifferentTrue() {
        assertFalse(pssID1_1_1.equals(pssID3_1_1));
    }

    @Test
    public void testDifferentFalse() {
        assertFalse(pssID1_1_1.equals(pssID2_3_1));
        assertFalse(pssID1_1_1.equals(pssID1_3_1));
        assertFalse(pssID1_1_1.equals(pssID3_1_1));
    }

}
