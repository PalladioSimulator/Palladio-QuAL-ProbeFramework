/**
 */
package edu.kit.ipd.sdq.probespec.impl;

import de.uka.ipd.sdq.identifier.Identifier;
import de.uka.ipd.sdq.identifier.IdentifierPackage;

import de.uka.ipd.sdq.identifier.util.IdentifierValidator;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.MeasurableEntity;
import edu.kit.ipd.sdq.probespec.ProbeRepository;
import edu.kit.ipd.sdq.probespec.probespecPackage;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Probe Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.probespec.impl.ProbeRepositoryImpl#getId <em>Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.probespec.impl.ProbeRepositoryImpl#getDerivedProbes <em>Derived Probes</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.probespec.impl.ProbeRepositoryImpl#getImportedRepositories <em>Imported Repositories</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.probespec.impl.ProbeRepositoryImpl#getEntities <em>Entities</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProbeRepositoryImpl extends ResultConfigurationImpl implements ProbeRepository {
	/**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
	protected static final String ID_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
	protected String id = ID_EDEFAULT;

	/**
     * The cached value of the '{@link #getDerivedProbes() <em>Derived Probes</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDerivedProbes()
     * @generated
     * @ordered
     */
	protected EList<DerivedProbe<Object>> derivedProbes;

	/**
     * The cached value of the '{@link #getImportedRepositories() <em>Imported Repositories</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getImportedRepositories()
     * @generated
     * @ordered
     */
	protected EList<ProbeRepository> importedRepositories;

	/**
     * The cached value of the '{@link #getEntities() <em>Entities</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getEntities()
     * @generated
     * @ordered
     */
	protected EList<MeasurableEntity> entities;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ProbeRepositoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return probespecPackage.Literals.PROBE_REPOSITORY;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getId() {
        return id;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, probespecPackage.PROBE_REPOSITORY__ID, oldId, id));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<DerivedProbe<Object>> getDerivedProbes() {
        if (derivedProbes == null) {
            derivedProbes = new EObjectContainmentEList<DerivedProbe<Object>>(DerivedProbe.class, this, probespecPackage.PROBE_REPOSITORY__DERIVED_PROBES);
        }
        return derivedProbes;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<ProbeRepository> getImportedRepositories() {
        if (importedRepositories == null) {
            importedRepositories = new EObjectResolvingEList<ProbeRepository>(ProbeRepository.class, this, probespecPackage.PROBE_REPOSITORY__IMPORTED_REPOSITORIES);
        }
        return importedRepositories;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<MeasurableEntity> getEntities() {
        if (entities == null) {
            entities = new EObjectContainmentEList<MeasurableEntity>(MeasurableEntity.class, this, probespecPackage.PROBE_REPOSITORY__ENTITIES);
        }
        return entities;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean idHasToBeUnique(DiagnosticChain diagnostics, Map<Object, Object> context) {
        // TODO: implement this method
        // -> specify the condition that violates the invariant
        // -> verify the details of the diagnostic, including severity and message
        // Ensure that you remove @generated or mark it @generated NOT
        if (false) {
            if (diagnostics != null) {
                diagnostics.add
                    (new BasicDiagnostic
                        (Diagnostic.ERROR,
                         IdentifierValidator.DIAGNOSTIC_SOURCE,
                         IdentifierValidator.IDENTIFIER__ID_HAS_TO_BE_UNIQUE,
                         EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "idHasToBeUnique", EObjectValidator.getObjectLabel(this, context) }),
                         new Object [] { this }));
            }
            return false;
        }
        return true;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case probespecPackage.PROBE_REPOSITORY__DERIVED_PROBES:
                return ((InternalEList<?>)getDerivedProbes()).basicRemove(otherEnd, msgs);
            case probespecPackage.PROBE_REPOSITORY__ENTITIES:
                return ((InternalEList<?>)getEntities()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case probespecPackage.PROBE_REPOSITORY__ID:
                return getId();
            case probespecPackage.PROBE_REPOSITORY__DERIVED_PROBES:
                return getDerivedProbes();
            case probespecPackage.PROBE_REPOSITORY__IMPORTED_REPOSITORIES:
                return getImportedRepositories();
            case probespecPackage.PROBE_REPOSITORY__ENTITIES:
                return getEntities();
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
            case probespecPackage.PROBE_REPOSITORY__ID:
                setId((String)newValue);
                return;
            case probespecPackage.PROBE_REPOSITORY__DERIVED_PROBES:
                getDerivedProbes().clear();
                getDerivedProbes().addAll((Collection<? extends DerivedProbe<Object>>)newValue);
                return;
            case probespecPackage.PROBE_REPOSITORY__IMPORTED_REPOSITORIES:
                getImportedRepositories().clear();
                getImportedRepositories().addAll((Collection<? extends ProbeRepository>)newValue);
                return;
            case probespecPackage.PROBE_REPOSITORY__ENTITIES:
                getEntities().clear();
                getEntities().addAll((Collection<? extends MeasurableEntity>)newValue);
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
            case probespecPackage.PROBE_REPOSITORY__ID:
                setId(ID_EDEFAULT);
                return;
            case probespecPackage.PROBE_REPOSITORY__DERIVED_PROBES:
                getDerivedProbes().clear();
                return;
            case probespecPackage.PROBE_REPOSITORY__IMPORTED_REPOSITORIES:
                getImportedRepositories().clear();
                return;
            case probespecPackage.PROBE_REPOSITORY__ENTITIES:
                getEntities().clear();
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
            case probespecPackage.PROBE_REPOSITORY__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case probespecPackage.PROBE_REPOSITORY__DERIVED_PROBES:
                return derivedProbes != null && !derivedProbes.isEmpty();
            case probespecPackage.PROBE_REPOSITORY__IMPORTED_REPOSITORIES:
                return importedRepositories != null && !importedRepositories.isEmpty();
            case probespecPackage.PROBE_REPOSITORY__ENTITIES:
                return entities != null && !entities.isEmpty();
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == Identifier.class) {
            switch (derivedFeatureID) {
                case probespecPackage.PROBE_REPOSITORY__ID: return IdentifierPackage.IDENTIFIER__ID;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == Identifier.class) {
            switch (baseFeatureID) {
                case IdentifierPackage.IDENTIFIER__ID: return probespecPackage.PROBE_REPOSITORY__ID;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (id: ");
        result.append(id);
        result.append(')');
        return result.toString();
    }

} //ProbeRepositoryImpl
