/**
 */
package edu.kit.ipd.sdq.probespec.util;

import de.uka.ipd.sdq.identifier.Identifier;

import edu.kit.ipd.sdq.probespec.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see edu.kit.ipd.sdq.probespec.probespecPackage
 * @generated
 */
public class probespecSwitch<T1> extends Switch<T1> {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static probespecPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public probespecSwitch() {
        if (modelPackage == null) {
            modelPackage = probespecPackage.eINSTANCE;
        }
    }

	/**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	@Override
	protected T1 doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case probespecPackage.PROBE: {
                Probe<?> probe = (Probe<?>)theEObject;
                T1 result = caseProbe(probe);
                if (result == null) result = caseResultConfiguration(probe);
                if (result == null) result = caseIdentifier(probe);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.RESULT_CONFIGURATION: {
                ResultConfiguration resultConfiguration = (ResultConfiguration)theEObject;
                T1 result = caseResultConfiguration(resultConfiguration);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.MOUNT_POINT: {
                MountPoint mountPoint = (MountPoint)theEObject;
                T1 result = caseMountPoint(mountPoint);
                if (result == null) result = caseResultConfiguration(mountPoint);
                if (result == null) result = caseIdentifier(mountPoint);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.OBJECT_PROBE: {
                ObjectProbe objectProbe = (ObjectProbe)theEObject;
                T1 result = caseObjectProbe(objectProbe);
                if (result == null) result = caseProbe(objectProbe);
                if (result == null) result = caseResultConfiguration(objectProbe);
                if (result == null) result = caseIdentifier(objectProbe);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.PROBE_REPOSITORY: {
                ProbeRepository probeRepository = (ProbeRepository)theEObject;
                T1 result = caseProbeRepository(probeRepository);
                if (result == null) result = caseResultConfiguration(probeRepository);
                if (result == null) result = caseIdentifier(probeRepository);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.DERIVED_PROBE: {
                DerivedProbe<?> derivedProbe = (DerivedProbe<?>)theEObject;
                T1 result = caseDerivedProbe(derivedProbe);
                if (result == null) result = caseProbe(derivedProbe);
                if (result == null) result = caseResultConfiguration(derivedProbe);
                if (result == null) result = caseIdentifier(derivedProbe);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.MEASURABLE_ENTITY: {
                MeasurableEntity measurableEntity = (MeasurableEntity)theEObject;
                T1 result = caseMeasurableEntity(measurableEntity);
                if (result == null) result = caseResultConfiguration(measurableEntity);
                if (result == null) result = caseIdentifier(measurableEntity);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.INTEGER_PROBE: {
                IntegerProbe integerProbe = (IntegerProbe)theEObject;
                T1 result = caseIntegerProbe(integerProbe);
                if (result == null) result = caseProbe(integerProbe);
                if (result == null) result = caseResultConfiguration(integerProbe);
                if (result == null) result = caseIdentifier(integerProbe);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.LONG_PROBE: {
                LongProbe longProbe = (LongProbe)theEObject;
                T1 result = caseLongProbe(longProbe);
                if (result == null) result = caseProbe(longProbe);
                if (result == null) result = caseResultConfiguration(longProbe);
                if (result == null) result = caseIdentifier(longProbe);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.DOUBLE_PROBE: {
                DoubleProbe doubleProbe = (DoubleProbe)theEObject;
                T1 result = caseDoubleProbe(doubleProbe);
                if (result == null) result = caseProbe(doubleProbe);
                if (result == null) result = caseResultConfiguration(doubleProbe);
                if (result == null) result = caseIdentifier(doubleProbe);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.DERIVED_OBJECT_PROBE: {
                DerivedObjectProbe derivedObjectProbe = (DerivedObjectProbe)theEObject;
                T1 result = caseDerivedObjectProbe(derivedObjectProbe);
                if (result == null) result = caseDerivedProbe(derivedObjectProbe);
                if (result == null) result = caseProbe(derivedObjectProbe);
                if (result == null) result = caseResultConfiguration(derivedObjectProbe);
                if (result == null) result = caseIdentifier(derivedObjectProbe);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.DERIVED_DOUBLE_PROBE: {
                DerivedDoubleProbe derivedDoubleProbe = (DerivedDoubleProbe)theEObject;
                T1 result = caseDerivedDoubleProbe(derivedDoubleProbe);
                if (result == null) result = caseDerivedProbe(derivedDoubleProbe);
                if (result == null) result = caseProbe(derivedDoubleProbe);
                if (result == null) result = caseResultConfiguration(derivedDoubleProbe);
                if (result == null) result = caseIdentifier(derivedDoubleProbe);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.DERIVED_INTEGER_PROBE: {
                DerivedIntegerProbe derivedIntegerProbe = (DerivedIntegerProbe)theEObject;
                T1 result = caseDerivedIntegerProbe(derivedIntegerProbe);
                if (result == null) result = caseDerivedProbe(derivedIntegerProbe);
                if (result == null) result = caseProbe(derivedIntegerProbe);
                if (result == null) result = caseResultConfiguration(derivedIntegerProbe);
                if (result == null) result = caseIdentifier(derivedIntegerProbe);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case probespecPackage.DERIVED_LONG_PROBE: {
                DerivedLongProbe derivedLongProbe = (DerivedLongProbe)theEObject;
                T1 result = caseDerivedLongProbe(derivedLongProbe);
                if (result == null) result = caseDerivedProbe(derivedLongProbe);
                if (result == null) result = caseProbe(derivedLongProbe);
                if (result == null) result = caseResultConfiguration(derivedLongProbe);
                if (result == null) result = caseIdentifier(derivedLongProbe);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Probe</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Probe</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public <T> T1 caseProbe(Probe<T> object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Result Configuration</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Result Configuration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T1 caseResultConfiguration(ResultConfiguration object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Mount Point</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Mount Point</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T1 caseMountPoint(MountPoint object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Probe Repository</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Probe Repository</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T1 caseProbeRepository(ProbeRepository object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Derived Probe</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Derived Probe</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public <Td> T1 caseDerivedProbe(DerivedProbe<Td> object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Measurable Entity</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Measurable Entity</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T1 caseMeasurableEntity(MeasurableEntity object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Double Probe</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Double Probe</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T1 caseDoubleProbe(DoubleProbe object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Derived Object Probe</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Derived Object Probe</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseDerivedObjectProbe(DerivedObjectProbe object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Derived Double Probe</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Derived Double Probe</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseDerivedDoubleProbe(DerivedDoubleProbe object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Derived Integer Probe</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Derived Integer Probe</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseDerivedIntegerProbe(DerivedIntegerProbe object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Derived Long Probe</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Derived Long Probe</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseDerivedLongProbe(DerivedLongProbe object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Integer Probe</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Integer Probe</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T1 caseIntegerProbe(IntegerProbe object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Long Probe</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Long Probe</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T1 caseLongProbe(LongProbe object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Object Probe</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Object Probe</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T1 caseObjectProbe(ObjectProbe object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Identifier</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Identifier</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T1 caseIdentifier(Identifier object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
	@Override
	public T1 defaultCase(EObject object) {
        return null;
    }

} //probespecSwitch
