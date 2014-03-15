package de.uka.ipd.sdq.probespec.framework.test;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Quantity;

import junit.framework.TestCase;
import de.uka.ipd.sdq.probespec.framework.ProbeSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeType;
import de.uka.ipd.sdq.probespec.framework.RequestContext;
import de.uka.ipd.sdq.probespec.framework.matching.ProbeIDMatchRule;
import de.uka.ipd.sdq.probespec.framework.matching.ProbeTypeMatchRule;

public class MatchRulesTest extends TestCase {

	private ProbeSample<Long, Dimensionless> ps1;

	private ProbeSample<Long, Dimensionless> ps2;

	private List<ProbeSample<?, ? extends Quantity>> v;

	private ProbeSetSample pss;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Create first probeSample
		Measure<Long, Dimensionless> measure1 = Measure.valueOf(100l,
				Dimensionless.UNIT);
		ps1 = new ProbeSample<Long, Dimensionless>(measure1,
				"CPUCore1Probe", ProbeType.RESOURCE_STATE);

		// Create second probeSample
		Measure<Long, Dimensionless> measure2 = Measure.valueOf(100l,
				Dimensionless.UNIT);
		ps2 = new ProbeSample<Long, Dimensionless>(measure2,
				"CPUCore2Probe", ProbeType.RESOURCE_STATE);

		// Create a probe set sample from the probe samples
		v = new ArrayList<ProbeSample<?, ? extends Quantity>>(2);
		v.add(ps1);
		v.add(ps2);
		pss = new ProbeSetSample(v, new RequestContext("1"),
				"modelElementID", 1);
	}

	public void testProbeIDMatchRule1() {
		List<ProbeSample<?, ? extends Quantity>> result = pss
				.getProbeSamples(new ProbeIDMatchRule("CPUCore1Probe"));

		assertTrue(result.size() == 1);
		assertTrue(result.get(0) == ps1);
	}

	public void testProbeIDMatchRule2() {
		List<ProbeSample<?, ? extends Quantity>> result = pss
				.getProbeSamples(new ProbeIDMatchRule("CPUCore2Probe"));

		assertTrue(result.size() == 1);
		assertTrue(result.get(0) == ps2);
	}

	public void testProbeTypeMatchRule() {
		List<ProbeSample<?, ? extends Quantity>> result = pss
				.getProbeSamples(new ProbeTypeMatchRule(
						ProbeType.RESOURCE_STATE));

		assertTrue(result.size() == 2);
		assertTrue(result.contains(ps1));
		assertTrue(result.contains(ps2));
	}

}
