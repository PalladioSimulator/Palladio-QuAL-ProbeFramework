package de.uka.ipd.sdq.probespec.framework.calculator;

import java.util.HashMap;


/**
 * 
 * @author Philipp Merkle
 *
 */
public class CalculatorRegistry {

	private static final CalculatorRegistry instance = new CalculatorRegistry();

	private HashMap<String, Calculator> calculators;

	/**
	 * Default constructor. This class is a singleton, thus the constructor is
	 * private.
	 */
	private CalculatorRegistry() {
		calculators = new HashMap<String, Calculator>();
	}

	public void registerCalculator(String calculatorId, Calculator calculator) {
		calculators.put(calculatorId, calculator);
	}
	
	public void unregisterCalculator(String calculatorId) {
		// TODO Implementation
	}

	public Calculator getCalculatorForId(String calculatorId) {
		return calculators.get(calculatorId);
	}

	public static CalculatorRegistry getInstance() {
		return instance;
	}

}
