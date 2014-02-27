package de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

import de.uka.ipd.sdq.pipesandfilters.framework.IMetaDataInitFactory;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.IRawWriteStrategy;

public class RecorderExtensionHelper {

	public static String[] getRecorderNames() throws CoreException {
		List<IExtension> recorderExtensions = loadExtensions("de.uka.ipd.sdq.pipesandfilters.framework.recorder");
		List<String> names = new ArrayList<String>();
		for (IExtension extension : recorderExtensions) {
			IConfigurationElement e = obtainConfigurationElement("recorder",
					extension);
			if (e != null) {
				names.add(e.getAttribute("name"));
			}
		}
		return names.toArray(new String[names.size()]);
	}

	public static ILaunchConfigurationTab[] getLaunchConfigTabs()
			throws CoreException {
		List<IExtension> recorderExtensions = loadExtensions("de.uka.ipd.sdq.pipesandfilters.framework.recorder");
		List<ILaunchConfigurationTab> tabList = new ArrayList<ILaunchConfigurationTab>();
		for (IExtension extension : recorderExtensions) {
			IConfigurationElement e = obtainConfigurationElement("recorder",
					extension);
			if (e != null) {
				tabList.add((ILaunchConfigurationTab) e
						.createExecutableExtension("launchConfigTab"));
			}
		}
		return tabList.toArray(new ILaunchConfigurationTab[tabList.size()]);
	}

	public static String getExtensionIdentifierForName(String recorderName)
			throws CoreException {
		List<IExtension> recorderExtensions = loadExtensions("de.uka.ipd.sdq.pipesandfilters.framework.recorder");
		for (IExtension extension : recorderExtensions) {
			IConfigurationElement e = obtainConfigurationElement("recorder",
					extension);
			if (e != null && e.getAttribute("name").equals(recorderName)) {
				return extension.getUniqueIdentifier();
			}
		}
		return null;
	}

	public static IRecorderConfiguration getRecorderConfigForName(
			String recorderName) throws CoreException {
		List<IExtension> recorderExtensions = loadExtensions("de.uka.ipd.sdq.pipesandfilters.framework.recorder");
		for (IExtension extension : recorderExtensions) {
			IConfigurationElement e = obtainConfigurationElement("recorder",
					extension);
			if (e != null && e.getAttribute("name").equals(recorderName)) {
				Object config = e.createExecutableExtension("configuration");
				if (config != null) {
					return (IRecorderConfiguration) config;
				}
			}
		}
		return null;
	}

    public static IRawWriteStrategy instantiateWriteStrategyForRecorder(String recorderName) {
        try {
            return (IRawWriteStrategy) instantiateExecutableExtension(recorderName, "writeStrategy");
        } catch (CoreException e) {
            throw new RuntimeException("Could not instantiate write strategy for recorder named " + recorderName);
        }
    }
	
    public static IMetaDataInitFactory instantiateMetaDataInitFactoryForRecorder(String recorderName) {
        try {
            return (IMetaDataInitFactory) instantiateExecutableExtension(recorderName, "metaDataInitFactory");
        } catch (CoreException e) {
            throw new RuntimeException("Could not instantiate MetaDataInit factory for recorder named " + recorderName);
        }
    }
	
	private static Object instantiateExecutableExtension(String recorderName, String attributeName)
	        throws CoreException {
	    List<IExtension> recorderExtensions = loadExtensions("de.uka.ipd.sdq.pipesandfilters.framework.recorder");
        for (IExtension extension : recorderExtensions) {            
            IConfigurationElement e = obtainConfigurationElement("recorder",
                    extension);            
            if (e != null && e.getAttribute("name").equals(recorderName)) {
                return e.createExecutableExtension(attributeName);
            }
        }
        return null;
	}

	public static String getNameForExtensionIdentifier(String extensionID)
			throws CoreException {
		IExtension ext = Platform.getExtensionRegistry().getExtensionPoint(
				"de.uka.ipd.sdq.pipesandfilters.framework.recorder")
				.getExtension(extensionID);
		if (ext != null) {
			IConfigurationElement e = obtainConfigurationElement("recorder",
					ext);
			return e.getAttribute("name");
		}
		return null;
	}

	private static IConfigurationElement obtainConfigurationElement(
			String elementName, IExtension extension) throws CoreException {
		IConfigurationElement[] elements = extension.getConfigurationElements();
		for (IConfigurationElement element : elements) {
			if (element.getName().equals(elementName)) {
				return element;
			}
		}
		return null;
	}

	private static List<IExtension> loadExtensions(String extensionPointID) {
		IExtension[] exts = Platform.getExtensionRegistry().getExtensionPoint(
				extensionPointID).getExtensions();
		List<IExtension> results = new ArrayList<IExtension>();
		for (IExtension extension : exts) {
			results.add(extension);
		}
		return results;
	}

}
