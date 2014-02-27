package de.uka.ipd.sdq.probespec.framework.test;

import java.util.Vector;

import javax.measure.quantity.Quantity;

import junit.framework.TestCase;
import de.uka.ipd.sdq.probespec.framework.ProbeSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeType;
import de.uka.ipd.sdq.probespec.framework.RequestContext;
import de.uka.ipd.sdq.probespec.framework.matching.IMatchRule;
import de.uka.ipd.sdq.probespec.framework.matching.ProbeTypeMatchRule;
import de.uka.ipd.sdq.probespec.framework.probes.ProbeStrategyRegistry;
import de.uka.ipd.sdq.probespec.framework.probes.example.ExampleProbeStrategyRegistry;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleSimulationContext;

public class ProbeSetSampleTest extends TestCase {

    private ProbeStrategyRegistry probeStrategyRegistry;

    private SimpleSimulationContext simCtx;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        simCtx = new SimpleSimulationContext();

        // Obtain the suitable probeFactory
        probeStrategyRegistry = new ExampleProbeStrategyRegistry();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testProbeSetSample() {
        simCtx.setSimulatedTime(100d);

        Vector<ProbeSample<?, ? extends Quantity>> v = new Vector<ProbeSample<?, ? extends Quantity>>();

        // Take a sample of the current simulation time
        Object measurableEntity = null; // here, we measure the "simulated" time, there is no entity
        v.add(probeStrategyRegistry.getProbeStrategy(ProbeType.CURRENT_TIME, measurableEntity).takeSample("myProbeID", simCtx));

        // Create a ProbeSetSample
        RequestContext ctxID = new RequestContext("1");
        Integer probeSetId = 1;
        ProbeSetSample pss = new ProbeSetSample(v, ctxID, "x", probeSetId);

        // Check, whether the probeSetSample returns the correct value for the
        // previously measured simulation time
        IMatchRule[] rules = { new ProbeTypeMatchRule(ProbeType.CURRENT_TIME) };
        assertEquals(100d, pss.getProbeSamples(rules).get(0).getMeasure().getValue());
    }

}
