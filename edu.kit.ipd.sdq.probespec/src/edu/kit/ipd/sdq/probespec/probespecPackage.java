/**
 */
package edu.kit.ipd.sdq.probespec;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see edu.kit.ipd.sdq.probespec.probespecFactory
 * @model kind="package"
 * @generated
 */
public interface probespecPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "probespec";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://sdq.ipd.kit.edu/ProbeSpecification/2.0";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "probespec";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	probespecPackage eINSTANCE = edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl.init();

	/**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.ResultConfigurationImpl <em>Result Configuration</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.ResultConfigurationImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getResultConfiguration()
     * @generated
     */
	int RESULT_CONFIGURATION = 1;

	/**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_CONFIGURATION__AGGREGATION_LEVEL = 0;

	/**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_CONFIGURATION__ENABLED = 1;

	/**
     * The number of structural features of the '<em>Result Configuration</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_CONFIGURATION_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.ProbeImpl <em>Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.ProbeImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getProbe()
     * @generated
     */
	int PROBE = 0;

	/**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE__AGGREGATION_LEVEL = RESULT_CONFIGURATION__AGGREGATION_LEVEL;

	/**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE__ENABLED = RESULT_CONFIGURATION__ENABLED;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE__ID = RESULT_CONFIGURATION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE__NAME = RESULT_CONFIGURATION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROBE__TRANSIENT = RESULT_CONFIGURATION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_FEATURE_COUNT = RESULT_CONFIGURATION_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.MountPointImpl <em>Mount Point</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.MountPointImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getMountPoint()
     * @generated
     */
	int MOUNT_POINT = 2;

	/**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MOUNT_POINT__AGGREGATION_LEVEL = RESULT_CONFIGURATION__AGGREGATION_LEVEL;

	/**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MOUNT_POINT__ENABLED = RESULT_CONFIGURATION__ENABLED;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MOUNT_POINT__ID = RESULT_CONFIGURATION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MOUNT_POINT__NAME = RESULT_CONFIGURATION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Mount Point</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MOUNT_POINT_FEATURE_COUNT = RESULT_CONFIGURATION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.ProbeRepositoryImpl <em>Probe Repository</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.ProbeRepositoryImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getProbeRepository()
     * @generated
     */
	int PROBE_REPOSITORY = 4;

	/**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.DerivedProbeImpl <em>Derived Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.DerivedProbeImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getDerivedProbe()
     * @generated
     */
	int DERIVED_PROBE = 5;

	/**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.MeasurableEntityImpl <em>Measurable Entity</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.MeasurableEntityImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getMeasurableEntity()
     * @generated
     */
	int MEASURABLE_ENTITY = 6;

	/**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.DoubleProbeImpl <em>Double Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.DoubleProbeImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getDoubleProbe()
     * @generated
     */
	int DOUBLE_PROBE = 9;

	/**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.DerivedObjectProbeImpl <em>Derived Object Probe</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.DerivedObjectProbeImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getDerivedObjectProbe()
     * @generated
     */
    int DERIVED_OBJECT_PROBE = 10;

    /**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.IntegerProbeImpl <em>Integer Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.IntegerProbeImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getIntegerProbe()
     * @generated
     */
	int INTEGER_PROBE = 7;

	/**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.LongProbeImpl <em>Long Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.LongProbeImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getLongProbe()
     * @generated
     */
	int LONG_PROBE = 8;

	/**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.ObjectProbeImpl <em>Object Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.ObjectProbeImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getObjectProbe()
     * @generated
     */
	int OBJECT_PROBE = 3;

	/**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OBJECT_PROBE__AGGREGATION_LEVEL = PROBE__AGGREGATION_LEVEL;

	/**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OBJECT_PROBE__ENABLED = PROBE__ENABLED;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OBJECT_PROBE__ID = PROBE__ID;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OBJECT_PROBE__NAME = PROBE__NAME;

	/**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_PROBE__TRANSIENT = PROBE__TRANSIENT;

    /**
     * The number of structural features of the '<em>Object Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OBJECT_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_REPOSITORY__AGGREGATION_LEVEL = RESULT_CONFIGURATION__AGGREGATION_LEVEL;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_REPOSITORY__ENABLED = RESULT_CONFIGURATION__ENABLED;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_REPOSITORY__ID = RESULT_CONFIGURATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Derived Probes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_REPOSITORY__DERIVED_PROBES = RESULT_CONFIGURATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Imported Repositories</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_REPOSITORY__IMPORTED_REPOSITORIES = RESULT_CONFIGURATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Entities</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_REPOSITORY__ENTITIES = RESULT_CONFIGURATION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Probe Repository</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_REPOSITORY_FEATURE_COUNT = RESULT_CONFIGURATION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DERIVED_PROBE__AGGREGATION_LEVEL = PROBE__AGGREGATION_LEVEL;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DERIVED_PROBE__ENABLED = PROBE__ENABLED;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DERIVED_PROBE__ID = PROBE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DERIVED_PROBE__NAME = PROBE__NAME;

    /**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_PROBE__TRANSIENT = PROBE__TRANSIENT;

    /**
     * The feature id for the '<em><b>Source Probes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_PROBE__SOURCE_PROBES = PROBE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Derived Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DERIVED_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEASURABLE_ENTITY__AGGREGATION_LEVEL = RESULT_CONFIGURATION__AGGREGATION_LEVEL;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEASURABLE_ENTITY__ENABLED = RESULT_CONFIGURATION__ENABLED;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEASURABLE_ENTITY__ID = RESULT_CONFIGURATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Measurable Entity</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEASURABLE_ENTITY_FEATURE_COUNT = RESULT_CONFIGURATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTEGER_PROBE__AGGREGATION_LEVEL = PROBE__AGGREGATION_LEVEL;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTEGER_PROBE__ENABLED = PROBE__ENABLED;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTEGER_PROBE__ID = PROBE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTEGER_PROBE__NAME = PROBE__NAME;

    /**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTEGER_PROBE__TRANSIENT = PROBE__TRANSIENT;

    /**
     * The number of structural features of the '<em>Integer Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTEGER_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LONG_PROBE__AGGREGATION_LEVEL = PROBE__AGGREGATION_LEVEL;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LONG_PROBE__ENABLED = PROBE__ENABLED;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LONG_PROBE__ID = PROBE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LONG_PROBE__NAME = PROBE__NAME;

    /**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LONG_PROBE__TRANSIENT = PROBE__TRANSIENT;

    /**
     * The number of structural features of the '<em>Long Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LONG_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOUBLE_PROBE__AGGREGATION_LEVEL = PROBE__AGGREGATION_LEVEL;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOUBLE_PROBE__ENABLED = PROBE__ENABLED;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOUBLE_PROBE__ID = PROBE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOUBLE_PROBE__NAME = PROBE__NAME;

    /**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_PROBE__TRANSIENT = PROBE__TRANSIENT;

    /**
     * The number of structural features of the '<em>Double Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOUBLE_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_OBJECT_PROBE__AGGREGATION_LEVEL = DERIVED_PROBE__AGGREGATION_LEVEL;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_OBJECT_PROBE__ENABLED = DERIVED_PROBE__ENABLED;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_OBJECT_PROBE__ID = DERIVED_PROBE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_OBJECT_PROBE__NAME = DERIVED_PROBE__NAME;

    /**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_OBJECT_PROBE__TRANSIENT = DERIVED_PROBE__TRANSIENT;

    /**
     * The feature id for the '<em><b>Source Probes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_OBJECT_PROBE__SOURCE_PROBES = DERIVED_PROBE__SOURCE_PROBES;

    /**
     * The number of structural features of the '<em>Derived Object Probe</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_OBJECT_PROBE_FEATURE_COUNT = DERIVED_PROBE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.DerivedDoubleProbeImpl <em>Derived Double Probe</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.DerivedDoubleProbeImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getDerivedDoubleProbe()
     * @generated
     */
    int DERIVED_DOUBLE_PROBE = 11;

    /**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_DOUBLE_PROBE__AGGREGATION_LEVEL = DERIVED_PROBE__AGGREGATION_LEVEL;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_DOUBLE_PROBE__ENABLED = DERIVED_PROBE__ENABLED;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_DOUBLE_PROBE__ID = DERIVED_PROBE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_DOUBLE_PROBE__NAME = DERIVED_PROBE__NAME;

    /**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_DOUBLE_PROBE__TRANSIENT = DERIVED_PROBE__TRANSIENT;

    /**
     * The feature id for the '<em><b>Source Probes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_DOUBLE_PROBE__SOURCE_PROBES = DERIVED_PROBE__SOURCE_PROBES;

    /**
     * The number of structural features of the '<em>Derived Double Probe</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_DOUBLE_PROBE_FEATURE_COUNT = DERIVED_PROBE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.DerivedIntegerProbeImpl <em>Derived Integer Probe</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.DerivedIntegerProbeImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getDerivedIntegerProbe()
     * @generated
     */
    int DERIVED_INTEGER_PROBE = 12;

    /**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_INTEGER_PROBE__AGGREGATION_LEVEL = DERIVED_PROBE__AGGREGATION_LEVEL;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_INTEGER_PROBE__ENABLED = DERIVED_PROBE__ENABLED;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_INTEGER_PROBE__ID = DERIVED_PROBE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_INTEGER_PROBE__NAME = DERIVED_PROBE__NAME;

    /**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_INTEGER_PROBE__TRANSIENT = DERIVED_PROBE__TRANSIENT;

    /**
     * The feature id for the '<em><b>Source Probes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_INTEGER_PROBE__SOURCE_PROBES = DERIVED_PROBE__SOURCE_PROBES;

    /**
     * The number of structural features of the '<em>Derived Integer Probe</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_INTEGER_PROBE_FEATURE_COUNT = DERIVED_PROBE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.impl.DerivedLongProbeImpl <em>Derived Long Probe</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.impl.DerivedLongProbeImpl
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getDerivedLongProbe()
     * @generated
     */
    int DERIVED_LONG_PROBE = 13;

    /**
     * The feature id for the '<em><b>Aggregation Level</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_LONG_PROBE__AGGREGATION_LEVEL = DERIVED_PROBE__AGGREGATION_LEVEL;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_LONG_PROBE__ENABLED = DERIVED_PROBE__ENABLED;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_LONG_PROBE__ID = DERIVED_PROBE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_LONG_PROBE__NAME = DERIVED_PROBE__NAME;

    /**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_LONG_PROBE__TRANSIENT = DERIVED_PROBE__TRANSIENT;

    /**
     * The feature id for the '<em><b>Source Probes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_LONG_PROBE__SOURCE_PROBES = DERIVED_PROBE__SOURCE_PROBES;

    /**
     * The number of structural features of the '<em>Derived Long Probe</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DERIVED_LONG_PROBE_FEATURE_COUNT = DERIVED_PROBE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link edu.kit.ipd.sdq.probespec.ResultAggregationLevel <em>Result Aggregation Level</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see edu.kit.ipd.sdq.probespec.ResultAggregationLevel
     * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getResultAggregationLevel()
     * @generated
     */
	int RESULT_AGGREGATION_LEVEL = 14;


	/**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.Probe <em>Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Probe</em>'.
     * @see edu.kit.ipd.sdq.probespec.Probe
     * @generated
     */
	EClass getProbe();

	/**
     * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.probespec.Probe#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see edu.kit.ipd.sdq.probespec.Probe#getName()
     * @see #getProbe()
     * @generated
     */
	EAttribute getProbe_Name();

	/**
     * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.probespec.Probe#isTransient <em>Transient</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Transient</em>'.
     * @see edu.kit.ipd.sdq.probespec.Probe#isTransient()
     * @see #getProbe()
     * @generated
     */
    EAttribute getProbe_Transient();

    /**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.ResultConfiguration <em>Result Configuration</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Result Configuration</em>'.
     * @see edu.kit.ipd.sdq.probespec.ResultConfiguration
     * @generated
     */
	EClass getResultConfiguration();

	/**
     * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.probespec.ResultConfiguration#getAggregationLevel <em>Aggregation Level</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Aggregation Level</em>'.
     * @see edu.kit.ipd.sdq.probespec.ResultConfiguration#getAggregationLevel()
     * @see #getResultConfiguration()
     * @generated
     */
	EAttribute getResultConfiguration_AggregationLevel();

	/**
     * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.probespec.ResultConfiguration#isEnabled <em>Enabled</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Enabled</em>'.
     * @see edu.kit.ipd.sdq.probespec.ResultConfiguration#isEnabled()
     * @see #getResultConfiguration()
     * @generated
     */
	EAttribute getResultConfiguration_Enabled();

	/**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.MountPoint <em>Mount Point</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Mount Point</em>'.
     * @see edu.kit.ipd.sdq.probespec.MountPoint
     * @generated
     */
	EClass getMountPoint();

	/**
     * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.probespec.MountPoint#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see edu.kit.ipd.sdq.probespec.MountPoint#getName()
     * @see #getMountPoint()
     * @generated
     */
	EAttribute getMountPoint_Name();

	/**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.ProbeRepository <em>Probe Repository</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Probe Repository</em>'.
     * @see edu.kit.ipd.sdq.probespec.ProbeRepository
     * @generated
     */
	EClass getProbeRepository();

	/**
     * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.probespec.ProbeRepository#getDerivedProbes <em>Derived Probes</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Derived Probes</em>'.
     * @see edu.kit.ipd.sdq.probespec.ProbeRepository#getDerivedProbes()
     * @see #getProbeRepository()
     * @generated
     */
	EReference getProbeRepository_DerivedProbes();

	/**
     * Returns the meta object for the reference list '{@link edu.kit.ipd.sdq.probespec.ProbeRepository#getImportedRepositories <em>Imported Repositories</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Imported Repositories</em>'.
     * @see edu.kit.ipd.sdq.probespec.ProbeRepository#getImportedRepositories()
     * @see #getProbeRepository()
     * @generated
     */
	EReference getProbeRepository_ImportedRepositories();

	/**
     * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.probespec.ProbeRepository#getEntities <em>Entities</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Entities</em>'.
     * @see edu.kit.ipd.sdq.probespec.ProbeRepository#getEntities()
     * @see #getProbeRepository()
     * @generated
     */
	EReference getProbeRepository_Entities();

	/**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.DerivedProbe <em>Derived Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Derived Probe</em>'.
     * @see edu.kit.ipd.sdq.probespec.DerivedProbe
     * @generated
     */
	EClass getDerivedProbe();

	/**
     * Returns the meta object for the reference list '{@link edu.kit.ipd.sdq.probespec.DerivedProbe#getSourceProbes <em>Source Probes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Source Probes</em>'.
     * @see edu.kit.ipd.sdq.probespec.DerivedProbe#getSourceProbes()
     * @see #getDerivedProbe()
     * @generated
     */
    EReference getDerivedProbe_SourceProbes();

    /**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.MeasurableEntity <em>Measurable Entity</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Measurable Entity</em>'.
     * @see edu.kit.ipd.sdq.probespec.MeasurableEntity
     * @generated
     */
	EClass getMeasurableEntity();

	/**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.DoubleProbe <em>Double Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Double Probe</em>'.
     * @see edu.kit.ipd.sdq.probespec.DoubleProbe
     * @generated
     */
	EClass getDoubleProbe();

	/**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.DerivedObjectProbe <em>Derived Object Probe</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Derived Object Probe</em>'.
     * @see edu.kit.ipd.sdq.probespec.DerivedObjectProbe
     * @generated
     */
    EClass getDerivedObjectProbe();

    /**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.DerivedDoubleProbe <em>Derived Double Probe</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Derived Double Probe</em>'.
     * @see edu.kit.ipd.sdq.probespec.DerivedDoubleProbe
     * @generated
     */
    EClass getDerivedDoubleProbe();

    /**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.DerivedIntegerProbe <em>Derived Integer Probe</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Derived Integer Probe</em>'.
     * @see edu.kit.ipd.sdq.probespec.DerivedIntegerProbe
     * @generated
     */
    EClass getDerivedIntegerProbe();

    /**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.DerivedLongProbe <em>Derived Long Probe</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Derived Long Probe</em>'.
     * @see edu.kit.ipd.sdq.probespec.DerivedLongProbe
     * @generated
     */
    EClass getDerivedLongProbe();

    /**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.IntegerProbe <em>Integer Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Integer Probe</em>'.
     * @see edu.kit.ipd.sdq.probespec.IntegerProbe
     * @generated
     */
	EClass getIntegerProbe();

	/**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.LongProbe <em>Long Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Long Probe</em>'.
     * @see edu.kit.ipd.sdq.probespec.LongProbe
     * @generated
     */
	EClass getLongProbe();

	/**
     * Returns the meta object for class '{@link edu.kit.ipd.sdq.probespec.ObjectProbe <em>Object Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Object Probe</em>'.
     * @see edu.kit.ipd.sdq.probespec.ObjectProbe
     * @generated
     */
	EClass getObjectProbe();

	/**
     * Returns the meta object for enum '{@link edu.kit.ipd.sdq.probespec.ResultAggregationLevel <em>Result Aggregation Level</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Result Aggregation Level</em>'.
     * @see edu.kit.ipd.sdq.probespec.ResultAggregationLevel
     * @generated
     */
	EEnum getResultAggregationLevel();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	probespecFactory getprobespecFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.ProbeImpl <em>Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.ProbeImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getProbe()
         * @generated
         */
		EClass PROBE = eINSTANCE.getProbe();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PROBE__NAME = eINSTANCE.getProbe_Name();

		/**
         * The meta object literal for the '<em><b>Transient</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROBE__TRANSIENT = eINSTANCE.getProbe_Transient();

        /**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.ResultConfigurationImpl <em>Result Configuration</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.ResultConfigurationImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getResultConfiguration()
         * @generated
         */
		EClass RESULT_CONFIGURATION = eINSTANCE.getResultConfiguration();

		/**
         * The meta object literal for the '<em><b>Aggregation Level</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESULT_CONFIGURATION__AGGREGATION_LEVEL = eINSTANCE.getResultConfiguration_AggregationLevel();

		/**
         * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESULT_CONFIGURATION__ENABLED = eINSTANCE.getResultConfiguration_Enabled();

		/**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.MountPointImpl <em>Mount Point</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.MountPointImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getMountPoint()
         * @generated
         */
		EClass MOUNT_POINT = eINSTANCE.getMountPoint();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MOUNT_POINT__NAME = eINSTANCE.getMountPoint_Name();

		/**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.ProbeRepositoryImpl <em>Probe Repository</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.ProbeRepositoryImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getProbeRepository()
         * @generated
         */
		EClass PROBE_REPOSITORY = eINSTANCE.getProbeRepository();

		/**
         * The meta object literal for the '<em><b>Derived Probes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PROBE_REPOSITORY__DERIVED_PROBES = eINSTANCE.getProbeRepository_DerivedProbes();

		/**
         * The meta object literal for the '<em><b>Imported Repositories</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PROBE_REPOSITORY__IMPORTED_REPOSITORIES = eINSTANCE.getProbeRepository_ImportedRepositories();

		/**
         * The meta object literal for the '<em><b>Entities</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PROBE_REPOSITORY__ENTITIES = eINSTANCE.getProbeRepository_Entities();

		/**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.DerivedProbeImpl <em>Derived Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.DerivedProbeImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getDerivedProbe()
         * @generated
         */
		EClass DERIVED_PROBE = eINSTANCE.getDerivedProbe();

		/**
         * The meta object literal for the '<em><b>Source Probes</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DERIVED_PROBE__SOURCE_PROBES = eINSTANCE.getDerivedProbe_SourceProbes();

        /**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.MeasurableEntityImpl <em>Measurable Entity</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.MeasurableEntityImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getMeasurableEntity()
         * @generated
         */
		EClass MEASURABLE_ENTITY = eINSTANCE.getMeasurableEntity();

		/**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.DoubleProbeImpl <em>Double Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.DoubleProbeImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getDoubleProbe()
         * @generated
         */
		EClass DOUBLE_PROBE = eINSTANCE.getDoubleProbe();

		/**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.DerivedObjectProbeImpl <em>Derived Object Probe</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.DerivedObjectProbeImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getDerivedObjectProbe()
         * @generated
         */
        EClass DERIVED_OBJECT_PROBE = eINSTANCE.getDerivedObjectProbe();

        /**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.DerivedDoubleProbeImpl <em>Derived Double Probe</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.DerivedDoubleProbeImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getDerivedDoubleProbe()
         * @generated
         */
        EClass DERIVED_DOUBLE_PROBE = eINSTANCE.getDerivedDoubleProbe();

        /**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.DerivedIntegerProbeImpl <em>Derived Integer Probe</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.DerivedIntegerProbeImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getDerivedIntegerProbe()
         * @generated
         */
        EClass DERIVED_INTEGER_PROBE = eINSTANCE.getDerivedIntegerProbe();

        /**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.DerivedLongProbeImpl <em>Derived Long Probe</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.DerivedLongProbeImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getDerivedLongProbe()
         * @generated
         */
        EClass DERIVED_LONG_PROBE = eINSTANCE.getDerivedLongProbe();

        /**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.IntegerProbeImpl <em>Integer Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.IntegerProbeImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getIntegerProbe()
         * @generated
         */
		EClass INTEGER_PROBE = eINSTANCE.getIntegerProbe();

		/**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.LongProbeImpl <em>Long Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.LongProbeImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getLongProbe()
         * @generated
         */
		EClass LONG_PROBE = eINSTANCE.getLongProbe();

		/**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.impl.ObjectProbeImpl <em>Object Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.impl.ObjectProbeImpl
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getObjectProbe()
         * @generated
         */
		EClass OBJECT_PROBE = eINSTANCE.getObjectProbe();

		/**
         * The meta object literal for the '{@link edu.kit.ipd.sdq.probespec.ResultAggregationLevel <em>Result Aggregation Level</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see edu.kit.ipd.sdq.probespec.ResultAggregationLevel
         * @see edu.kit.ipd.sdq.probespec.impl.probespecPackageImpl#getResultAggregationLevel()
         * @generated
         */
		EEnum RESULT_AGGREGATION_LEVEL = eINSTANCE.getResultAggregationLevel();

	}

} //probespecPackage
