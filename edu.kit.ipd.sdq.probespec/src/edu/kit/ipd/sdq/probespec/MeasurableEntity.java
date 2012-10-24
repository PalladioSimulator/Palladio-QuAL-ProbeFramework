/**
 */
package edu.kit.ipd.sdq.probespec;

import de.uka.ipd.sdq.identifier.Identifier;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurable Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see edu.kit.ipd.sdq.probespec.probespecPackage#getMeasurableEntity()
 * @model abstract="true"
 * @generated
 */
public interface MeasurableEntity extends ResultConfiguration, Identifier {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation" ordered="false"
     * @generated
     */
	EList<MountPoint> getMountPoints();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation" required="true" ordered="false"
     * @generated
     */
	EObject getDecoratedEntity();

} // MeasurableEntity
