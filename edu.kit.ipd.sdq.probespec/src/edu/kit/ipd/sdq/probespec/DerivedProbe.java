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
 *   <li>{@link edu.kit.ipd.sdq.probespec.DerivedProbe#getMatchStrategy <em>Match Strategy</em>}</li>
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

    /**
     * Returns the value of the '<em><b>Match Strategy</b></em>' attribute.
     * The default value is <code>"SAME_OR_PARENT_CONTEXT"</code>.
     * The literals are from the enumeration {@link edu.kit.ipd.sdq.probespec.ContextMatchStrategy}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Match Strategy</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Match Strategy</em>' attribute.
     * @see edu.kit.ipd.sdq.probespec.ContextMatchStrategy
     * @see #setMatchStrategy(ContextMatchStrategy)
     * @see edu.kit.ipd.sdq.probespec.probespecPackage#getDerivedProbe_MatchStrategy()
     * @model default="SAME_OR_PARENT_CONTEXT" required="true" ordered="false"
     * @generated
     */
    ContextMatchStrategy getMatchStrategy();

    /**
     * Sets the value of the '{@link edu.kit.ipd.sdq.probespec.DerivedProbe#getMatchStrategy <em>Match Strategy</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Match Strategy</em>' attribute.
     * @see edu.kit.ipd.sdq.probespec.ContextMatchStrategy
     * @see #getMatchStrategy()
     * @generated
     */
    void setMatchStrategy(ContextMatchStrategy value);

} // DerivedProbe
