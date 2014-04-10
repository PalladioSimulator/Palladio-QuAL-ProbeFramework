/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.probespec;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Probe Spec Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.palladiosimulator.probespec.ProbeSpecRepository#getCalculator <em>Calculator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.probespec.probespecPackage#getProbeSpecRepository()
 * @model
 * @generated
 */
public interface ProbeSpecRepository extends EObject {
	/**
     * Returns the value of the '<em><b>Calculator</b></em>' containment reference list.
     * The list contents are of type {@link org.palladiosimulator.probespec.Calculator}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Calculator</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Calculator</em>' containment reference list.
     * @see org.palladiosimulator.probespec.probespecPackage#getProbeSpecRepository_Calculator()
     * @model containment="true" ordered="false"
     * @generated
     */
	EList<Calculator> getCalculator();

} // ProbeSpecRepository
