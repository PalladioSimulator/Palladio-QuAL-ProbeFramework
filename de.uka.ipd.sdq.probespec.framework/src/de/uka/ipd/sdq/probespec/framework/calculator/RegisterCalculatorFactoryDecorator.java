package de.uka.ipd.sdq.probespec.framework.calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.probes.Probe;

public class RegisterCalculatorFactoryDecorator implements ICalculatorFactory {
    
    private final ICalculatorFactory decoratedFactory = null;
    private final Map<String, Calculator> calculators = new HashMap<String, Calculator>();
    private ProbeSpecContext probeSpecContext;
    
    public RegisterCalculatorFactoryDecorator(final ICalculatorFactory calcFactory) {
        
        
    }

    /**
     * @param calculatorName
     * @param probes
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildResponseTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildResponseTimeCalculator(String calculatorName, List<Probe> probes) {
        return register(decoratedFactory.buildResponseTimeCalculator(calculatorName, probes), calculatorName);
    }

    private Calculator register(Calculator calculator, String calculatorName) {
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
    public Calculator buildDemandBasedWaitingTimeCalculator(String calculatorName, List<Probe> probes) {
        return register(decoratedFactory.buildDemandBasedWaitingTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probes
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildWaitingTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildWaitingTimeCalculator(String calculatorName, List<Probe> probes) {
        return register(decoratedFactory.buildWaitingTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probes
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildHoldTimeCalculator(java.lang.String, java.util.List)
     */
    @Override
    public Calculator buildHoldTimeCalculator(String calculatorName, List<Probe> probes) {
        return register(decoratedFactory.buildHoldTimeCalculator(calculatorName, probes), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probe
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildStateCalculator(java.lang.String, de.uka.ipd.sdq.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildStateCalculator(String calculatorName, Probe probe) {
        return register(decoratedFactory.buildStateCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probe
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildOverallUtilizationCalculator(java.lang.String, de.uka.ipd.sdq.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildOverallUtilizationCalculator(String calculatorName, Probe probe) {
        return register(decoratedFactory.buildOverallUtilizationCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probe
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildDemandCalculator(java.lang.String, de.uka.ipd.sdq.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildDemandCalculator(String calculatorName, Probe probe) {
        return register(decoratedFactory.buildDemandCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * @param calculatorName
     * @param probe
     * @return
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#buildExecutionResultCalculator(java.lang.String, de.uka.ipd.sdq.probespec.framework.probes.Probe)
     */
    @Override
    public Calculator buildExecutionResultCalculator(String calculatorName, Probe probe) {
        return register(decoratedFactory.buildExecutionResultCalculator(calculatorName, probe), calculatorName);
    }

    /**
     * @param probeSpecContext
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory#setProbeSpecContext(de.uka.ipd.sdq.probespec.framework.ProbeSpecContext)
     */
    @Override
    public void setProbeSpecContext(ProbeSpecContext probeSpecContext) {
        this.probeSpecContext = probeSpecContext;
        decoratedFactory.setProbeSpecContext(probeSpecContext);
    }
    
    public void finish() {
        for (Calculator calculator : this.calculators.values()) {
            calculator.unregisterCalculatorListeners();
        }
        // clear calculators
        calculators.clear();
    }
    
    public Calculator getCalculatorByName(String calculatorName) {
        // If calculator already exists, return it
        if(calculators.containsKey(calculatorName)) {
            return calculators.get(calculatorName);
        }
        throw new IllegalArgumentException("Calculator not found.");
    }

}
