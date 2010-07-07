package de.uka.ipd.sdq.probespec.framework;

import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.calculator.DemandCalculator;
import de.uka.ipd.sdq.probespec.framework.calculator.StateCalculator;
import de.uka.ipd.sdq.probespec.framework.calculator.WaitingTimeCalculator;

public interface ICalculatorFactory {

	public abstract Calculator buildResponseTimeCalculator(
			String calculatorName, Integer startProbeSetID, Integer endProbeSetID);

	public abstract WaitingTimeCalculator buildDemandBasedWaitingTimeCalculator(
			String calculatorName, Integer startWaitingProbeSetID,
			Integer stopProcessingProbeSetID);

	public abstract WaitingTimeCalculator buildWaitingTimeCalculator(
			String calculatorName, Integer startWaitingProbeSetID,
			Integer stopWaitingProbeSetID);

	public abstract StateCalculator buildStateCalculator(String calculatorName,
			Integer probeSetId);

	public abstract DemandCalculator buildDemandCalculator(
			String calculatorName, Integer probeSetID);

}