/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.probespec;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binary Calculator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.palladiosimulator.probespec.BinaryCalculator#getProbeSet <em>Probe Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.probespec.probespecPackage#getBinaryCalculator()
 * @model abstract="true"
 * @generated
 */
public interface BinaryCalculator extends Calculator {
	/**
     * Returns the value of the '<em><b>Probe Set</b></em>' containment reference list.
     * The list contents are of type {@link org.palladiosimulator.probespec.ProbeSet}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Probe Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Probe Set</em>' containment reference list.
     * @see org.palladiosimulator.probespec.probespecPackage#getBinaryCalculator_ProbeSet()
     * @model containment="true" lower="2" upper="2" ordered="false"
     * @generated
     */
	EList<ProbeSet> getProbeSet();

} // BinaryCalculator
