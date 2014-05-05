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
 * Default implementation of ICalculatorFactory. Directly creates calculators, with minimal overhead
 * for other concerns. Therefore, the factory methods only check for valid probe inputs (checks for
 * non-null and correct number of probes).
 * 
 * @see ICalculatorFactory
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public class DefaultCalculatorFactory implements ICalculatorFactory {

    /** ProbeSpecContext needed for creating calculators. */
    private ProbeFrameworkContext probeFrameworkContext;

    /**
     * Default constructor. Nothing special.
     */
    public DefaultCalculatorFactory() {
        super();
    }

    /**
     * Ensures that the given list of probes contains exactly two probes. Otherwise, it throws an
     * IllegalArgumentException.
     * 
     * @param probes
     *            The list of probes
     */
    private void ensureTwoProbes(final List<Probe> probes) {
        if (probes == null || probes.size() != 2 || probes.get(0) == null || probes.get(1) == null) {
            throw new IllegalArgumentException("You have to provide exactly two probes for this calcultor.");
        }
    }

    /**
     * Ensures that the given probe is a valid probe, i.e., not null. Otherwise, it throws an
     * IllegalArgumentException.
     * 
     * @param probe
     *            The probe
     */
    private void ensureValidProbe(final Probe probe) {
        if (probe == null) {
            throw new IllegalArgumentException("You have to provide exactly one valid probes for this calcultor.");
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setProbeFrameworkContext(final ProbeFrameworkContext probeFrameworkContext) {
        this.probeFrameworkContext = probeFrameworkContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildResponseTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new ResponseTimeCalculator(this.probeFrameworkContext, "Response Time", "Response Time of "
                + calculatorName, probes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildDemandBasedWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new DemandBasedWaitingTimeCalculator(this.probeFrameworkContext, "Waiting time", "Waiting time at "
                + calculatorName, probes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new WaitingTimeCalculator(this.probeFrameworkContext, "Waiting time", "Waiting time at "
                + calculatorName, probes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildHoldTimeCalculator(final String calculatorName, final List<Probe> probes) {
        ensureTwoProbes(probes);
        return new HoldTimeCalculator(this.probeFrameworkContext, "Hold Time", "Hold time at " + calculatorName, probes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildStateCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeFrameworkContext, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildOverallUtilizationCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeFrameworkContext, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildDemandCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeFrameworkContext, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildExecutionResultCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeFrameworkContext, probe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildIdentityCalculator(final String calculatorName, final Probe probe) {
        ensureValidProbe(probe);
        return new IdentityCalculator(this.probeFrameworkContext, probe);
    }    
}
