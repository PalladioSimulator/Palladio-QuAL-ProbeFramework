/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.uka.ipd.sdq.probespec.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;
import de.uka.ipd.sdq.probespec.Calculator;
import de.uka.ipd.sdq.probespec.probespecPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Calculator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.uka.ipd.sdq.probespec.impl.CalculatorImpl#isActive <em>Active</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CalculatorImpl extends IdentifierImpl implements Calculator {
	/**
     * The default value of the '{@link #isActive() <em>Active</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isActive()
     * @generated
     * @ordered
     */
	protected static final boolean ACTIVE_EDEFAULT = true;

	/**
     * The cached value of the '{@link #isActive() <em>Active</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isActive()
     * @generated
     * @ordered
     */
	protected boolean active = ACTIVE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected CalculatorImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return probespecPackage.Literals.CALCULATOR;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isActive() {
        return active;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setActive(boolean newActive) {
        boolean oldActive = active;
        active = newActive;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, probespecPackage.CALCULATOR__ACTIVE, oldActive, active));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case probespecPackage.CALCULATOR__ACTIVE:
                return isActive();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case probespecPackage.CALCULATOR__ACTIVE:
                setActive((Boolean)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eUnset(int featureID) {
        switch (featureID) {
            case probespecPackage.CALCULATOR__ACTIVE:
                setActive(ACTIVE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case probespecPackage.CALCULATOR__ACTIVE:
                return active != ACTIVE_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (active: ");
        result.append(active);
        result.append(')');
        return result.toString();
    }

} //CalculatorImpl
