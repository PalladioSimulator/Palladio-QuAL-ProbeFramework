package de.uka.ipd.sdq.probespec.framework.calculator;

import java.util.List;

import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.probes.Probe;

public interface ICalculatorFactory {

    public abstract Calculator buildResponseTimeCalculator(String calculatorName, List<Probe> probes);

    public abstract Calculator buildDemandBasedWaitingTimeCalculator(String calculatorName, List<Probe> probes);

    public abstract Calculator buildWaitingTimeCalculator(String calculatorName, List<Probe> probes);

    public abstract Calculator buildHoldTimeCalculator(String calculatorName, List<Probe> probes);

    public abstract Calculator buildStateCalculator(String calculatorName, Probe probe);

    public abstract Calculator buildOverallUtilizationCalculator(String calculatorName, Probe probe);

    public abstract Calculator buildDemandCalculator(String calculatorName, Probe probe);

    public abstract Calculator buildExecutionResultCalculator(String calculatorName, Probe probe);

    public abstract void setProbeSpecContext(ProbeSpecContext probeSpecContext);

}