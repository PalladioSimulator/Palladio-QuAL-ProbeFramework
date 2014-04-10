/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.probespec.tests;

import org.palladiosimulator.probespec.PassiveResourceStateProbe;
import org.palladiosimulator.probespec.probespecFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Passive Resource State Probe</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PassiveResourceStateProbeTest extends ProbeTest {

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static void main(String[] args) {
        TestRunner.run(PassiveResourceStateProbeTest.class);
    }

	/**
     * Constructs a new Passive Resource State Probe test case with the given name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PassiveResourceStateProbeTest(String name) {
        super(name);
    }

	/**
     * Returns the fixture for this Passive Resource State Probe test case.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected PassiveResourceStateProbe getFixture() {
        return (PassiveResourceStateProbe)fixture;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
	@Override
	protected void setUp() throws Exception {
        setFixture(probespecFactory.eINSTANCE.createPassiveResourceStateProbe());
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

} //PassiveResourceStateProbeTest
