/**
 */
package edu.kit.ipd.sdq.probespec;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Probe</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.probespec.Probe#getName <em>Name</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.probespec.Probe#isTransient <em>Transient</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.probespec.probespecPackage#getProbe()
 * @model abstract="true"
 * @generated
 */
public interface Probe<T> extends ResultConfiguration, Identifier {
	/**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see edu.kit.ipd.sdq.probespec.probespecPackage#getProbe_Name()
     * @model required="true" ordered="false"
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link edu.kit.ipd.sdq.probespec.Probe#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

    /**
     * Returns the value of the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transient</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transient</em>' attribute.
     * @see #setTransient(boolean)
     * @see edu.kit.ipd.sdq.probespec.probespecPackage#getProbe_Transient()
     * @model required="true" ordered="false"
     * @generated
     */
    boolean isTransient();

    /**
     * Sets the value of the '{@link edu.kit.ipd.sdq.probespec.Probe#isTransient <em>Transient</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Transient</em>' attribute.
     * @see #isTransient()
     * @generated
     */
    void setTransient(boolean value);

} // Probe
