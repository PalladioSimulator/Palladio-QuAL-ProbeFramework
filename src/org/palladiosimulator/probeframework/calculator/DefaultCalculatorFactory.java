/**
 * 
 */
package org.palladiosimulator.probeframework.calculator;

import java.util.List;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.internal.DemandBasedWaitingTimeCalculator;
import org.palladiosimulator.probeframework.calculator.internal.HoldTimeCalculator;
import org.palladiosimulator.probeframework.calculator.internal.IdentityCalculator;
import org.palladiosimulator.probeframework.calculator.internal.ResponseTimeCalculator;
import org.palladiosimulator.probeframework.calculator.internal.WaitingTimeCalculator;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * @author snowball
 *
 */
public class DefaultCalculatorFactory implements ICalculatorFactory {

    private ProbeFrameworkContext probeFrameworkContext;

    /**
     * 
     */
    public DefaultCalculatorFactory() {
        super();
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probeframework.ICalculatorFactory#buildResponseTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildResponseTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new ResponseTimeCalculator(this.probeFrameworkContext, "Response Time", "Response Time of "+calculatorName, probes);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probeframework.ICalculatorFactory#buildDemandBasedWaitingTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildDemandBasedWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new DemandBasedWaitingTimeCalculator(this.probeFrameworkContext, "Waiting time", "Waiting time at " + calculatorName, probes);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probeframework.ICalculatorFactory#buildWaitingTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new WaitingTimeCalculator(this.probeFrameworkContext, "Waiting time", "Waiting time at "+calculatorName, probes);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probeframework.ICalculatorFactory#buildHoldTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildHoldTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new HoldTimeCalculator(this.probeFrameworkContext, "Hold Time", "Hold time at "+calculatorName, probes);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probeframework.ICalculatorFactory#buildStateCalculator(java.lang.String, org.palladiosimulator.probeframework.probes.Probe)
     */
    @Override
    public Calculator buildStateCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeFrameworkContext, probe);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probeframework.ICalculatorFactory#buildOverallUtilizationCalculator(java.lang.String, org.palladiosimulator.probeframework.probes.Probe)
     */
    @Override
    public Calculator buildOverallUtilizationCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeFrameworkContext, probe);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probeframework.ICalculatorFactory#buildDemandCalculator(java.lang.String, org.palladiosimulator.probeframework.probes.Probe)
     */
    @Override
    public Calculator buildDemandCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeFrameworkContext, probe);
    }

    /* (non-Javadoc)
     * @see org.palladiosimulator.probeframework.ICalculatorFactory#buildExecutionResultCalculator(java.lang.String, org.palladiosimulator.probeframework.probes.Probe)
     */
    @Override
    public Calculator buildExecutionResultCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeFrameworkContext, probe);
    }

    @Override
    public Calculator buildIdentityCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeFrameworkContext, probe);
    }

    private void ensureTwoProbes(final List<Probe> probes) {
        if (probes == null || probes.size() != 2 || probes.get(0) == null || probes.get(1) == null) {
            throw new IllegalArgumentException("You have to provide exactly two probes for this calcultor.");
        }
    }

    private void ensureValidProbe(final Probe probe) {
        if (probe == null) {
            throw new IllegalArgumentException("You have to provide exactly one valid probes for this calcultor.");
        }
    }

    @Override
    public void setProbeFrameworkContext(final ProbeFrameworkContext probeFrameworkContext) {
        this.probeFrameworkContext = probeFrameworkContext;
    }

}
