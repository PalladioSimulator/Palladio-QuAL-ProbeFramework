/**
 */
package edu.kit.ipd.sdq.probespec;

import de.uka.ipd.sdq.identifier.Identifier;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Probe Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.probespec.ProbeRepository#getDerivedProbes <em>Derived Probes</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.probespec.ProbeRepository#getImportedRepositories <em>Imported Repositories</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.probespec.ProbeRepository#getEntities <em>Entities</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.probespec.probespecPackage#getProbeRepository()
 * @model
 * @generated
 */
public interface ProbeRepository extends ResultConfiguration, Identifier {
	/**
     * Returns the value of the '<em><b>Derived Probes</b></em>' containment reference list.
     * The list contents are of type {@link edu.kit.ipd.sdq.probespec.DerivedProbe}&lt;java.lang.Object>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived Probes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Derived Probes</em>' containment reference list.
     * @see edu.kit.ipd.sdq.probespec.probespecPackage#getProbeRepository_DerivedProbes()
     * @model type="edu.kit.ipd.sdq.probespec.DerivedProbe<org.eclipse.emf.ecore.EJavaObject>" containment="true" ordered="false"
     * @generated
     */
	EList<DerivedProbe<Object>> getDerivedProbes();

	/**
     * Returns the value of the '<em><b>Imported Repositories</b></em>' reference list.
     * The list contents are of type {@link edu.kit.ipd.sdq.probespec.ProbeRepository}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imported Repositories</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Imported Repositories</em>' reference list.
     * @see edu.kit.ipd.sdq.probespec.probespecPackage#getProbeRepository_ImportedRepositories()
     * @model ordered="false"
     * @generated
     */
	EList<ProbeRepository> getImportedRepositories();

	/**
     * Returns the value of the '<em><b>Entities</b></em>' containment reference list.
     * The list contents are of type {@link edu.kit.ipd.sdq.probespec.MeasurableEntity}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Entities</em>' containment reference list.
     * @see edu.kit.ipd.sdq.probespec.probespecPackage#getProbeRepository_Entities()
     * @model containment="true" ordered="false"
     * @generated
     */
	EList<MeasurableEntity> getEntities();

} // ProbeRepository
