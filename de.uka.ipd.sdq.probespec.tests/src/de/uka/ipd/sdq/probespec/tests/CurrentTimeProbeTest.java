/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.uka.ipd.sdq.probespec.tests;

import de.uka.ipd.sdq.probespec.CurrentTimeProbe;
import de.uka.ipd.sdq.probespec.probespecFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Current Time Probe</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CurrentTimeProbeTest extends ProbeTest {

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static void main(String[] args) {
        TestRunner.run(CurrentTimeProbeTest.class);
    }

	/**
     * Constructs a new Current Time Probe test case with the given name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CurrentTimeProbeTest(String name) {
        super(name);
    }

	/**
     * Returns the fixture for this Current Time Probe test case.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected CurrentTimeProbe getFixture() {
        return (CurrentTimeProbe)fixture;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
	@Override
	protected void setUp() throws Exception {
        setFixture(probespecFactory.eINSTANCE.createCurrentTimeProbe());
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

} //CurrentTimeProbeTest
