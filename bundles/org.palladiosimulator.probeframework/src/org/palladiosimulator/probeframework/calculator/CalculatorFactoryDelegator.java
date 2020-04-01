package org.palladiosimulator.probeframework.calculator;

import java.util.List;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.probeframework.probes.Probe;

public abstract class CalculatorFactoryDelegator implements ICalculatorFactory {

	@Override
	public Calculator buildResponseTimeCalculator(MeasuringPoint measuringPoint, List<Probe> probes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildDemandBasedWaitingTimeCalculator(MeasuringPoint measuringPoint, List<Probe> probes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildWaitingTimeCalculator(MeasuringPoint measuringPoint, List<Probe> probes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildHoldingTimeCalculator(MeasuringPoint measuringPoint, List<Probe> probes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildStateOfActiveResourceCalculator(MeasuringPoint measuringPoint, Probe probe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildOverallStateOfActiveResourceCalculator(MeasuringPoint measuringPoint, Probe probe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildStateOfPassiveResourceCalculator(MeasuringPoint measuringPoint, Probe probe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildOverallStateOfPassiveResourceCalculator(MeasuringPoint measuringPoint, Probe probe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildResourceDemandCalculator(MeasuringPoint measuringPoint, Probe probe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildExecutionResultCalculator(MeasuringPoint measuringPoint, Probe probe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildNumberOfResourceContainersCalculator(MeasuringPoint measuringPoint, Probe probe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildReconfigurationTimeCalculator(MeasuringPoint measuringPoint, Probe probe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildCostOverTimeCalculator(MeasuringPoint measuringPoint, Probe probe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildAggregatedCostOverTimeCalculator(MeasuringPoint measuringPoint, Probe probe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calculator buildOptimisationTimeCalculator(MeasuringPoint measuringPoint, Probe probe) {
		// TODO Auto-generated method stub
		return null;
	}

}
