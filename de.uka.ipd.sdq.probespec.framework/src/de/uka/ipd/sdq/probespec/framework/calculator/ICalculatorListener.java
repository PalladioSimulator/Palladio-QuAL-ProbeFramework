package de.uka.ipd.sdq.probespec.framework.calculator;

import de.uka.ipd.sdq.probespec.framework.measurements.IMeasurementSourceListener;


/**
 * Implement this interface to listen for the calculation results of a
 * {@link Calculator}.
 * 
 * @author Philipp Merkle, Sebastian Lehrig
 * 
 */
public interface ICalculatorListener extends IMeasurementSourceListener {

    /**
     * After having registered at a {@link Calculator}, this method gets invoked
     * to inform the listener about being unregistered.
     */
    public void preUnregister();

}
