package de.uka.ipd.sdq.probespec.framework;

import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory;
import de.uka.ipd.sdq.probespec.framework.calculator.RegisterCalculatorFactoryDecorator;

public class ProbeSpecContext {

    private final RegisterCalculatorFactoryDecorator calculatorFactory;

    public ProbeSpecContext(final ICalculatorFactory calculatorFactory) {
        super();
        if (calculatorFactory == null) {
            throw new IllegalArgumentException("A valid calculator factory is required.");
        }
        this.calculatorFactory = new RegisterCalculatorFactoryDecorator(calculatorFactory);
        this.calculatorFactory.setProbeSpecContext(this);
    }

    public void finish() {
        this.calculatorFactory.finish();
    }

    public ICalculatorFactory getCalculatorFactory() {
        return calculatorFactory;
    }
    
    public Calculator getCalculatorByName(String calculatorName) {
        return this.calculatorFactory.getCalculatorByName(calculatorName);
    }

}
