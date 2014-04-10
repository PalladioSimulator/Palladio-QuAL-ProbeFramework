package org.palladiosimulator.probespec.framework;

import org.palladiosimulator.probespec.framework.calculator.ICalculatorFactory;
import org.palladiosimulator.probespec.framework.calculator.RegisterCalculatorFactoryDecorator;

/**
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
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

    public ICalculatorFactory getCalculatorFactory() {
        return calculatorFactory;
    }
    
    public void finish() {
        this.calculatorFactory.finish();
    }

}
