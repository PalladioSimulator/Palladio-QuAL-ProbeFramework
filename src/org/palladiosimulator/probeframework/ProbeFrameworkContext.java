package org.palladiosimulator.probeframework;

import org.palladiosimulator.probeframework.calculator.ICalculatorFactory;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;

/**
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public class ProbeFrameworkContext {

    private final RegisterCalculatorFactoryDecorator calculatorFactory;

    public ProbeFrameworkContext(final ICalculatorFactory calculatorFactory) {
        super();
        if (calculatorFactory == null) {
            throw new IllegalArgumentException("A valid calculator factory is required.");
        }
        this.calculatorFactory = new RegisterCalculatorFactoryDecorator(calculatorFactory);
        this.calculatorFactory.setProbeFrameworkContext(this);
    }

    public ICalculatorFactory getCalculatorFactory() {
        return calculatorFactory;
    }
    
    public void finish() {
        this.calculatorFactory.finish();
    }

}
