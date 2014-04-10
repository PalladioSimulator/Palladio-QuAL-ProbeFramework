/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.probespec.tests;

import org.palladiosimulator.probespec.HDDStateCalculator;
import org.palladiosimulator.probespec.probespecFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>HDD State Calculator</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class HDDStateCalculatorTest extends UnaryCalculatorTest {

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static void main(String[] args) {
        TestRunner.run(HDDStateCalculatorTest.class);
    }

	/**
     * Constructs a new HDD State Calculator test case with the given name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public HDDStateCalculatorTest(String name) {
        super(name);
    }

	/**
     * Returns the fixture for this HDD State Calculator test case.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected HDDStateCalculator getFixture() {
        return (HDDStateCalculator)fixture;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
	@Override
	protected void setUp() throws Exception {
        setFixture(probespecFactory.eINSTANCE.createHDDStateCalculator());
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

} //HDDStateCalculatorTest
