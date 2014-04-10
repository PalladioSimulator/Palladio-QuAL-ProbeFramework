/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.probespec;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

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
 * @see org.palladiosimulator.probespec.probespecFactory
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
	String eNS_URI = "http://sdq.ipd.uka.de/ProbeSpec/0.1";

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
	probespecPackage eINSTANCE = org.palladiosimulator.probespec.impl.probespecPackageImpl.init();

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.CalculatorImpl <em>Calculator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.CalculatorImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getCalculator()
     * @generated
     */
	int CALCULATOR = 0;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALCULATOR__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALCULATOR__ACTIVE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Calculator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALCULATOR_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.UnaryCalculatorImpl <em>Unary Calculator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.UnaryCalculatorImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getUnaryCalculator()
     * @generated
     */
	int UNARY_CALCULATOR = 2;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UNARY_CALCULATOR__ID = CALCULATOR__ID;

	/**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UNARY_CALCULATOR__ACTIVE = CALCULATOR__ACTIVE;

	/**
     * The feature id for the '<em><b>Probe Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UNARY_CALCULATOR__PROBE_SET = CALCULATOR_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Unary Calculator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UNARY_CALCULATOR_FEATURE_COUNT = CALCULATOR_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.PassiveResourceCalculatorImpl <em>Passive Resource Calculator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.PassiveResourceCalculatorImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getPassiveResourceCalculator()
     * @generated
     */
	int PASSIVE_RESOURCE_CALCULATOR = 1;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSIVE_RESOURCE_CALCULATOR__ID = UNARY_CALCULATOR__ID;

	/**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSIVE_RESOURCE_CALCULATOR__ACTIVE = UNARY_CALCULATOR__ACTIVE;

	/**
     * The feature id for the '<em><b>Probe Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSIVE_RESOURCE_CALCULATOR__PROBE_SET = UNARY_CALCULATOR__PROBE_SET;

	/**
     * The number of structural features of the '<em>Passive Resource Calculator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSIVE_RESOURCE_CALCULATOR_FEATURE_COUNT = UNARY_CALCULATOR_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.ProbeSetImpl <em>Probe Set</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.ProbeSetImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getProbeSet()
     * @generated
     */
	int PROBE_SET = 3;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_SET__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
     * The feature id for the '<em><b>Probes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_SET__PROBES = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_SET__POSITION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Annotated Element</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_SET__ANNOTATED_ELEMENT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Probe Set</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_SET_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.ProbeImpl <em>Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.ProbeImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getProbe()
     * @generated
     */
	int PROBE = 4;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
     * The number of structural features of the '<em>Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.PassiveResourceStateProbeImpl <em>Passive Resource State Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.PassiveResourceStateProbeImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getPassiveResourceStateProbe()
     * @generated
     */
	int PASSIVE_RESOURCE_STATE_PROBE = 5;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSIVE_RESOURCE_STATE_PROBE__ID = PROBE__ID;

	/**
     * The number of structural features of the '<em>Passive Resource State Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSIVE_RESOURCE_STATE_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.StoExProbeImpl <em>Sto Ex Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.StoExProbeImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getStoExProbe()
     * @generated
     */
	int STO_EX_PROBE = 6;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STO_EX_PROBE__ID = PROBE__ID;

	/**
     * The number of structural features of the '<em>Sto Ex Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STO_EX_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.SEFFParameterProbeImpl <em>SEFF Parameter Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.SEFFParameterProbeImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getSEFFParameterProbe()
     * @generated
     */
	int SEFF_PARAMETER_PROBE = 7;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEFF_PARAMETER_PROBE__ID = PROBE__ID;

	/**
     * The number of structural features of the '<em>SEFF Parameter Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEFF_PARAMETER_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.CurrentTimeProbeImpl <em>Current Time Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.CurrentTimeProbeImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getCurrentTimeProbe()
     * @generated
     */
	int CURRENT_TIME_PROBE = 8;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CURRENT_TIME_PROBE__ID = PROBE__ID;

	/**
     * The number of structural features of the '<em>Current Time Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CURRENT_TIME_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.StoExCalculatorImpl <em>Sto Ex Calculator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.StoExCalculatorImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getStoExCalculator()
     * @generated
     */
	int STO_EX_CALCULATOR = 9;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STO_EX_CALCULATOR__ID = UNARY_CALCULATOR__ID;

	/**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STO_EX_CALCULATOR__ACTIVE = UNARY_CALCULATOR__ACTIVE;

	/**
     * The feature id for the '<em><b>Probe Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STO_EX_CALCULATOR__PROBE_SET = UNARY_CALCULATOR__PROBE_SET;

	/**
     * The number of structural features of the '<em>Sto Ex Calculator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STO_EX_CALCULATOR_FEATURE_COUNT = UNARY_CALCULATOR_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.SEFFParameterCalculatorImpl <em>SEFF Parameter Calculator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.SEFFParameterCalculatorImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getSEFFParameterCalculator()
     * @generated
     */
	int SEFF_PARAMETER_CALCULATOR = 10;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEFF_PARAMETER_CALCULATOR__ID = UNARY_CALCULATOR__ID;

	/**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEFF_PARAMETER_CALCULATOR__ACTIVE = UNARY_CALCULATOR__ACTIVE;

	/**
     * The feature id for the '<em><b>Probe Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEFF_PARAMETER_CALCULATOR__PROBE_SET = UNARY_CALCULATOR__PROBE_SET;

	/**
     * The number of structural features of the '<em>SEFF Parameter Calculator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEFF_PARAMETER_CALCULATOR_FEATURE_COUNT = UNARY_CALCULATOR_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.BinaryCalculatorImpl <em>Binary Calculator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.BinaryCalculatorImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getBinaryCalculator()
     * @generated
     */
	int BINARY_CALCULATOR = 12;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BINARY_CALCULATOR__ID = CALCULATOR__ID;

	/**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BINARY_CALCULATOR__ACTIVE = CALCULATOR__ACTIVE;

	/**
     * The feature id for the '<em><b>Probe Set</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BINARY_CALCULATOR__PROBE_SET = CALCULATOR_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Binary Calculator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BINARY_CALCULATOR_FEATURE_COUNT = CALCULATOR_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.ResponseTimeCalculatorImpl <em>Response Time Calculator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.ResponseTimeCalculatorImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getResponseTimeCalculator()
     * @generated
     */
	int RESPONSE_TIME_CALCULATOR = 11;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_CALCULATOR__ID = BINARY_CALCULATOR__ID;

	/**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_CALCULATOR__ACTIVE = BINARY_CALCULATOR__ACTIVE;

	/**
     * The feature id for the '<em><b>Probe Set</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_CALCULATOR__PROBE_SET = BINARY_CALCULATOR__PROBE_SET;

	/**
     * The number of structural features of the '<em>Response Time Calculator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_CALCULATOR_FEATURE_COUNT = BINARY_CALCULATOR_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.WaitingTimeCalculatorImpl <em>Waiting Time Calculator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.WaitingTimeCalculatorImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getWaitingTimeCalculator()
     * @generated
     */
	int WAITING_TIME_CALCULATOR = 13;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WAITING_TIME_CALCULATOR__ID = BINARY_CALCULATOR__ID;

	/**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WAITING_TIME_CALCULATOR__ACTIVE = BINARY_CALCULATOR__ACTIVE;

	/**
     * The feature id for the '<em><b>Probe Set</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WAITING_TIME_CALCULATOR__PROBE_SET = BINARY_CALCULATOR__PROBE_SET;

	/**
     * The number of structural features of the '<em>Waiting Time Calculator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WAITING_TIME_CALCULATOR_FEATURE_COUNT = BINARY_CALCULATOR_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.ProbeSpecRepositoryImpl <em>Probe Spec Repository</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.ProbeSpecRepositoryImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getProbeSpecRepository()
     * @generated
     */
	int PROBE_SPEC_REPOSITORY = 14;

	/**
     * The feature id for the '<em><b>Calculator</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_SPEC_REPOSITORY__CALCULATOR = 0;

	/**
     * The number of structural features of the '<em>Probe Spec Repository</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROBE_SPEC_REPOSITORY_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.CPUStateProbeImpl <em>CPU State Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.CPUStateProbeImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getCPUStateProbe()
     * @generated
     */
	int CPU_STATE_PROBE = 15;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CPU_STATE_PROBE__ID = PROBE__ID;

	/**
     * The number of structural features of the '<em>CPU State Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CPU_STATE_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.HDDStateProbeImpl <em>HDD State Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.HDDStateProbeImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getHDDStateProbe()
     * @generated
     */
	int HDD_STATE_PROBE = 16;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HDD_STATE_PROBE__ID = PROBE__ID;

	/**
     * The number of structural features of the '<em>HDD State Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HDD_STATE_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.CPUDemandProbeImpl <em>CPU Demand Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.CPUDemandProbeImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getCPUDemandProbe()
     * @generated
     */
	int CPU_DEMAND_PROBE = 17;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CPU_DEMAND_PROBE__ID = PROBE__ID;

	/**
     * The number of structural features of the '<em>CPU Demand Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CPU_DEMAND_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.HDDDemandProbeImpl <em>HDD Demand Probe</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.HDDDemandProbeImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getHDDDemandProbe()
     * @generated
     */
	int HDD_DEMAND_PROBE = 18;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HDD_DEMAND_PROBE__ID = PROBE__ID;

	/**
     * The number of structural features of the '<em>HDD Demand Probe</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HDD_DEMAND_PROBE_FEATURE_COUNT = PROBE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.HDDStateCalculatorImpl <em>HDD State Calculator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.HDDStateCalculatorImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getHDDStateCalculator()
     * @generated
     */
	int HDD_STATE_CALCULATOR = 19;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HDD_STATE_CALCULATOR__ID = UNARY_CALCULATOR__ID;

	/**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HDD_STATE_CALCULATOR__ACTIVE = UNARY_CALCULATOR__ACTIVE;

	/**
     * The feature id for the '<em><b>Probe Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HDD_STATE_CALCULATOR__PROBE_SET = UNARY_CALCULATOR__PROBE_SET;

	/**
     * The number of structural features of the '<em>HDD State Calculator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HDD_STATE_CALCULATOR_FEATURE_COUNT = UNARY_CALCULATOR_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.CPUStateCalculatorImpl <em>CPU State Calculator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.CPUStateCalculatorImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getCPUStateCalculator()
     * @generated
     */
	int CPU_STATE_CALCULATOR = 20;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CPU_STATE_CALCULATOR__ID = UNARY_CALCULATOR__ID;

	/**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CPU_STATE_CALCULATOR__ACTIVE = UNARY_CALCULATOR__ACTIVE;

	/**
     * The feature id for the '<em><b>Probe Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CPU_STATE_CALCULATOR__PROBE_SET = UNARY_CALCULATOR__PROBE_SET;

	/**
     * The number of structural features of the '<em>CPU State Calculator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CPU_STATE_CALCULATOR_FEATURE_COUNT = UNARY_CALCULATOR_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.CPUDemandCalculatorImpl <em>CPU Demand Calculator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.CPUDemandCalculatorImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getCPUDemandCalculator()
     * @generated
     */
	int CPU_DEMAND_CALCULATOR = 21;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CPU_DEMAND_CALCULATOR__ID = UNARY_CALCULATOR__ID;

	/**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CPU_DEMAND_CALCULATOR__ACTIVE = UNARY_CALCULATOR__ACTIVE;

	/**
     * The feature id for the '<em><b>Probe Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CPU_DEMAND_CALCULATOR__PROBE_SET = UNARY_CALCULATOR__PROBE_SET;

	/**
     * The number of structural features of the '<em>CPU Demand Calculator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CPU_DEMAND_CALCULATOR_FEATURE_COUNT = UNARY_CALCULATOR_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.impl.HDDDemandCalculatorImpl <em>HDD Demand Calculator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.impl.HDDDemandCalculatorImpl
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getHDDDemandCalculator()
     * @generated
     */
	int HDD_DEMAND_CALCULATOR = 22;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HDD_DEMAND_CALCULATOR__ID = UNARY_CALCULATOR__ID;

	/**
     * The feature id for the '<em><b>Active</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HDD_DEMAND_CALCULATOR__ACTIVE = UNARY_CALCULATOR__ACTIVE;

	/**
     * The feature id for the '<em><b>Probe Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HDD_DEMAND_CALCULATOR__PROBE_SET = UNARY_CALCULATOR__PROBE_SET;

	/**
     * The number of structural features of the '<em>HDD Demand Calculator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HDD_DEMAND_CALCULATOR_FEATURE_COUNT = UNARY_CALCULATOR_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.palladiosimulator.probespec.ProbeSetPosition <em>Probe Set Position</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.probespec.ProbeSetPosition
     * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getProbeSetPosition()
     * @generated
     */
	int PROBE_SET_POSITION = 23;


	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.Calculator <em>Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Calculator</em>'.
     * @see org.palladiosimulator.probespec.Calculator
     * @generated
     */
	EClass getCalculator();

	/**
     * Returns the meta object for the attribute '{@link org.palladiosimulator.probespec.Calculator#isActive <em>Active</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Active</em>'.
     * @see org.palladiosimulator.probespec.Calculator#isActive()
     * @see #getCalculator()
     * @generated
     */
	EAttribute getCalculator_Active();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.PassiveResourceCalculator <em>Passive Resource Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Passive Resource Calculator</em>'.
     * @see org.palladiosimulator.probespec.PassiveResourceCalculator
     * @generated
     */
	EClass getPassiveResourceCalculator();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.UnaryCalculator <em>Unary Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Unary Calculator</em>'.
     * @see org.palladiosimulator.probespec.UnaryCalculator
     * @generated
     */
	EClass getUnaryCalculator();

	/**
     * Returns the meta object for the containment reference '{@link org.palladiosimulator.probespec.UnaryCalculator#getProbeSet <em>Probe Set</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Probe Set</em>'.
     * @see org.palladiosimulator.probespec.UnaryCalculator#getProbeSet()
     * @see #getUnaryCalculator()
     * @generated
     */
	EReference getUnaryCalculator_ProbeSet();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.ProbeSet <em>Probe Set</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Probe Set</em>'.
     * @see org.palladiosimulator.probespec.ProbeSet
     * @generated
     */
	EClass getProbeSet();

	/**
     * Returns the meta object for the containment reference list '{@link org.palladiosimulator.probespec.ProbeSet#getProbes <em>Probes</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Probes</em>'.
     * @see org.palladiosimulator.probespec.ProbeSet#getProbes()
     * @see #getProbeSet()
     * @generated
     */
	EReference getProbeSet_Probes();

	/**
     * Returns the meta object for the attribute '{@link org.palladiosimulator.probespec.ProbeSet#getPosition <em>Position</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Position</em>'.
     * @see org.palladiosimulator.probespec.ProbeSet#getPosition()
     * @see #getProbeSet()
     * @generated
     */
	EAttribute getProbeSet_Position();

	/**
     * Returns the meta object for the reference '{@link org.palladiosimulator.probespec.ProbeSet#getAnnotatedElement <em>Annotated Element</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Annotated Element</em>'.
     * @see org.palladiosimulator.probespec.ProbeSet#getAnnotatedElement()
     * @see #getProbeSet()
     * @generated
     */
	EReference getProbeSet_AnnotatedElement();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.Probe <em>Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Probe</em>'.
     * @see org.palladiosimulator.probespec.Probe
     * @generated
     */
	EClass getProbe();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.PassiveResourceStateProbe <em>Passive Resource State Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Passive Resource State Probe</em>'.
     * @see org.palladiosimulator.probespec.PassiveResourceStateProbe
     * @generated
     */
	EClass getPassiveResourceStateProbe();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.StoExProbe <em>Sto Ex Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Sto Ex Probe</em>'.
     * @see org.palladiosimulator.probespec.StoExProbe
     * @generated
     */
	EClass getStoExProbe();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.SEFFParameterProbe <em>SEFF Parameter Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>SEFF Parameter Probe</em>'.
     * @see org.palladiosimulator.probespec.SEFFParameterProbe
     * @generated
     */
	EClass getSEFFParameterProbe();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.CurrentTimeProbe <em>Current Time Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Current Time Probe</em>'.
     * @see org.palladiosimulator.probespec.CurrentTimeProbe
     * @generated
     */
	EClass getCurrentTimeProbe();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.StoExCalculator <em>Sto Ex Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Sto Ex Calculator</em>'.
     * @see org.palladiosimulator.probespec.StoExCalculator
     * @generated
     */
	EClass getStoExCalculator();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.SEFFParameterCalculator <em>SEFF Parameter Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>SEFF Parameter Calculator</em>'.
     * @see org.palladiosimulator.probespec.SEFFParameterCalculator
     * @generated
     */
	EClass getSEFFParameterCalculator();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.ResponseTimeCalculator <em>Response Time Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Response Time Calculator</em>'.
     * @see org.palladiosimulator.probespec.ResponseTimeCalculator
     * @generated
     */
	EClass getResponseTimeCalculator();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.BinaryCalculator <em>Binary Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Binary Calculator</em>'.
     * @see org.palladiosimulator.probespec.BinaryCalculator
     * @generated
     */
	EClass getBinaryCalculator();

	/**
     * Returns the meta object for the containment reference list '{@link org.palladiosimulator.probespec.BinaryCalculator#getProbeSet <em>Probe Set</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Probe Set</em>'.
     * @see org.palladiosimulator.probespec.BinaryCalculator#getProbeSet()
     * @see #getBinaryCalculator()
     * @generated
     */
	EReference getBinaryCalculator_ProbeSet();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.WaitingTimeCalculator <em>Waiting Time Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Waiting Time Calculator</em>'.
     * @see org.palladiosimulator.probespec.WaitingTimeCalculator
     * @generated
     */
	EClass getWaitingTimeCalculator();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.ProbeSpecRepository <em>Probe Spec Repository</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Probe Spec Repository</em>'.
     * @see org.palladiosimulator.probespec.ProbeSpecRepository
     * @generated
     */
	EClass getProbeSpecRepository();

	/**
     * Returns the meta object for the containment reference list '{@link org.palladiosimulator.probespec.ProbeSpecRepository#getCalculator <em>Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Calculator</em>'.
     * @see org.palladiosimulator.probespec.ProbeSpecRepository#getCalculator()
     * @see #getProbeSpecRepository()
     * @generated
     */
	EReference getProbeSpecRepository_Calculator();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.CPUStateProbe <em>CPU State Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>CPU State Probe</em>'.
     * @see org.palladiosimulator.probespec.CPUStateProbe
     * @generated
     */
	EClass getCPUStateProbe();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.HDDStateProbe <em>HDD State Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>HDD State Probe</em>'.
     * @see org.palladiosimulator.probespec.HDDStateProbe
     * @generated
     */
	EClass getHDDStateProbe();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.CPUDemandProbe <em>CPU Demand Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>CPU Demand Probe</em>'.
     * @see org.palladiosimulator.probespec.CPUDemandProbe
     * @generated
     */
	EClass getCPUDemandProbe();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.HDDDemandProbe <em>HDD Demand Probe</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>HDD Demand Probe</em>'.
     * @see org.palladiosimulator.probespec.HDDDemandProbe
     * @generated
     */
	EClass getHDDDemandProbe();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.HDDStateCalculator <em>HDD State Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>HDD State Calculator</em>'.
     * @see org.palladiosimulator.probespec.HDDStateCalculator
     * @generated
     */
	EClass getHDDStateCalculator();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.CPUStateCalculator <em>CPU State Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>CPU State Calculator</em>'.
     * @see org.palladiosimulator.probespec.CPUStateCalculator
     * @generated
     */
	EClass getCPUStateCalculator();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.CPUDemandCalculator <em>CPU Demand Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>CPU Demand Calculator</em>'.
     * @see org.palladiosimulator.probespec.CPUDemandCalculator
     * @generated
     */
	EClass getCPUDemandCalculator();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.probespec.HDDDemandCalculator <em>HDD Demand Calculator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>HDD Demand Calculator</em>'.
     * @see org.palladiosimulator.probespec.HDDDemandCalculator
     * @generated
     */
	EClass getHDDDemandCalculator();

	/**
     * Returns the meta object for enum '{@link org.palladiosimulator.probespec.ProbeSetPosition <em>Probe Set Position</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Probe Set Position</em>'.
     * @see org.palladiosimulator.probespec.ProbeSetPosition
     * @generated
     */
	EEnum getProbeSetPosition();

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
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.CalculatorImpl <em>Calculator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.CalculatorImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getCalculator()
         * @generated
         */
		EClass CALCULATOR = eINSTANCE.getCalculator();

		/**
         * The meta object literal for the '<em><b>Active</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CALCULATOR__ACTIVE = eINSTANCE.getCalculator_Active();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.PassiveResourceCalculatorImpl <em>Passive Resource Calculator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.PassiveResourceCalculatorImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getPassiveResourceCalculator()
         * @generated
         */
		EClass PASSIVE_RESOURCE_CALCULATOR = eINSTANCE.getPassiveResourceCalculator();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.UnaryCalculatorImpl <em>Unary Calculator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.UnaryCalculatorImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getUnaryCalculator()
         * @generated
         */
		EClass UNARY_CALCULATOR = eINSTANCE.getUnaryCalculator();

		/**
         * The meta object literal for the '<em><b>Probe Set</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference UNARY_CALCULATOR__PROBE_SET = eINSTANCE.getUnaryCalculator_ProbeSet();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.ProbeSetImpl <em>Probe Set</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.ProbeSetImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getProbeSet()
         * @generated
         */
		EClass PROBE_SET = eINSTANCE.getProbeSet();

		/**
         * The meta object literal for the '<em><b>Probes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PROBE_SET__PROBES = eINSTANCE.getProbeSet_Probes();

		/**
         * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PROBE_SET__POSITION = eINSTANCE.getProbeSet_Position();

		/**
         * The meta object literal for the '<em><b>Annotated Element</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PROBE_SET__ANNOTATED_ELEMENT = eINSTANCE.getProbeSet_AnnotatedElement();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.ProbeImpl <em>Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.ProbeImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getProbe()
         * @generated
         */
		EClass PROBE = eINSTANCE.getProbe();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.PassiveResourceStateProbeImpl <em>Passive Resource State Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.PassiveResourceStateProbeImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getPassiveResourceStateProbe()
         * @generated
         */
		EClass PASSIVE_RESOURCE_STATE_PROBE = eINSTANCE.getPassiveResourceStateProbe();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.StoExProbeImpl <em>Sto Ex Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.StoExProbeImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getStoExProbe()
         * @generated
         */
		EClass STO_EX_PROBE = eINSTANCE.getStoExProbe();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.SEFFParameterProbeImpl <em>SEFF Parameter Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.SEFFParameterProbeImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getSEFFParameterProbe()
         * @generated
         */
		EClass SEFF_PARAMETER_PROBE = eINSTANCE.getSEFFParameterProbe();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.CurrentTimeProbeImpl <em>Current Time Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.CurrentTimeProbeImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getCurrentTimeProbe()
         * @generated
         */
		EClass CURRENT_TIME_PROBE = eINSTANCE.getCurrentTimeProbe();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.StoExCalculatorImpl <em>Sto Ex Calculator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.StoExCalculatorImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getStoExCalculator()
         * @generated
         */
		EClass STO_EX_CALCULATOR = eINSTANCE.getStoExCalculator();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.SEFFParameterCalculatorImpl <em>SEFF Parameter Calculator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.SEFFParameterCalculatorImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getSEFFParameterCalculator()
         * @generated
         */
		EClass SEFF_PARAMETER_CALCULATOR = eINSTANCE.getSEFFParameterCalculator();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.ResponseTimeCalculatorImpl <em>Response Time Calculator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.ResponseTimeCalculatorImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getResponseTimeCalculator()
         * @generated
         */
		EClass RESPONSE_TIME_CALCULATOR = eINSTANCE.getResponseTimeCalculator();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.BinaryCalculatorImpl <em>Binary Calculator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.BinaryCalculatorImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getBinaryCalculator()
         * @generated
         */
		EClass BINARY_CALCULATOR = eINSTANCE.getBinaryCalculator();

		/**
         * The meta object literal for the '<em><b>Probe Set</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference BINARY_CALCULATOR__PROBE_SET = eINSTANCE.getBinaryCalculator_ProbeSet();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.WaitingTimeCalculatorImpl <em>Waiting Time Calculator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.WaitingTimeCalculatorImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getWaitingTimeCalculator()
         * @generated
         */
		EClass WAITING_TIME_CALCULATOR = eINSTANCE.getWaitingTimeCalculator();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.ProbeSpecRepositoryImpl <em>Probe Spec Repository</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.ProbeSpecRepositoryImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getProbeSpecRepository()
         * @generated
         */
		EClass PROBE_SPEC_REPOSITORY = eINSTANCE.getProbeSpecRepository();

		/**
         * The meta object literal for the '<em><b>Calculator</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PROBE_SPEC_REPOSITORY__CALCULATOR = eINSTANCE.getProbeSpecRepository_Calculator();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.CPUStateProbeImpl <em>CPU State Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.CPUStateProbeImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getCPUStateProbe()
         * @generated
         */
		EClass CPU_STATE_PROBE = eINSTANCE.getCPUStateProbe();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.HDDStateProbeImpl <em>HDD State Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.HDDStateProbeImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getHDDStateProbe()
         * @generated
         */
		EClass HDD_STATE_PROBE = eINSTANCE.getHDDStateProbe();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.CPUDemandProbeImpl <em>CPU Demand Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.CPUDemandProbeImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getCPUDemandProbe()
         * @generated
         */
		EClass CPU_DEMAND_PROBE = eINSTANCE.getCPUDemandProbe();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.HDDDemandProbeImpl <em>HDD Demand Probe</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.HDDDemandProbeImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getHDDDemandProbe()
         * @generated
         */
		EClass HDD_DEMAND_PROBE = eINSTANCE.getHDDDemandProbe();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.HDDStateCalculatorImpl <em>HDD State Calculator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.HDDStateCalculatorImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getHDDStateCalculator()
         * @generated
         */
		EClass HDD_STATE_CALCULATOR = eINSTANCE.getHDDStateCalculator();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.CPUStateCalculatorImpl <em>CPU State Calculator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.CPUStateCalculatorImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getCPUStateCalculator()
         * @generated
         */
		EClass CPU_STATE_CALCULATOR = eINSTANCE.getCPUStateCalculator();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.CPUDemandCalculatorImpl <em>CPU Demand Calculator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.CPUDemandCalculatorImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getCPUDemandCalculator()
         * @generated
         */
		EClass CPU_DEMAND_CALCULATOR = eINSTANCE.getCPUDemandCalculator();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.impl.HDDDemandCalculatorImpl <em>HDD Demand Calculator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.impl.HDDDemandCalculatorImpl
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getHDDDemandCalculator()
         * @generated
         */
		EClass HDD_DEMAND_CALCULATOR = eINSTANCE.getHDDDemandCalculator();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.probespec.ProbeSetPosition <em>Probe Set Position</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.probespec.ProbeSetPosition
         * @see org.palladiosimulator.probespec.impl.probespecPackageImpl#getProbeSetPosition()
         * @generated
         */
		EEnum PROBE_SET_POSITION = eINSTANCE.getProbeSetPosition();

	}

} //probespecPackage
