/**
 */
package edu.kit.ipd.sdq.probespec;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Derived Probe</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.probespec.DerivedProbe#getSourceProbes <em>Source Probes</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.probespec.probespecPackage#getDerivedProbe()
 * @model abstract="true"
 * @generated
 */
public interface DerivedProbe<Td> extends Probe<Td> {

    /**
     * Returns the value of the '<em><b>Source Probes</b></em>' reference list.
     * The list contents are of type {@link edu.kit.ipd.sdq.probespec.Probe}&lt;Td>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Probes</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Probes</em>' reference list.
     * @see edu.kit.ipd.sdq.probespec.probespecPackage#getDerivedProbe_SourceProbes()
     * @model required="true" ordered="false"
     * @generated
     */
    EList<Probe<Td>> getSourceProbes();

} // DerivedProbe
