package de.uka.ipd.sdq.probespec.framework;

import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.calculator.DemandCalculator;
import de.uka.ipd.sdq.probespec.framework.calculator.StateCalculator;
import de.uka.ipd.sdq.probespec.framework.calculator.WaitingTimeCalculator;

public interface ICalculatorFactory {

	public abstract Calculator buildResponseTimeCalculator(
			String calculatorName, String startProbeSetID, String endProbeSetID);

	public abstract WaitingTimeCalculator buildDemandBasedWaitingTimeCalculator(
			String calculatorName, String startWaitingProbeSetID,
			String stopProcessingProbeSetID);

	public abstract WaitingTimeCalculator buildWaitingTimeCalculator(
			String calculatorName, String startWaitingProbeSetID,
			String stopWaitingProbeSetID);

	public abstract StateCalculator buildStateCalculator(String calculatorName,
			String probeSetId);

	public abstract DemandCalculator buildDemandCalculator(
			String calculatorName, String probeSetID);

}