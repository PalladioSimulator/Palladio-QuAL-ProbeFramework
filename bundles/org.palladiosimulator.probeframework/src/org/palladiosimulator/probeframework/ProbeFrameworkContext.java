package org.palladiosimulator.probeframework;

import org.palladiosimulator.probeframework.calculator.ICalculatorFactory;
import org.palladiosimulator.probeframework.calculator.ICalculatorFactoryLegacyAdapter;
import org.palladiosimulator.probeframework.calculator.IGenericCalculatorFactory;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;

/**
 * This class allows to create context objects for the Probe Framework, i.e., these objects store
 * state information needed by the Probe Framework. The central state information is the employed
 * calculator factory. Therefore, this class maintains a calculator factory member variable. In
 * particular, this class provides the <code>getCalculatorFactory</code> getter method for this
 * factory as well as a <code>finish</code> method for informing the factory about the end of
 * calculator usage.
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public class ProbeFrameworkContext {

    /** The used calculator factory */
    private final RegisterCalculatorFactoryDecorator calculatorFactory;
    
    /** The legacy adapter */
    private final ICalculatorFactory legacyFactory;

    /**
     * Default constructor. Expects a calculator factory to be stored as Probe Framework state
     * information. Internally, this factory is decorated by a register to manage the life-cycle of
     * calculators.
     * 
     * @param calculatorFactory
     *            The calculator factory to be used by the Probe Framework.
     */
    public ProbeFrameworkContext(final IGenericCalculatorFactory calculatorFactory) {
        super();
        if (calculatorFactory == null) {
            throw new IllegalArgumentException("A valid calculator factory is required.");
        }
        this.calculatorFactory = new RegisterCalculatorFactoryDecorator(calculatorFactory);
        this.legacyFactory = ICalculatorFactoryLegacyAdapter.createDelegatingAdapter(calculatorFactory);
    }

    /**
     * Getter for the calculator factory.
     * 
     * @return The calculator factory.
     * 
     * @deprecated Please use <code>getGenericCalculatorFactory</code> instead.
     */
    @Deprecated
    public ICalculatorFactory getCalculatorFactory() {
        return legacyFactory;
    }

	/**
	 * Gets the Calculator Factory registered for the current ProbeFrameworkContext.
	 * 
	 * @return the calculator factory
	 * 
	 */
	public IGenericCalculatorFactory getGenericCalculatorFactory() {
		return calculatorFactory;
	}

    /**
     * Call-back method informing about the end of calculator usage.
     */
    public void finish() {
        this.calculatorFactory.finish();
    }

}
