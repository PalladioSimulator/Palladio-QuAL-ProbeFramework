package org.palladiosimulator.probeframework.calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Decorates an existing ICalculatorFactory by a register for its calculators. In case a calculator
 * is registered in this register, this factory directly returns the calculator from it. Otherwise,
 * a new calculator is instantiated, put into the register, and returned.
 * 
 * Furthermore, this class provides convenience methods for cleaning up calculators (detach their
 * probes and cleaning the register; c.f., <code>finish</code> method) as well as requesting a
 * calculator by its name (c.f., <code>getCalculatorByName</code> method).
 * 
 * @see ICalculatorFactory
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public class RegisterCalculatorFactoryDecorator implements ICalculatorFactory {

    /** Factory to be decorated by a register. */
    private final ICalculatorFactory decoratedFactory;

    /** Register for calculators. */
    private final Map<String, Calculator> calculatorRegister = new HashMap<String, Calculator>();

    /**
     * Default constructor. Decorates given factory by a register.
     * 
     * @param decoratedFactory
     *            The calculator factory to be decorated.
     */
    public RegisterCalculatorFactoryDecorator(final ICalculatorFactory decoratedFactory) {
        super();
        this.decoratedFactory = decoratedFactory;
    }

    /**
     * Cleans up all registered calculators by detaching all probes, informing each calculator about
     * being unregistered, and cleaning the register.
     */
    public void finish() {
        for (final Calculator calculator : this.calculatorRegister.values()) {
            calculator.preUnregister();
        }
        // clear calculatorRegister
        calculatorRegister.clear();
    }

    /**
     * Returns the requested calculator from the register based on the given calculator name. If not
     * present in the register, an <code>IllegalArgumentException</code> is thrown.
     * 
     * @param calculatorName
     *            The name of the requested calculator.
     * @return The requested calculator.
     */
    public Calculator getCalculatorByName(final String calculatorName) {
        // If calculator already exists, return it
        if (calculatorRegister.containsKey(calculatorName)) {
            return calculatorRegister.get(calculatorName);
        }
        throw new IllegalArgumentException("Calculator not found.");
    }

    /**
     * Registers the given calculator in the register using the given name as an access key.
     * 
     * @param calculator
     *            The calculator to be registered.
     * @param calculatorName
     *            The name of the calculator to be used as access key.
     * @return The unmodified calculator (allows for fluent API).
     */
    private Calculator register(final Calculator calculator, final String calculatorName) {
        calculatorRegister.put(calculatorName, calculator);
        return calculator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildResponseTimeCalculator(final String calculatorName, final List<Probe> probes) {
        if (calculatorRegister.containsKey(calculatorName)) {
            return calculatorRegister.get(calculatorName);
        }
        return register(decoratedFactory.buildResponseTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildDemandBasedWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        if (calculatorRegister.containsKey(calculatorName)) {
            return calculatorRegister.get(calculatorName);
        }
        return register(decoratedFactory.buildDemandBasedWaitingTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        if (calculatorRegister.containsKey(calculatorName)) {
            return calculatorRegister.get(calculatorName);
        }
        return register(decoratedFactory.buildWaitingTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildHoldTimeCalculator(final String calculatorName, final List<Probe> probes) {
        if (calculatorRegister.containsKey(calculatorName)) {
            return calculatorRegister.get(calculatorName);
        }
        return register(decoratedFactory.buildHoldTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildStateCalculator(final String calculatorName, final Probe probe) {
        if (calculatorRegister.containsKey(calculatorName)) {
            return calculatorRegister.get(calculatorName);
        }
        return register(decoratedFactory.buildStateCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildOverallUtilizationCalculator(final String calculatorName, final Probe probe) {
        if (calculatorRegister.containsKey(calculatorName)) {
            return calculatorRegister.get(calculatorName);
        }
        return register(decoratedFactory.buildOverallUtilizationCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildDemandCalculator(final String calculatorName, final Probe probe) {
        if (calculatorRegister.containsKey(calculatorName)) {
            return calculatorRegister.get(calculatorName);
        }
        return register(decoratedFactory.buildDemandCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildExecutionResultCalculator(final String calculatorName, final Probe probe) {
        if (calculatorRegister.containsKey(calculatorName)) {
            return calculatorRegister.get(calculatorName);
        }
        return register(decoratedFactory.buildExecutionResultCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calculator buildIdentityCalculator(String calculatorName, Probe probe) {
        if (calculatorRegister.containsKey(calculatorName)) {
            return calculatorRegister.get(calculatorName);
        }
        return register(decoratedFactory.buildExecutionResultCalculator(calculatorName, probe), calculatorName);
    }
}
