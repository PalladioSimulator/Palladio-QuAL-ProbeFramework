/**
 */
package edu.kit.ipd.sdq.probespec.impl;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

import edu.kit.ipd.sdq.probespec.ContextMatchStrategy;
import edu.kit.ipd.sdq.probespec.DerivedDoubleProbe;
import edu.kit.ipd.sdq.probespec.DerivedIntegerProbe;
import edu.kit.ipd.sdq.probespec.DerivedLongProbe;
import edu.kit.ipd.sdq.probespec.DerivedObjectProbe;
import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.DoubleProbe;
import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.LongProbe;
import edu.kit.ipd.sdq.probespec.MeasurableEntity;
import edu.kit.ipd.sdq.probespec.MountPoint;
import edu.kit.ipd.sdq.probespec.ObjectProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.ProbeRepository;
import edu.kit.ipd.sdq.probespec.ResultAggregationLevel;
import edu.kit.ipd.sdq.probespec.ResultConfiguration;
import edu.kit.ipd.sdq.probespec.probespecFactory;
import edu.kit.ipd.sdq.probespec.probespecPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class probespecPackageImpl extends EPackageImpl implements probespecPackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass probeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass resultConfigurationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass mountPointEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass probeRepositoryEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass derivedProbeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass measurableEntityEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass doubleProbeEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass derivedObjectProbeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass derivedDoubleProbeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass derivedIntegerProbeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass derivedLongProbeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass integerProbeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass longProbeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass objectProbeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum resultAggregationLevelEEnum = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum contextMatchStrategyEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see edu.kit.ipd.sdq.probespec.probespecPackage#eNS_URI
     * @see #init()
     * @generated
     */
	private probespecPackageImpl() {
        super(eNS_URI, probespecFactory.eINSTANCE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static boolean isInited = false;

	/**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link probespecPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static probespecPackage init() {
        if (isInited) return (probespecPackage)EPackage.Registry.INSTANCE.getEPackage(probespecPackage.eNS_URI);

        // Obtain or create and register package
        probespecPackageImpl theprobespecPackage = (probespecPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof probespecPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new probespecPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();
        IdentifierPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theprobespecPackage.createPackageContents();

        // Initialize created meta-data
        theprobespecPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theprobespecPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(probespecPackage.eNS_URI, theprobespecPackage);
        return theprobespecPackage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getProbe() {
        return probeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getProbe_Name() {
        return (EAttribute)probeEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProbe_Transient() {
        return (EAttribute)probeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getResultConfiguration() {
        return resultConfigurationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getResultConfiguration_AggregationLevel() {
        return (EAttribute)resultConfigurationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getResultConfiguration_Enabled() {
        return (EAttribute)resultConfigurationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getMountPoint() {
        return mountPointEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getMountPoint_Name() {
        return (EAttribute)mountPointEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getProbeRepository() {
        return probeRepositoryEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getProbeRepository_DerivedProbes() {
        return (EReference)probeRepositoryEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getProbeRepository_ImportedRepositories() {
        return (EReference)probeRepositoryEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getProbeRepository_Entities() {
        return (EReference)probeRepositoryEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getDerivedProbe() {
        return derivedProbeEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDerivedProbe_SourceProbes() {
        return (EReference)derivedProbeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDerivedProbe_MatchStrategy() {
        return (EAttribute)derivedProbeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getMeasurableEntity() {
        return measurableEntityEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getDoubleProbe() {
        return doubleProbeEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDerivedObjectProbe() {
        return derivedObjectProbeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDerivedDoubleProbe() {
        return derivedDoubleProbeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDerivedIntegerProbe() {
        return derivedIntegerProbeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDerivedLongProbe() {
        return derivedLongProbeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIntegerProbe() {
        return integerProbeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getLongProbe() {
        return longProbeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getObjectProbe() {
        return objectProbeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EEnum getResultAggregationLevel() {
        return resultAggregationLevelEEnum;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getContextMatchStrategy() {
        return contextMatchStrategyEEnum;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public probespecFactory getprobespecFactory() {
        return (probespecFactory)getEFactoryInstance();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isCreated = false;

	/**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        probeEClass = createEClass(PROBE);
        createEAttribute(probeEClass, PROBE__NAME);
        createEAttribute(probeEClass, PROBE__TRANSIENT);

        resultConfigurationEClass = createEClass(RESULT_CONFIGURATION);
        createEAttribute(resultConfigurationEClass, RESULT_CONFIGURATION__AGGREGATION_LEVEL);
        createEAttribute(resultConfigurationEClass, RESULT_CONFIGURATION__ENABLED);

        mountPointEClass = createEClass(MOUNT_POINT);
        createEAttribute(mountPointEClass, MOUNT_POINT__NAME);

        objectProbeEClass = createEClass(OBJECT_PROBE);

        probeRepositoryEClass = createEClass(PROBE_REPOSITORY);
        createEReference(probeRepositoryEClass, PROBE_REPOSITORY__DERIVED_PROBES);
        createEReference(probeRepositoryEClass, PROBE_REPOSITORY__IMPORTED_REPOSITORIES);
        createEReference(probeRepositoryEClass, PROBE_REPOSITORY__ENTITIES);

        derivedProbeEClass = createEClass(DERIVED_PROBE);
        createEReference(derivedProbeEClass, DERIVED_PROBE__SOURCE_PROBES);
        createEAttribute(derivedProbeEClass, DERIVED_PROBE__MATCH_STRATEGY);

        measurableEntityEClass = createEClass(MEASURABLE_ENTITY);

        integerProbeEClass = createEClass(INTEGER_PROBE);

        longProbeEClass = createEClass(LONG_PROBE);

        doubleProbeEClass = createEClass(DOUBLE_PROBE);

        derivedObjectProbeEClass = createEClass(DERIVED_OBJECT_PROBE);

        derivedDoubleProbeEClass = createEClass(DERIVED_DOUBLE_PROBE);

        derivedIntegerProbeEClass = createEClass(DERIVED_INTEGER_PROBE);

        derivedLongProbeEClass = createEClass(DERIVED_LONG_PROBE);

        // Create enums
        resultAggregationLevelEEnum = createEEnum(RESULT_AGGREGATION_LEVEL);
        contextMatchStrategyEEnum = createEEnum(CONTEXT_MATCH_STRATEGY);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isInitialized = false;

	/**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        IdentifierPackage theIdentifierPackage = (IdentifierPackage)EPackage.Registry.INSTANCE.getEPackage(IdentifierPackage.eNS_URI);

        // Create type parameters
        ETypeParameter probeEClass_T = addETypeParameter(probeEClass, "T");
        ETypeParameter derivedProbeEClass_Td = addETypeParameter(derivedProbeEClass, "Td");

        // Set bounds for type parameters

        // Add supertypes to classes
        probeEClass.getESuperTypes().add(this.getResultConfiguration());
        probeEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
        mountPointEClass.getESuperTypes().add(this.getResultConfiguration());
        mountPointEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
        EGenericType g1 = createEGenericType(this.getProbe());
        EGenericType g2 = createEGenericType(ecorePackage.getEJavaObject());
        g1.getETypeArguments().add(g2);
        objectProbeEClass.getEGenericSuperTypes().add(g1);
        probeRepositoryEClass.getESuperTypes().add(this.getResultConfiguration());
        probeRepositoryEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
        g1 = createEGenericType(this.getProbe());
        g2 = createEGenericType(derivedProbeEClass_Td);
        g1.getETypeArguments().add(g2);
        derivedProbeEClass.getEGenericSuperTypes().add(g1);
        measurableEntityEClass.getESuperTypes().add(this.getResultConfiguration());
        measurableEntityEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
        g1 = createEGenericType(this.getProbe());
        g2 = createEGenericType(ecorePackage.getEIntegerObject());
        g1.getETypeArguments().add(g2);
        integerProbeEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getProbe());
        g2 = createEGenericType(ecorePackage.getELongObject());
        g1.getETypeArguments().add(g2);
        longProbeEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getProbe());
        g2 = createEGenericType(ecorePackage.getEDoubleObject());
        g1.getETypeArguments().add(g2);
        doubleProbeEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getDerivedProbe());
        g2 = createEGenericType(ecorePackage.getEJavaObject());
        g1.getETypeArguments().add(g2);
        derivedObjectProbeEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getDerivedProbe());
        g2 = createEGenericType(ecorePackage.getEDoubleObject());
        g1.getETypeArguments().add(g2);
        derivedDoubleProbeEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getDerivedProbe());
        g2 = createEGenericType(ecorePackage.getEIntegerObject());
        g1.getETypeArguments().add(g2);
        derivedIntegerProbeEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getDerivedProbe());
        g2 = createEGenericType(ecorePackage.getELongObject());
        g1.getETypeArguments().add(g2);
        derivedLongProbeEClass.getEGenericSuperTypes().add(g1);

        // Initialize classes and features; add operations and parameters
        initEClass(probeEClass, Probe.class, "Probe", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getProbe_Name(), ecorePackage.getEString(), "name", null, 1, 1, Probe.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getProbe_Transient(), ecorePackage.getEBoolean(), "transient", null, 1, 1, Probe.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        EOperation op = addEOperation(probeEClass, null, "getGenericClass", 1, 1, IS_UNIQUE, !IS_ORDERED);
        g1 = createEGenericType(ecorePackage.getEJavaClass());
        g2 = createEGenericType(probeEClass_T);
        g1.getETypeArguments().add(g2);
        initEOperation(op, g1);

        initEClass(resultConfigurationEClass, ResultConfiguration.class, "ResultConfiguration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getResultConfiguration_AggregationLevel(), this.getResultAggregationLevel(), "aggregationLevel", null, 1, 1, ResultConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getResultConfiguration_Enabled(), ecorePackage.getEBoolean(), "enabled", null, 1, 1, ResultConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(mountPointEClass, MountPoint.class, "MountPoint", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMountPoint_Name(), ecorePackage.getEString(), "name", null, 1, 1, MountPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        addEOperation(mountPointEClass, this.getObjectProbe(), "getProbes", 0, -1, IS_UNIQUE, !IS_ORDERED);

        initEClass(objectProbeEClass, ObjectProbe.class, "ObjectProbe", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(probeRepositoryEClass, ProbeRepository.class, "ProbeRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        g1 = createEGenericType(this.getDerivedProbe());
        g2 = createEGenericType(ecorePackage.getEJavaObject());
        g1.getETypeArguments().add(g2);
        initEReference(getProbeRepository_DerivedProbes(), g1, null, "derivedProbes", null, 0, -1, ProbeRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getProbeRepository_ImportedRepositories(), this.getProbeRepository(), null, "importedRepositories", null, 0, -1, ProbeRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getProbeRepository_Entities(), this.getMeasurableEntity(), null, "entities", null, 0, -1, ProbeRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(derivedProbeEClass, DerivedProbe.class, "DerivedProbe", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        g1 = createEGenericType(this.getProbe());
        g2 = createEGenericType(derivedProbeEClass_Td);
        g1.getETypeArguments().add(g2);
        initEReference(getDerivedProbe_SourceProbes(), g1, null, "sourceProbes", null, 1, -1, DerivedProbe.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getDerivedProbe_MatchStrategy(), this.getContextMatchStrategy(), "matchStrategy", "SAME_OR_PARENT_CONTEXT", 1, 1, DerivedProbe.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(measurableEntityEClass, MeasurableEntity.class, "MeasurableEntity", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(measurableEntityEClass, this.getMountPoint(), "getMountPoints", 0, -1, IS_UNIQUE, !IS_ORDERED);

        addEOperation(measurableEntityEClass, ecorePackage.getEObject(), "getDecoratedEntity", 1, 1, IS_UNIQUE, !IS_ORDERED);

        initEClass(integerProbeEClass, IntegerProbe.class, "IntegerProbe", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        op = addEOperation(integerProbeEClass, null, "getGenericClass", 1, 1, IS_UNIQUE, !IS_ORDERED);
        g1 = createEGenericType(ecorePackage.getEJavaClass());
        g2 = createEGenericType(ecorePackage.getEIntegerObject());
        g1.getETypeArguments().add(g2);
        initEOperation(op, g1);

        initEClass(longProbeEClass, LongProbe.class, "LongProbe", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        op = addEOperation(longProbeEClass, null, "getGenericClass", 1, 1, IS_UNIQUE, !IS_ORDERED);
        g1 = createEGenericType(ecorePackage.getEJavaClass());
        g2 = createEGenericType(ecorePackage.getELongObject());
        g1.getETypeArguments().add(g2);
        initEOperation(op, g1);

        initEClass(doubleProbeEClass, DoubleProbe.class, "DoubleProbe", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        op = addEOperation(doubleProbeEClass, null, "getGenericClass", 1, 1, IS_UNIQUE, !IS_ORDERED);
        g1 = createEGenericType(ecorePackage.getEJavaClass());
        g2 = createEGenericType(ecorePackage.getEDoubleObject());
        g1.getETypeArguments().add(g2);
        initEOperation(op, g1);

        initEClass(derivedObjectProbeEClass, DerivedObjectProbe.class, "DerivedObjectProbe", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        op = addEOperation(derivedObjectProbeEClass, null, "getGenericClass", 1, 1, IS_UNIQUE, !IS_ORDERED);
        g1 = createEGenericType(ecorePackage.getEJavaClass());
        g2 = createEGenericType(ecorePackage.getEJavaObject());
        g1.getETypeArguments().add(g2);
        initEOperation(op, g1);

        initEClass(derivedDoubleProbeEClass, DerivedDoubleProbe.class, "DerivedDoubleProbe", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        op = addEOperation(derivedDoubleProbeEClass, null, "getGenericClass", 1, 1, IS_UNIQUE, !IS_ORDERED);
        g1 = createEGenericType(ecorePackage.getEJavaClass());
        g2 = createEGenericType(ecorePackage.getEDoubleObject());
        g1.getETypeArguments().add(g2);
        initEOperation(op, g1);

        initEClass(derivedIntegerProbeEClass, DerivedIntegerProbe.class, "DerivedIntegerProbe", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        op = addEOperation(derivedIntegerProbeEClass, null, "getGenericClass", 1, 1, IS_UNIQUE, !IS_ORDERED);
        g1 = createEGenericType(ecorePackage.getEJavaClass());
        g2 = createEGenericType(ecorePackage.getEIntegerObject());
        g1.getETypeArguments().add(g2);
        initEOperation(op, g1);

        initEClass(derivedLongProbeEClass, DerivedLongProbe.class, "DerivedLongProbe", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        op = addEOperation(derivedLongProbeEClass, null, "getGenericClass", 1, 1, IS_UNIQUE, !IS_ORDERED);
        g1 = createEGenericType(ecorePackage.getEJavaClass());
        g2 = createEGenericType(ecorePackage.getELongObject());
        g1.getETypeArguments().add(g2);
        initEOperation(op, g1);

        // Initialize enums and add enum literals
        initEEnum(resultAggregationLevelEEnum, ResultAggregationLevel.class, "ResultAggregationLevel");
        addEEnumLiteral(resultAggregationLevelEEnum, ResultAggregationLevel.INHERIT);
        addEEnumLiteral(resultAggregationLevelEEnum, ResultAggregationLevel.NONE);

        initEEnum(contextMatchStrategyEEnum, ContextMatchStrategy.class, "ContextMatchStrategy");
        addEEnumLiteral(contextMatchStrategyEEnum, ContextMatchStrategy.ALL_CONTEXTS);
        addEEnumLiteral(contextMatchStrategyEEnum, ContextMatchStrategy.SAME_CONTEXT);
        addEEnumLiteral(contextMatchStrategyEEnum, ContextMatchStrategy.SAME_OR_PARENT_CONTEXT);

        // Create resource
        createResource(eNS_URI);
    }

} //probespecPackageImpl
