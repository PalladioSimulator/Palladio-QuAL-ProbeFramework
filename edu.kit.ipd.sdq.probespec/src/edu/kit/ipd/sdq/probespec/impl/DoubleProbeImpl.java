/**
 */
package edu.kit.ipd.sdq.probespec.impl;

import edu.kit.ipd.sdq.probespec.DoubleProbe;
import edu.kit.ipd.sdq.probespec.probespecPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Double Probe</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DoubleProbeImpl extends ProbeImpl<Double> implements DoubleProbe {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected DoubleProbeImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return probespecPackage.Literals.DOUBLE_PROBE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public Class<Double> getGenericClass() {
        return Double.class;
    }

} //DoubleProbeImpl
