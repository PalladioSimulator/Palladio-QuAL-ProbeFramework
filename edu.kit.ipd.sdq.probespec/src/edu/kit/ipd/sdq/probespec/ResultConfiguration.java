/**
 */
package edu.kit.ipd.sdq.probespec;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Result Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.probespec.ResultConfiguration#getAggregationLevel <em>Aggregation Level</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.probespec.ResultConfiguration#isEnabled <em>Enabled</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.probespec.probespecPackage#getResultConfiguration()
 * @model abstract="true"
 * @generated
 */
public interface ResultConfiguration extends EObject {
	/**
     * Returns the value of the '<em><b>Aggregation Level</b></em>' attribute.
     * The literals are from the enumeration {@link edu.kit.ipd.sdq.probespec.ResultAggregationLevel}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aggregation Level</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Aggregation Level</em>' attribute.
     * @see edu.kit.ipd.sdq.probespec.ResultAggregationLevel
     * @see #setAggregationLevel(ResultAggregationLevel)
     * @see edu.kit.ipd.sdq.probespec.probespecPackage#getResultConfiguration_AggregationLevel()
     * @model required="true" ordered="false"
     * @generated
     */
	ResultAggregationLevel getAggregationLevel();

	/**
     * Sets the value of the '{@link edu.kit.ipd.sdq.probespec.ResultConfiguration#getAggregationLevel <em>Aggregation Level</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Aggregation Level</em>' attribute.
     * @see edu.kit.ipd.sdq.probespec.ResultAggregationLevel
     * @see #getAggregationLevel()
     * @generated
     */
	void setAggregationLevel(ResultAggregationLevel value);

	/**
     * Returns the value of the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Enabled</em>' attribute.
     * @see #setEnabled(boolean)
     * @see edu.kit.ipd.sdq.probespec.probespecPackage#getResultConfiguration_Enabled()
     * @model required="true" ordered="false"
     * @generated
     */
	boolean isEnabled();

	/**
     * Sets the value of the '{@link edu.kit.ipd.sdq.probespec.ResultConfiguration#isEnabled <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Enabled</em>' attribute.
     * @see #isEnabled()
     * @generated
     */
	void setEnabled(boolean value);

} // ResultConfiguration
