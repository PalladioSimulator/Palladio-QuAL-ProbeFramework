/**
 * 
 */
package org.palladiosimulator.probespec.framework.calculator;

import java.util.List;

import org.palladiosimulator.measurementspec.IMeasurementSource;
import org.palladiosimulator.probespec.framework.ProbeSpecContext;
import org.palladiosimulator.probespec.framework.calculator.internal.DemandBasedWaitingTimeCalculator;
import org.palladiosimulator.probespec.framework.calculator.internal.HoldTimeCalculator;
import org.palladiosimulator.probespec.framework.calculator.internal.IdentityCalculator;
import org.palladiosimulator.probespec.framework.calculator.internal.ResponseTimeCalculator;
import org.palladiosimulator.probespec.framework.calculator.internal.WaitingTimeCalculator;
import org.palladiosimulator.probespec.framework.probes.Probe;

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
     * @see org.palladiosimulator.probespec.framework.ICalculatorFactory#buildResponseTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildResponseTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new ResponseTimeCalculator(this.probeSpecContext, "Response Time", "Response Time of "+calculatorName, probes);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probespec.framework.ICalculatorFactory#buildDemandBasedWaitingTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildDemandBasedWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new DemandBasedWaitingTimeCalculator(this.probeSpecContext, "Waiting time", "Waiting time at " + calculatorName, probes);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probespec.framework.ICalculatorFactory#buildWaitingTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new WaitingTimeCalculator(this.probeSpecContext, "Waiting time", "Waiting time at "+calculatorName, probes);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probespec.framework.ICalculatorFactory#buildHoldTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildHoldTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new HoldTimeCalculator(this.probeSpecContext, "Hold Time", "Hold time at "+calculatorName, probes);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probespec.framework.ICalculatorFactory#buildStateCalculator(java.lang.String, org.palladiosimulator.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildStateCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeSpecContext, probe);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probespec.framework.ICalculatorFactory#buildOverallUtilizationCalculator(java.lang.String, org.palladiosimulator.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildOverallUtilizationCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeSpecContext, probe);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probespec.framework.ICalculatorFactory#buildDemandCalculator(java.lang.String, org.palladiosimulator.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildDemandCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeSpecContext, probe);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probespec.framework.ICalculatorFactory#buildExecutionResultCalculator(java.lang.String, org.palladiosimulator.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildExecutionResultCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeSpecContext, probe);
    }
    
    @Override
    public Calculator buildIdentityCalculator(String calculatorName, Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeSpecContext, probe);
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
