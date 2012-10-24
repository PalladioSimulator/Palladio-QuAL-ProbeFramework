/**
 */
package edu.kit.ipd.sdq.probespec;

import de.uka.ipd.sdq.identifier.Identifier;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mount Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.probespec.MountPoint#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.probespec.probespecPackage#getMountPoint()
 * @model abstract="true"
 * @generated
 */
public interface MountPoint extends ResultConfiguration, Identifier {
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
     * @see edu.kit.ipd.sdq.probespec.probespecPackage#getMountPoint_Name()
     * @model required="true" ordered="false"
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link edu.kit.ipd.sdq.probespec.MountPoint#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation" ordered="false"
     * @generated
     */
	EList<ObjectProbe> getProbes();

} // MountPoint
