package de.uka.ipd.sdq.probespec.framework.calculator;


public interface ICalculatorFactory {

	public abstract Calculator buildResponseTimeCalculator(
			String calculatorName, Integer startProbeSetID, Integer endProbeSetID);

	public abstract Calculator buildDemandBasedWaitingTimeCalculator(
			String calculatorName, Integer startWaitingProbeSetID,
			Integer stopProcessingProbeSetID);

	public abstract Calculator buildWaitingTimeCalculator(
			String calculatorName, Integer startWaitingProbeSetID,
			Integer stopWaitingProbeSetID);
	
	public abstract Calculator buildHoldTimeCalculator(
			String calculatorName, Integer startWaitingProbeSetID,
			Integer stopWaitingProbeSetID);

	public abstract Calculator buildStateCalculator(String calculatorName,
			Integer probeSetId);
	
	public abstract Calculator buildOverallUtilizationCalculator(String calculatorName,
			Integer probeSetId);

	public abstract Calculator buildDemandCalculator(
			String calculatorName, Integer probeSetID);

	public abstract Calculator buildExecutionResultCalculator(
			String calculatorName, Integer probeSetID);

}