/**
 */
package edu.kit.ipd.sdq.probespec;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see edu.kit.ipd.sdq.probespec.probespecPackage
 * @generated
 */
public interface probespecFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	probespecFactory eINSTANCE = edu.kit.ipd.sdq.probespec.impl.probespecFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Probe Repository</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Probe Repository</em>'.
     * @generated
     */
	ProbeRepository createProbeRepository();

	/**
     * Returns a new object of class '<em>Double Probe</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Double Probe</em>'.
     * @generated
     */
	DoubleProbe createDoubleProbe();

	/**
     * Returns a new object of class '<em>Derived Object Probe</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Derived Object Probe</em>'.
     * @generated
     */
    DerivedObjectProbe createDerivedObjectProbe();

    /**
     * Returns a new object of class '<em>Derived Double Probe</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Derived Double Probe</em>'.
     * @generated
     */
    DerivedDoubleProbe createDerivedDoubleProbe();

    /**
     * Returns a new object of class '<em>Derived Integer Probe</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Derived Integer Probe</em>'.
     * @generated
     */
    DerivedIntegerProbe createDerivedIntegerProbe();

    /**
     * Returns a new object of class '<em>Derived Long Probe</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Derived Long Probe</em>'.
     * @generated
     */
    DerivedLongProbe createDerivedLongProbe();

    /**
     * Returns a new object of class '<em>Integer Probe</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Integer Probe</em>'.
     * @generated
     */
	IntegerProbe createIntegerProbe();

	/**
     * Returns a new object of class '<em>Long Probe</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Long Probe</em>'.
     * @generated
     */
	LongProbe createLongProbe();

	/**
     * Returns a new object of class '<em>Object Probe</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Object Probe</em>'.
     * @generated
     */
	ObjectProbe createObjectProbe();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	probespecPackage getprobespecPackage();

} //probespecFactory
