package org.palladiosimulator.probeframework.calculator;

/**
 * Clients can register with the calculator registry to be notified upon
 * changes.
 * 
 * @author Sebastian Krach
 *
 */
public interface CalculatorRegistryListener {

	/**
	 * Called by the calculator registry, once a new {@code Calculator} has been
	 * registered.
	 * 
	 * @param calculator The newly registered calculator.
	 */
	void notifyCalculatorRegistration(Calculator calculator);

}
