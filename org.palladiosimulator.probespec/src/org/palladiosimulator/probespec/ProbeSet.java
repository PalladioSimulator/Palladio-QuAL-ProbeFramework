/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.probespec;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Probe Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.palladiosimulator.probespec.ProbeSet#getProbes <em>Probes</em>}</li>
 *   <li>{@link org.palladiosimulator.probespec.ProbeSet#getPosition <em>Position</em>}</li>
 *   <li>{@link org.palladiosimulator.probespec.ProbeSet#getAnnotatedElement <em>Annotated Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.probespec.probespecPackage#getProbeSet()
 * @model
 * @generated
 */
public interface ProbeSet extends Identifier {
	/**
     * Returns the value of the '<em><b>Probes</b></em>' containment reference list.
     * The list contents are of type {@link org.palladiosimulator.probespec.Probe}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Probes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Probes</em>' containment reference list.
     * @see org.palladiosimulator.probespec.probespecPackage#getProbeSet_Probes()
     * @model containment="true" required="true" ordered="false"
     * @generated
     */
	EList<Probe> getProbes();

	/**
     * Returns the value of the '<em><b>Position</b></em>' attribute.
     * The literals are from the enumeration {@link org.palladiosimulator.probespec.ProbeSetPosition}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Position</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Position</em>' attribute.
     * @see org.palladiosimulator.probespec.ProbeSetPosition
     * @see #setPosition(ProbeSetPosition)
     * @see org.palladiosimulator.probespec.probespecPackage#getProbeSet_Position()
     * @model required="true" ordered="false"
     * @generated
     */
	ProbeSetPosition getPosition();

	/**
     * Sets the value of the '{@link org.palladiosimulator.probespec.ProbeSet#getPosition <em>Position</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Position</em>' attribute.
     * @see org.palladiosimulator.probespec.ProbeSetPosition
     * @see #getPosition()
     * @generated
     */
	void setPosition(ProbeSetPosition value);

	/**
     * Returns the value of the '<em><b>Annotated Element</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotated Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Annotated Element</em>' reference.
     * @see #setAnnotatedElement(EObject)
     * @see org.palladiosimulator.probespec.probespecPackage#getProbeSet_AnnotatedElement()
     * @model required="true" ordered="false"
     * @generated
     */
	EObject getAnnotatedElement();

	/**
     * Sets the value of the '{@link org.palladiosimulator.probespec.ProbeSet#getAnnotatedElement <em>Annotated Element</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Annotated Element</em>' reference.
     * @see #getAnnotatedElement()
     * @generated
     */
	void setAnnotatedElement(EObject value);

} // ProbeSet
