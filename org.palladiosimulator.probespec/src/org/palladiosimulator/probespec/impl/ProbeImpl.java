/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.probespec.impl;

import org.eclipse.emf.ecore.EClass;
import org.palladiosimulator.probespec.Probe;
import org.palladiosimulator.probespec.probespecPackage;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Probe</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class ProbeImpl extends IdentifierImpl implements Probe {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ProbeImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return probespecPackage.Literals.PROBE;
    }

} //ProbeImpl
