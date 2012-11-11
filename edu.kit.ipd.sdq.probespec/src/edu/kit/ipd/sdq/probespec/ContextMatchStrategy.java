/**
 */
package edu.kit.ipd.sdq.probespec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Context Match Strategy</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see edu.kit.ipd.sdq.probespec.probespecPackage#getContextMatchStrategy()
 * @model
 * @generated
 */
public enum ContextMatchStrategy implements Enumerator {
    /**
     * The '<em><b>All Contexts</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ALL_CONTEXTS_VALUE
     * @generated
     * @ordered
     */
    ALL_CONTEXTS(0, "allContexts", "allContexts"),

    /**
     * The '<em><b>Same Context</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SAME_CONTEXT_VALUE
     * @generated
     * @ordered
     */
    SAME_CONTEXT(1, "sameContext", "sameContext"),

    /**
     * The '<em><b>Same Or Parent Context</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SAME_OR_PARENT_CONTEXT_VALUE
     * @generated
     * @ordered
     */
    SAME_OR_PARENT_CONTEXT(2, "sameOrParentContext", "sameOrParentContext");

    /**
     * The '<em><b>All Contexts</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>All Contexts</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ALL_CONTEXTS
     * @model name="allContexts"
     * @generated
     * @ordered
     */
    public static final int ALL_CONTEXTS_VALUE = 0;

    /**
     * The '<em><b>Same Context</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Same Context</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SAME_CONTEXT
     * @model name="sameContext"
     * @generated
     * @ordered
     */
    public static final int SAME_CONTEXT_VALUE = 1;

    /**
     * The '<em><b>Same Or Parent Context</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Same Or Parent Context</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SAME_OR_PARENT_CONTEXT
     * @model name="sameOrParentContext"
     * @generated
     * @ordered
     */
    public static final int SAME_OR_PARENT_CONTEXT_VALUE = 2;

    /**
     * An array of all the '<em><b>Context Match Strategy</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ContextMatchStrategy[] VALUES_ARRAY =
        new ContextMatchStrategy[] {
            ALL_CONTEXTS,
            SAME_CONTEXT,
            SAME_OR_PARENT_CONTEXT,
        };

    /**
     * A public read-only list of all the '<em><b>Context Match Strategy</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<ContextMatchStrategy> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Context Match Strategy</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ContextMatchStrategy get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ContextMatchStrategy result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Context Match Strategy</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ContextMatchStrategy getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ContextMatchStrategy result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Context Match Strategy</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ContextMatchStrategy get(int value) {
        switch (value) {
            case ALL_CONTEXTS_VALUE: return ALL_CONTEXTS;
            case SAME_CONTEXT_VALUE: return SAME_CONTEXT;
            case SAME_OR_PARENT_CONTEXT_VALUE: return SAME_OR_PARENT_CONTEXT;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private ContextMatchStrategy(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getValue() {
      return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
      return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral() {
      return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }
    
} //ContextMatchStrategy
