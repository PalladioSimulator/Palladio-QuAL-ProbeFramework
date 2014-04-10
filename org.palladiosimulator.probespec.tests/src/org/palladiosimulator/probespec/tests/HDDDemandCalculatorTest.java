/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.probespec.tests;

import org.palladiosimulator.probespec.HDDDemandCalculator;
import org.palladiosimulator.probespec.probespecFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>HDD Demand Calculator</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class HDDDemandCalculatorTest extends UnaryCalculatorTest {

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static void main(String[] args) {
        TestRunner.run(HDDDemandCalculatorTest.class);
    }

	/**
     * Constructs a new HDD Demand Calculator test case with the given name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public HDDDemandCalculatorTest(String name) {
        super(name);
    }

	/**
     * Returns the fixture for this HDD Demand Calculator test case.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected HDDDemandCalculator getFixture() {
        return (HDDDemandCalculator)fixture;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
	@Override
	protected void setUp() throws Exception {
        setFixture(probespecFactory.eINSTANCE.createHDDDemandCalculator());
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see junit.framework.TestCase#tearDown()
     * @generated
     */
	@Override
	protected void tearDown() throws Exception {
        setFixture(null);
    }

} //HDDDemandCalculatorTest
