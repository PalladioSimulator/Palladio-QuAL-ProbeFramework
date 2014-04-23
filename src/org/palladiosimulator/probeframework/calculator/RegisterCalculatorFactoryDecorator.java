package org.palladiosimulator.probeframework.calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.probes.Probe;

public class RegisterCalculatorFactoryDecorator implements ICalculatorFactory {

    private final ICalculatorFactory decoratedFactory;
    private final Map<String, Calculator> calculators = new HashMap<String, Calculator>();

    public RegisterCalculatorFactoryDecorator(final ICalculatorFactory calcFactory) {
        super();
        this.decoratedFactory = calcFactory;
    }

    /**
     * @param calculatorName
     * @param probes
     * @return
     * @see org.palladiosimulator.probeframework.calculator.ICalculatorFactory#buildResponseTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildResponseTimeCalculator(final String calculatorName, final List<Probe> probes) {
        if(calculators.containsKey(calculatorName)) {
            return calculators.get(calculatorName);
        }
        return register(decoratedFactory.buildResponseTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probes
     * @return
     * @see org.palladiosimulator.probeframework.calculator.ICalculatorFactory#buildDemandBasedWaitingTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildDemandBasedWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        if(calculators.containsKey(calculatorName)) {
            return calculators.get(calculatorName);
        }
        return register(decoratedFactory.buildDemandBasedWaitingTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probes
     * @return
     * @see org.palladiosimulator.probeframework.calculator.ICalculatorFactory#buildWaitingTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        if(calculators.containsKey(calculatorName)) {
            return calculators.get(calculatorName);
        }
        return register(decoratedFactory.buildWaitingTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probes
     * @return
     * @see org.palladiosimulator.probeframework.calculator.ICalculatorFactory#buildHoldTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildHoldTimeCalculator(final String calculatorName, final List<Probe> probes) {
        if(calculators.containsKey(calculatorName)) {
            return calculators.get(calculatorName);
        }
        return register(decoratedFactory.buildHoldTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probe
     * @return
     * @see org.palladiosimulator.probeframework.calculator.ICalculatorFactory#buildStateCalculator(java.lang.String, org.palladiosimulator.probeframework.probes.Probe)
     */
    @Override
    public Calculator buildStateCalculator(final String calculatorName, final Probe probe) {
        if(calculators.containsKey(calculatorName)) {
            return calculators.get(calculatorName);
        }
        return register(decoratedFactory.buildStateCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probe
     * @return
     * @see org.palladiosimulator.probeframework.calculator.ICalculatorFactory#buildOverallUtilizationCalculator(java.lang.String, org.palladiosimulator.probeframework.probes.Probe)
     */
    @Override
    public Calculator buildOverallUtilizationCalculator(final String calculatorName, final Probe probe) {
        if(calculators.containsKey(calculatorName)) {
            return calculators.get(calculatorName);
        }
        return register(decoratedFactory.buildOverallUtilizationCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probe
     * @return
     * @see org.palladiosimulator.probeframework.calculator.ICalculatorFactory#buildDemandCalculator(java.lang.String, org.palladiosimulator.probeframework.probes.Probe)
     */
    @Override
    public Calculator buildDemandCalculator(final String calculatorName, final Probe probe) {
        if(calculators.containsKey(calculatorName)) {
            return calculators.get(calculatorName);
        }
        return register(decoratedFactory.buildDemandCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probe
     * @return
     * @see org.palladiosimulator.probeframework.calculator.ICalculatorFactory#buildExecutionResultCalculator(java.lang.String, org.palladiosimulator.probeframework.probes.Probe)
     */
    @Override
    public Calculator buildExecutionResultCalculator(final String calculatorName, final Probe probe) {
        if(calculators.containsKey(calculatorName)) {
            return calculators.get(calculatorName);
        }
        return register(decoratedFactory.buildExecutionResultCalculator(calculatorName, probe), calculatorName);
    }
    
    @Override
    public Calculator buildIdentityCalculator(String calculatorName, Probe probe) {
        if(calculators.containsKey(calculatorName)) {
            return calculators.get(calculatorName);
        }
        return register(decoratedFactory.buildExecutionResultCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * @param probeFrameworkContext
     * @see org.palladiosimulator.probeframework.calculator.ICalculatorFactory#setProbeFrameworkContext(org.palladiosimulator.probeframework.ProbeFrameworkContext)
     */
    @Override
    public void setProbeFrameworkContext(final ProbeFrameworkContext probeFrameworkContext) {
        decoratedFactory.setProbeFrameworkContext(probeFrameworkContext);
    }

    public void finish() {
        for (final Calculator calculator : this.calculators.values()) {
            calculator.detachProbes();
            calculator.preUnregister();
        }
        // clear calculators
        calculators.clear();
    }

    public Calculator getCalculatorByName(final String calculatorName) {
        // If calculator already exists, return it
        if(calculators.containsKey(calculatorName)) {
            return calculators.get(calculatorName);
        }
        throw new IllegalArgumentException("Calculator not found.");
    }

    private Calculator register(final Calculator calculator, final String calculatorName) {
        calculators.put(calculatorName, calculator);
        return calculator;
    }

}
