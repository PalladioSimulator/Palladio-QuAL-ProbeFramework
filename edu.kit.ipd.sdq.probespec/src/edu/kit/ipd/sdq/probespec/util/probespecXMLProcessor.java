/**
 */
package edu.kit.ipd.sdq.probespec.util;

import edu.kit.ipd.sdq.probespec.probespecPackage;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class probespecXMLProcessor extends XMLProcessor {

	/**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public probespecXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        probespecPackage.eINSTANCE.eClass();
    }
	
	/**
     * Register for "*" and "xml" file extensions the probespecResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new probespecResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new probespecResourceFactoryImpl());
        }
        return registrations;
    }

} //probespecXMLProcessor