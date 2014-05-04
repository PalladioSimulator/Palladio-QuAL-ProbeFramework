package org.palladiosimulator.probeframework.exceptions;

/**
 * This Exception arises if something during the execution of the Calculator went wrong.
 * 
 * @author Faber
 * 
 */
public class CalculatorException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = -5967757360389441076L;

    public CalculatorException(String desc) {
        super(desc);
    }

}
