/**
 */
package edu.kit.ipd.sdq.probespec.impl;

import edu.kit.ipd.sdq.probespec.ResultAggregationLevel;
import edu.kit.ipd.sdq.probespec.ResultConfiguration;
import edu.kit.ipd.sdq.probespec.probespecPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.probespec.impl.ResultConfigurationImpl#getAggregationLevel <em>Aggregation Level</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.probespec.impl.ResultConfigurationImpl#isEnabled <em>Enabled</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ResultConfigurationImpl extends EObjectImpl implements ResultConfiguration {
	/**
     * The default value of the '{@link #getAggregationLevel() <em>Aggregation Level</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAggregationLevel()
     * @generated
     * @ordered
     */
	protected static final ResultAggregationLevel AGGREGATION_LEVEL_EDEFAULT = ResultAggregationLevel.INHERIT;

	/**
     * The cached value of the '{@link #getAggregationLevel() <em>Aggregation Level</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAggregationLevel()
     * @generated
     * @ordered
     */
	protected ResultAggregationLevel aggregationLevel = AGGREGATION_LEVEL_EDEFAULT;

	/**
     * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isEnabled()
     * @generated
     * @ordered
     */
	protected static final boolean ENABLED_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isEnabled()
     * @generated
     * @ordered
     */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ResultConfigurationImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return probespecPackage.Literals.RESULT_CONFIGURATION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ResultAggregationLevel getAggregationLevel() {
        return aggregationLevel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setAggregationLevel(ResultAggregationLevel newAggregationLevel) {
        ResultAggregationLevel oldAggregationLevel = aggregationLevel;
        aggregationLevel = newAggregationLevel == null ? AGGREGATION_LEVEL_EDEFAULT : newAggregationLevel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, probespecPackage.RESULT_CONFIGURATION__AGGREGATION_LEVEL, oldAggregationLevel, aggregationLevel));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isEnabled() {
        return enabled;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setEnabled(boolean newEnabled) {
        boolean oldEnabled = enabled;
        enabled = newEnabled;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, probespecPackage.RESULT_CONFIGURATION__ENABLED, oldEnabled, enabled));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case probespecPackage.RESULT_CONFIGURATION__AGGREGATION_LEVEL:
                return getAggregationLevel();
            case probespecPackage.RESULT_CONFIGURATION__ENABLED:
                return isEnabled();
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
            case probespecPackage.RESULT_CONFIGURATION__AGGREGATION_LEVEL:
                setAggregationLevel((ResultAggregationLevel)newValue);
                return;
            case probespecPackage.RESULT_CONFIGURATION__ENABLED:
                setEnabled((Boolean)newValue);
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
            case probespecPackage.RESULT_CONFIGURATION__AGGREGATION_LEVEL:
                setAggregationLevel(AGGREGATION_LEVEL_EDEFAULT);
                return;
            case probespecPackage.RESULT_CONFIGURATION__ENABLED:
                setEnabled(ENABLED_EDEFAULT);
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
            case probespecPackage.RESULT_CONFIGURATION__AGGREGATION_LEVEL:
                return aggregationLevel != AGGREGATION_LEVEL_EDEFAULT;
            case probespecPackage.RESULT_CONFIGURATION__ENABLED:
                return enabled != ENABLED_EDEFAULT;
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
        result.append(" (aggregationLevel: ");
        result.append(aggregationLevel);
        result.append(", enabled: ");
        result.append(enabled);
        result.append(')');
        return result.toString();
    }

} //ResultConfigurationImpl
