package org.palladiosimulator.probeframework.calculator;

import java.util.List;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Factory interface to create calculator objects. This interface covers all calculators that are
 * allowed to be instantiated from within the internal package
 * <code>org.palladiosimulator.probeframework.calculator.internal</code> (access without
 * implementing the factory interface is discouraged).
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public interface ICalculatorFactory {

    public abstract void setProbeFrameworkContext(ProbeFrameworkContext probeFrameworkContext);

    /**
     * Creates a response time calculator object based on the given name and the given probes.
     * 
     * @param calculatorName
     *            Name of the calculator, e.g., "Response Time of Operation A".
     * @param probes
     *            List of two probes for starting point of the operation call and final point of the
     *            operation call.
     * @return A new response time calculator object.
     */
    public abstract Calculator buildResponseTimeCalculator(String calculatorName, List<Probe> probes);

    /**
     * Creates a demand-based waiting time calculator object based on the given name and the given
     * probes.
     * 
     * @param calculatorName
     *            Name of the calculator, e.g., "Demand-based Waiting Time at Passive Resource A".
     * @param probes
     *            List of three probes for (1) start request for processing, (2) end of processing
     *            event, and (3) the demanded time at the resource.
     * @return A new demand-based waiting time calculator object.
     */
    public abstract Calculator buildDemandBasedWaitingTimeCalculator(String calculatorName, List<Probe> probes);

    /**
     * Creates a waiting time calculator object based on the given name and the given probes.
     * 
     * @param calculatorName
     *            Name of the calculator, e.g., "Waiting Time at Passive Resource A".
     * @param probes
     *            List of two probes for starting point of the waiting time and final point of
     *            waiting time.
     * @return A new waiting time calculator object.
     */
    public abstract Calculator buildWaitingTimeCalculator(String calculatorName, List<Probe> probes);

    /**
     * Creates a hold time calculator object based on the given name and the given probes.
     * 
     * @param calculatorName
     *            Name of the calculator, e.g., "Hold Time at Passive Resource A".
     * @param probes
     *            List of two probes for starting point of hold time and final point of hold time.
     * @return A new hold time time calculator object.
     */
    public abstract Calculator buildHoldTimeCalculator(String calculatorName, List<Probe> probes);

    /**
     * Creates a state calculator object based on the given name and the given probe.
     * 
     * @param calculatorName
     *            Name of the calculator, e.g., "Utilization of CPU 1".
     * @param probe
     *            A single probe providing the resource state of interest.
     * @return A new state calculator object.
     */
    public abstract Calculator buildStateCalculator(String calculatorName, Probe probe);

    /**
     * Creates an overall utilization calculator object based on the given name and the given probe.
     * 
     * @param calculatorName
     *            Name of the calculator, e.g., "Overall Utilization of CPU 1".
     * @param probe
     *            A single probe providing the resource state of interest.
     * @return A new overall utilization calculator object.
     */
    public abstract Calculator buildOverallUtilizationCalculator(String calculatorName, Probe probe);

    /**
     * Creates a demand calculator object based on the given name and the given probe.
     * 
     * @param calculatorName
     *            Name of the calculator, e.g., "Demand to CPU 1".
     * @param probe
     *            A single probe providing the resource demand.
     * @return A new demand calculator object.
     */
    public abstract Calculator buildDemandCalculator(String calculatorName, Probe probe);

    /**
     * Creates an execution result calculator object based on the given name and the given probe.
     * 
     * @param calculatorName
     *            Name of the calculator, e.g., "Execution results of Operation A".
     * @param probe
     *            A single probe providing the execution results.
     * @return A new execution result calculator object.
     */
    public abstract Calculator buildExecutionResultCalculator(String calculatorName, Probe probe);

    /**
     * Creates an identity calculator object based on the given name and the given probe.
     * 
     * @param calculatorName
     *            Name of the calculator, e.g., "Execution results of Operation A" (an execution
     *            result calculator can be realized using an identity calculator).
     * @param probe
     *            A single probe directly providing the result of interest.
     * @return A new identity calculator object.
     */
    public abstract Calculator buildIdentityCalculator(String calculatorName, Probe probe);

}