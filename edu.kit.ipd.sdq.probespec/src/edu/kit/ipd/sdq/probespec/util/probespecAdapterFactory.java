/**
 */
package edu.kit.ipd.sdq.probespec.util;

import de.uka.ipd.sdq.identifier.Identifier;

import edu.kit.ipd.sdq.probespec.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see edu.kit.ipd.sdq.probespec.probespecPackage
 * @generated
 */
public class probespecAdapterFactory extends AdapterFactoryImpl {
	/**
     * The cached model package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static probespecPackage modelPackage;

	/**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public probespecAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = probespecPackage.eINSTANCE;
        }
    }

	/**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
	@Override
	public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

	/**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected probespecSwitch<Adapter> modelSwitch =
		new probespecSwitch<Adapter>() {
            @Override
            public <T> Adapter caseProbe(Probe<T> object) {
                return createProbeAdapter();
            }
            @Override
            public Adapter caseResultConfiguration(ResultConfiguration object) {
                return createResultConfigurationAdapter();
            }
            @Override
            public Adapter caseMountPoint(MountPoint object) {
                return createMountPointAdapter();
            }
            @Override
            public Adapter caseObjectProbe(ObjectProbe object) {
                return createObjectProbeAdapter();
            }
            @Override
            public Adapter caseProbeRepository(ProbeRepository object) {
                return createProbeRepositoryAdapter();
            }
            @Override
            public <Td> Adapter caseDerivedProbe(DerivedProbe<Td> object) {
                return createDerivedProbeAdapter();
            }
            @Override
            public Adapter caseMeasurableEntity(MeasurableEntity object) {
                return createMeasurableEntityAdapter();
            }
            @Override
            public Adapter caseIntegerProbe(IntegerProbe object) {
                return createIntegerProbeAdapter();
            }
            @Override
            public Adapter caseLongProbe(LongProbe object) {
                return createLongProbeAdapter();
            }
            @Override
            public Adapter caseDoubleProbe(DoubleProbe object) {
                return createDoubleProbeAdapter();
            }
            @Override
            public Adapter caseDerivedObjectProbe(DerivedObjectProbe object) {
                return createDerivedObjectProbeAdapter();
            }
            @Override
            public Adapter caseDerivedDoubleProbe(DerivedDoubleProbe object) {
                return createDerivedDoubleProbeAdapter();
            }
            @Override
            public Adapter caseDerivedIntegerProbe(DerivedIntegerProbe object) {
                return createDerivedIntegerProbeAdapter();
            }
            @Override
            public Adapter caseDerivedLongProbe(DerivedLongProbe object) {
                return createDerivedLongProbeAdapter();
            }
            @Override
            public Adapter caseIdentifier(Identifier object) {
                return createIdentifierAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

	/**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
	@Override
	public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


	/**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.Probe <em>Probe</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.Probe
     * @generated
     */
	public Adapter createProbeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.ResultConfiguration <em>Result Configuration</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.ResultConfiguration
     * @generated
     */
	public Adapter createResultConfigurationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.MountPoint <em>Mount Point</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.MountPoint
     * @generated
     */
	public Adapter createMountPointAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.ProbeRepository <em>Probe Repository</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.ProbeRepository
     * @generated
     */
	public Adapter createProbeRepositoryAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.DerivedProbe <em>Derived Probe</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.DerivedProbe
     * @generated
     */
	public Adapter createDerivedProbeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.MeasurableEntity <em>Measurable Entity</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.MeasurableEntity
     * @generated
     */
	public Adapter createMeasurableEntityAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.DoubleProbe <em>Double Probe</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.DoubleProbe
     * @generated
     */
	public Adapter createDoubleProbeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.DerivedObjectProbe <em>Derived Object Probe</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.DerivedObjectProbe
     * @generated
     */
    public Adapter createDerivedObjectProbeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.DerivedDoubleProbe <em>Derived Double Probe</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.DerivedDoubleProbe
     * @generated
     */
    public Adapter createDerivedDoubleProbeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.DerivedIntegerProbe <em>Derived Integer Probe</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.DerivedIntegerProbe
     * @generated
     */
    public Adapter createDerivedIntegerProbeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.DerivedLongProbe <em>Derived Long Probe</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.DerivedLongProbe
     * @generated
     */
    public Adapter createDerivedLongProbeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.IntegerProbe <em>Integer Probe</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.IntegerProbe
     * @generated
     */
	public Adapter createIntegerProbeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.LongProbe <em>Long Probe</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.LongProbe
     * @generated
     */
	public Adapter createLongProbeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link edu.kit.ipd.sdq.probespec.ObjectProbe <em>Object Probe</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see edu.kit.ipd.sdq.probespec.ObjectProbe
     * @generated
     */
	public Adapter createObjectProbeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link de.uka.ipd.sdq.identifier.Identifier <em>Identifier</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.uka.ipd.sdq.identifier.Identifier
     * @generated
     */
	public Adapter createIdentifierAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
	public Adapter createEObjectAdapter() {
        return null;
    }

} //probespecAdapterFactory
