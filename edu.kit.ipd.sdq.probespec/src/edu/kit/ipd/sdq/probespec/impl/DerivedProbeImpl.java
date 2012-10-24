/**
 */
package edu.kit.ipd.sdq.probespec.impl;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.probespecPackage;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Derived Probe</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.probespec.impl.DerivedProbeImpl#getSourceProbes <em>Source Probes</em>}</li>
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
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case probespecPackage.DERIVED_PROBE__SOURCE_PROBES:
                return getSourceProbes();
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
        }
        return super.eIsSet(featureID);
    }

} //DerivedProbeImpl
