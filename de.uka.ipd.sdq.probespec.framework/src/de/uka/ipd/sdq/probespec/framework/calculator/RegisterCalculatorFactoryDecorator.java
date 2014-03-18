package de.uka.ipd.sdq.probespec.framework.calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.probes.Probe;

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
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildResponseTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildResponseTimeCalculator(final String calculatorName, final List<Probe> probes) {
        return register(decoratedFactory.buildResponseTimeCalculator(calculatorName, probes), calculatorName);
    }

    private Calculator register(final Calculator calculator, final String calculatorName) {
        // If calculator already exists, return it
        if(calculators.containsKey(calculatorName)) {
            return calculators.get(calculatorName);
        }
        // else create a new one
        calculators.put(calculatorName, calculator);

        return calculator;
    }

    /**
     * @param calculatorName
     * @param probes
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildDemandBasedWaitingTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildDemandBasedWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        return register(decoratedFactory.buildDemandBasedWaitingTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probes
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildWaitingTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildWaitingTimeCalculator(final String calculatorName, final List<Probe> probes) {
        return register(decoratedFactory.buildWaitingTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probes
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildHoldTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildHoldTimeCalculator(final String calculatorName, final List<Probe> probes) {
        return register(decoratedFactory.buildHoldTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probe
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildStateCalculator(java.lang.String, de.uka.ipd.sdq.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildStateCalculator(final String calculatorName, final Probe probe) {
        return register(decoratedFactory.buildStateCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probe
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildOverallUtilizationCalculator(java.lang.String, de.uka.ipd.sdq.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildOverallUtilizationCalculator(final String calculatorName, final Probe probe) {
        return register(decoratedFactory.buildOverallUtilizationCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probe
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildDemandCalculator(java.lang.String, de.uka.ipd.sdq.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildDemandCalculator(final String calculatorName, final Probe probe) {
        return register(decoratedFactory.buildDemandCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probe
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildExecutionResultCalculator(java.lang.String, de.uka.ipd.sdq.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildExecutionResultCalculator(final String calculatorName, final Probe probe) {
        return register(decoratedFactory.buildExecutionResultCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * @param probeSpecContext
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#setProbeSpecContext(de.uka.ipd.sdq.probespec.framework.ProbeSpecContext)
     */
    @Override
    public void setProbeSpecContext(final ProbeSpecContext probeSpecContext) {
        decoratedFactory.setProbeSpecContext(probeSpecContext);
    }

    public void finish() {
        for (final Calculator calculator : this.calculators.values()) {
            calculator.unregisterCalculatorListeners();
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

}
