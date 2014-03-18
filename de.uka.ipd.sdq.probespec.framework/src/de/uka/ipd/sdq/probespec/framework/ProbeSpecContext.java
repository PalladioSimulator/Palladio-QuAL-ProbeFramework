package de.uka.ipd.sdq.probespec.framework;

import java.util.HashSet;
import java.util.Set;

import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory;

public class ProbeSpecContext {

    private final ICalculatorFactory calculatorFactory;

    private final Set<Calculator> calculators;

    public ProbeSpecContext(final ICalculatorFactory calculatorFactory) {
        super();
        if (calculatorFactory == null) {
            throw new IllegalArgumentException("A valid calculator factory is required.");
        }
        this.calculatorFactory = calculatorFactory;
        calculators = new HashSet<Calculator>();
    }

    public void finish() {
        // remove all listeners from used calculators
        for (final Calculator c : calculators) {
            c.unregisterCalculatorListeners();
        }

        // clear calculators
        calculators.clear();
    }

    public ICalculatorFactory getCalculatorFactory() {
        return calculatorFactory;
    }

    public void addCalculator(final Calculator calculator) {
        this.calculators.add(calculator);
    }

}
