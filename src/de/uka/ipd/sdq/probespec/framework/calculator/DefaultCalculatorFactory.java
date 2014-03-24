/**
 * 
 */
package de.uka.ipd.sdq.probespec.framework.calculator;

import java.util.List;

import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.calculator.internal.DemandBasedWaitingTimeCalculator;
import de.uka.ipd.sdq.probespec.framework.calculator.internal.DemandCalculator;
import de.uka.ipd.sdq.probespec.framework.calculator.internal.HoldTimeCalculator;
import de.uka.ipd.sdq.probespec.framework.calculator.internal.ResponseTimeCalculator;
import de.uka.ipd.sdq.probespec.framework.calculator.internal.StateCalculator;
import de.uka.ipd.sdq.probespec.framework.calculator.internal.WaitingTimeCalculator;
import de.uka.ipd.sdq.probespec.framework.measurements.IMeasurementSource;
import de.uka.ipd.sdq.probespec.framework.probes.Probe;

/**
 * @author snowball
 *
 */
public class DefaultCalculatorFactory implements ICalculatorFactory {

    private ProbeSpecContext probeSpecContext;

    /**
     * 
     */
    public DefaultCalculatorFactory() {
        super();
    }

    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.probespec.framework.ICalculatorFactory#buildResponseTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildResponseTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new ResponseTimeCalculator(this.probeSpecContext, "Response Time", "Response Time of "+calculatorName, probes);
    }

    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.probespec.framework.ICalculatorFactory#buildDemandBasedWaitingTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildDemandBasedWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new DemandBasedWaitingTimeCalculator(this.probeSpecContext, "Waiting time", "Waiting time at " + calculatorName, probes);
    }

    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.probespec.framework.ICalculatorFactory#buildWaitingTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new WaitingTimeCalculator(this.probeSpecContext, "Waiting time", "Waiting time at "+calculatorName, probes);
    }

    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.probespec.framework.ICalculatorFactory#buildHoldTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildHoldTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new HoldTimeCalculator(this.probeSpecContext, "Hold Time", "Hold time at "+calculatorName, probes);
    }

    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.probespec.framework.ICalculatorFactory#buildStateCalculator(java.lang.String, de.uka.ipd.sdq.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildStateCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new StateCalculator(this.probeSpecContext, "State", "State of "+calculatorName, probe);
    }

    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.probespec.framework.ICalculatorFactory#buildOverallUtilizationCalculator(java.lang.String, de.uka.ipd.sdq.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildOverallUtilizationCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new StateCalculator(this.probeSpecContext, "Utilization", "Utilization of "+calculatorName, probe);
    }

    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.probespec.framework.ICalculatorFactory#buildDemandCalculator(java.lang.String, de.uka.ipd.sdq.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildDemandCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new DemandCalculator(this.probeSpecContext, "Demand", "Demand at "+calculatorName, probe);
    }

    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.probespec.framework.ICalculatorFactory#buildExecutionResultCalculator(java.lang.String, de.uka.ipd.sdq.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildExecutionResultCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new StateCalculator(this.probeSpecContext, "Execution result", "Execution result of "+calculatorName, probe);
    }

    private void ensureTwoProbes(final List<Probe> probes) {
        if (probes == null || probes.size() != 2 || probes.get(0) == null || probes.get(1) == null) {
            throw new IllegalArgumentException("You have to provide exactly two probes for this calcultor.");
        }
    }

    private void ensureValidProbe(final IMeasurementSource probe) {
        if (probe == null) {
            throw new IllegalArgumentException("You have to provide exactly one valid probes for this calcultor.");
        }
    }

    @Override
    public void setProbeSpecContext(final ProbeSpecContext probeSpecContext) {
        this.probeSpecContext = probeSpecContext;
    }

}
