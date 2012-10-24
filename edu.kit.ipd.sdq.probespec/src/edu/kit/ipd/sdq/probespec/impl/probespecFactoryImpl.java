/**
 */
package edu.kit.ipd.sdq.probespec.impl;

import edu.kit.ipd.sdq.probespec.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class probespecFactoryImpl extends EFactoryImpl implements probespecFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static probespecFactory init() {
        try {
            probespecFactory theprobespecFactory = (probespecFactory)EPackage.Registry.INSTANCE.getEFactory("http://sdq.ipd.kit.edu/ProbeSpecification/2.0"); 
            if (theprobespecFactory != null) {
                return theprobespecFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new probespecFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public probespecFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case probespecPackage.OBJECT_PROBE: return createObjectProbe();
            case probespecPackage.PROBE_REPOSITORY: return createProbeRepository();
            case probespecPackage.INTEGER_PROBE: return createIntegerProbe();
            case probespecPackage.LONG_PROBE: return createLongProbe();
            case probespecPackage.DOUBLE_PROBE: return createDoubleProbe();
            case probespecPackage.DERIVED_OBJECT_PROBE: return createDerivedObjectProbe();
            case probespecPackage.DERIVED_DOUBLE_PROBE: return createDerivedDoubleProbe();
            case probespecPackage.DERIVED_INTEGER_PROBE: return createDerivedIntegerProbe();
            case probespecPackage.DERIVED_LONG_PROBE: return createDerivedLongProbe();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case probespecPackage.RESULT_AGGREGATION_LEVEL:
                return createResultAggregationLevelFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case probespecPackage.RESULT_AGGREGATION_LEVEL:
                return convertResultAggregationLevelToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ProbeRepository createProbeRepository() {
        ProbeRepositoryImpl probeRepository = new ProbeRepositoryImpl();
        return probeRepository;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public DoubleProbe createDoubleProbe() {
        DoubleProbeImpl doubleProbe = new DoubleProbeImpl();
        return doubleProbe;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DerivedObjectProbe createDerivedObjectProbe() {
        DerivedObjectProbeImpl derivedObjectProbe = new DerivedObjectProbeImpl();
        return derivedObjectProbe;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DerivedDoubleProbe createDerivedDoubleProbe() {
        DerivedDoubleProbeImpl derivedDoubleProbe = new DerivedDoubleProbeImpl();
        return derivedDoubleProbe;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DerivedIntegerProbe createDerivedIntegerProbe() {
        DerivedIntegerProbeImpl derivedIntegerProbe = new DerivedIntegerProbeImpl();
        return derivedIntegerProbe;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DerivedLongProbe createDerivedLongProbe() {
        DerivedLongProbeImpl derivedLongProbe = new DerivedLongProbeImpl();
        return derivedLongProbe;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public IntegerProbe createIntegerProbe() {
        IntegerProbeImpl integerProbe = new IntegerProbeImpl();
        return integerProbe;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public LongProbe createLongProbe() {
        LongProbeImpl longProbe = new LongProbeImpl();
        return longProbe;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ObjectProbe createObjectProbe() {
        ObjectProbeImpl objectProbe = new ObjectProbeImpl();
        return objectProbe;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ResultAggregationLevel createResultAggregationLevelFromString(EDataType eDataType, String initialValue) {
        ResultAggregationLevel result = ResultAggregationLevel.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertResultAggregationLevelToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public probespecPackage getprobespecPackage() {
        return (probespecPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static probespecPackage getPackage() {
        return probespecPackage.eINSTANCE;
    }

} //probespecFactoryImpl
