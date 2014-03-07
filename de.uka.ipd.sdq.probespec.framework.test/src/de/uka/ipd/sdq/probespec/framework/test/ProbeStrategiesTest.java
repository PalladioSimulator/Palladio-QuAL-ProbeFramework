package de.uka.ipd.sdq.probespec.framework.test;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import junit.framework.TestCase;
import de.uka.ipd.sdq.probespec.framework.ProbeSample;
import de.uka.ipd.sdq.probespec.framework.ProbeType;
import de.uka.ipd.sdq.probespec.framework.probes.IProbeStrategyRegistry;
import de.uka.ipd.sdq.probespec.framework.probes.example.ASimpleActiveResource;
import de.uka.ipd.sdq.probespec.framework.probes.example.ASimplePassiveResource;
import de.uka.ipd.sdq.probespec.framework.probes.example.ExampleProbeStrategyRegistry;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleCPUResource;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleDemandingComponent;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleMutEx;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleSEFFParameter;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleSimulationContext;
import de.uka.ipd.sdq.probespec.framework.probes.example.SimpleStoEx;

public class ProbeStrategiesTest extends TestCase {

	private IProbeStrategyRegistry registry;

	private SimpleSimulationContext ctx;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		ctx = new SimpleSimulationContext();
		registry = new ExampleProbeStrategyRegistry();
	}

	@SuppressWarnings("unchecked")
	public void testTakeCPUDemandStrategy() {
		ASimpleActiveResource cpu = new SimpleCPUResource();
		SimpleDemandingComponent demandingComponent = new SimpleDemandingComponent();
		demandingComponent.setDemand(cpu, 10);

		ProbeSample<Double, Dimensionless> sample = (ProbeSample<Double, Dimensionless>) registry.getProbeStrategy(ProbeType.RESOURCE_DEMAND, SimpleCPUResource.class)
				.takeSample("probeId", cpu, demandingComponent);

		assertEquals(10.0, sample.getMeasure().doubleValue(Dimensionless.UNIT));
	}

	@SuppressWarnings("unchecked")
	public void testTakeCPUStateStrategy() {
		ASimpleActiveResource cpu = new SimpleCPUResource();
		cpu.setJobs(5);
		ctx.addActiveResource("CPU 1", cpu);

		ProbeSample<Long, Dimensionless> sample = (ProbeSample<Long, Dimensionless>) registry
				.getProbeStrategy(ProbeType.RESOURCE_STATE,
						SimpleCPUResource.class).takeSample("probeId", cpu);

		assertEquals(5, sample.getMeasure().longValue(Dimensionless.UNIT));
	}

	@SuppressWarnings("unchecked")
	public void testTakeCurrentTimeStrategy() {
		ctx.setSimulatedTime(150.0); // seconds

		ProbeSample<Double, Duration> sample = (ProbeSample<Double, Duration>) registry
				.getProbeStrategy(ProbeType.CURRENT_TIME, null).takeSample(
						"probeId", ctx);

		assertTrue(sample.getMeasure().compareTo(Measure.valueOf(150.0, SI.SECOND)) == 0);
	}

	@SuppressWarnings("unchecked")
	public void testTakePassiveResourceStateStrategy() {
		ASimplePassiveResource mutex = new SimpleMutEx();
		if (mutex.canAcquire())
			mutex.acquire();

		ProbeSample<Long, Dimensionless> sample1 = (ProbeSample<Long, Dimensionless>) registry
				.getProbeStrategy(ProbeType.RESOURCE_STATE,
						ASimplePassiveResource.class).takeSample("probeId",
						mutex);

		assertEquals(0, sample1.getMeasure().longValue(Dimensionless.UNIT));

		mutex.release();

		ProbeSample<Long, Dimensionless> sample2 = (ProbeSample<Long, Dimensionless>) registry
				.getProbeStrategy(ProbeType.RESOURCE_STATE,
						ASimplePassiveResource.class).takeSample("probeId",
						mutex);

		assertEquals(1, sample2.getMeasure().longValue(Dimensionless.UNIT));
	}

	@SuppressWarnings("unchecked")
	public void testTakeSEFFParameterStrategy() {
		SimpleSEFFParameter<Double> param = new SimpleSEFFParameter<Double>();
		param.setValue(12.345);

		ProbeSample<?, Dimensionless> sample = (ProbeSample<?, Dimensionless>) registry
				.getProbeStrategy(ProbeType.SEFF_PARAMETER,
						SimpleSEFFParameter.class).takeSample("probeId", param);

		assertEquals(12.345, sample.getMeasure()
				.doubleValue(Dimensionless.UNIT));
	}

	@SuppressWarnings("unchecked")
	public void testTakeStoExStrategy() {
		SimpleStoEx stoex = new SimpleStoEx();
		stoex.setEvaluatedExpression(10.2);

		ProbeSample<Double, Dimensionless> sample = (ProbeSample<Double, Dimensionless>) registry
				.getProbeStrategy(ProbeType.STOEX, SimpleStoEx.class)
				.takeSample("probeId", stoex);

		assertEquals(10.2, sample.getMeasure().doubleValue(Dimensionless.UNIT));
	}
	
}
