/**
 */
package edu.kit.ipd.sdq.probespec.impl;

import edu.kit.ipd.sdq.probespec.ContextMatchStrategy;
import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.probespecPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Derived Probe</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.probespec.impl.DerivedProbeImpl#getSourceProbes <em>Source Probes</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.probespec.impl.DerivedProbeImpl#getMatchStrategy <em>Match Strategy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DerivedProbeImpl<Td> extends ProbeImpl<Td> implements DerivedProbe<Td> {
	/**
     * The cached value of the '{@link #getSourceProbes() <em>Source Probes</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceProbes()
     * @generated
     * @ordered
     */
    protected EList<Probe<Td>> sourceProbes;

    /**
     * The default value of the '{@link #getMatchStrategy() <em>Match Strategy</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMatchStrategy()
     * @generated
     * @ordered
     */
    protected static final ContextMatchStrategy MATCH_STRATEGY_EDEFAULT = ContextMatchStrategy.ALL_CONTEXTS;
    /**
     * The cached value of the '{@link #getMatchStrategy() <em>Match Strategy</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMatchStrategy()
     * @generated
     * @ordered
     */
    protected ContextMatchStrategy matchStrategy = MATCH_STRATEGY_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected DerivedProbeImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return probespecPackage.Literals.DERIVED_PROBE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Probe<Td>> getSourceProbes() {
        if (sourceProbes == null) {
            sourceProbes = new EObjectResolvingEList<Probe<Td>>(Probe.class, this, probespecPackage.DERIVED_PROBE__SOURCE_PROBES);
        }
        return sourceProbes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ContextMatchStrategy getMatchStrategy() {
        return matchStrategy;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMatchStrategy(ContextMatchStrategy newMatchStrategy) {
        ContextMatchStrategy oldMatchStrategy = matchStrategy;
        matchStrategy = newMatchStrategy == null ? MATCH_STRATEGY_EDEFAULT : newMatchStrategy;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, probespecPackage.DERIVED_PROBE__MATCH_STRATEGY, oldMatchStrategy, matchStrategy));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case probespecPackage.DERIVED_PROBE__SOURCE_PROBES:
                return getSourceProbes();
            case probespecPackage.DERIVED_PROBE__MATCH_STRATEGY:
                return getMatchStrategy();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case probespecPackage.DERIVED_PROBE__SOURCE_PROBES:
                getSourceProbes().clear();
                getSourceProbes().addAll((Collection<? extends Probe<Td>>)newValue);
                return;
            case probespecPackage.DERIVED_PROBE__MATCH_STRATEGY:
                setMatchStrategy((ContextMatchStrategy)newValue);
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
            case probespecPackage.DERIVED_PROBE__SOURCE_PROBES:
                getSourceProbes().clear();
                return;
            case probespecPackage.DERIVED_PROBE__MATCH_STRATEGY:
                setMatchStrategy(MATCH_STRATEGY_EDEFAULT);
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
            case probespecPackage.DERIVED_PROBE__SOURCE_PROBES:
                return sourceProbes != null && !sourceProbes.isEmpty();
            case probespecPackage.DERIVED_PROBE__MATCH_STRATEGY:
                return matchStrategy != MATCH_STRATEGY_EDEFAULT;
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
        result.append(" (matchStrategy: ");
        result.append(matchStrategy);
        result.append(')');
        return result.toString();
    }

} //DerivedProbeImpl
