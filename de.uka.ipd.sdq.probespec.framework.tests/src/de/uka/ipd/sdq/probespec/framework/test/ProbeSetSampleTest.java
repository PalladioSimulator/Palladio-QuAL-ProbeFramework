package de.uka.ipd.sdq.probespec.framework.test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import javax.measure.quantity.Quantity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import de.uka.ipd.sdq.probespec.framework.ProbeSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeType;
import de.uka.ipd.sdq.probespec.framework.RequestContext;
import de.uka.ipd.sdq.probespec.framework.matching.ProbeTypeMatchRule;
import de.uka.ipd.sdq.probespec.framework.probes.ProbeStrategyRegistry;
import de.uka.ipd.sdq.probespec.framework.probes.example.ExampleProbeStrategyRegistry;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleSimulationContext;

@RunWith(JUnit4.class)
public class ProbeSetSampleTest {

    private ProbeStrategyRegistry probeStrategyRegistry;

    private SimpleSimulationContext simCtx;

    @Before
    protected void setUp() throws Exception {
        simCtx = new SimpleSimulationContext();

        // Obtain the suitable probeFactory
        probeStrategyRegistry = new ExampleProbeStrategyRegistry();
    }

    @After
    protected void tearDown() throws Exception {
    }

    @Test
    public void testProbeSetSample() {
        simCtx.setSimulatedTime(100d);

        final List<ProbeSample<?, ? extends Quantity>> v = new LinkedList<ProbeSample<?, ? extends Quantity>>();

        // Take a sample of the current simulation time
        final Object measurableEntity = null; // here, we measure the "simulated" time, there is no entity
        v.add(probeStrategyRegistry.getProbeStrategy(ProbeType.CURRENT_TIME, measurableEntity).takeSample("myProbeID", simCtx));

        // Create a ProbeSetSample
        final RequestContext ctxID = new RequestContext("1");
        final Integer probeSetId = 1;
        final ProbeSetSample pss = new ProbeSetSample(v, ctxID, "x", probeSetId);

        // Check, whether the probeSetSample returns the correct value for the
        // previously measured simulation time
        assertEquals(100d, pss.getProbeSamples(new ProbeTypeMatchRule(ProbeType.CURRENT_TIME)).get(0).getMeasure().getValue());
    }

}
