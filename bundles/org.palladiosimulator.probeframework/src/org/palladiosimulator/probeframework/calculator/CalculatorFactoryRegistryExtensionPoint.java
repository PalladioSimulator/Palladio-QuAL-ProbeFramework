package org.palladiosimulator.probeframework.calculator;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;

/**
 * This registry allows to provide specialized calculator factories for metrics
 * using the eclipse extension point
 * <code>org.palladiosimulator.probeframework.calculator.factories</code>
 * 
 * @author Sebastian Krach
 *
 */
public enum CalculatorFactoryRegistryExtensionPoint {

    /** The singleton instance of the calculator factory registry */
    INSTANCE;

    public static final String METRIC_ATTRIBUTE = "metricId";
    public static final String FACTORY_ATTRIBUTE = "factoryClass";
    public static final String EXTENSION_POINT_ID = "org.palladiosimulator.probeframework.calculator.factories";
    private static final Logger LOGGER = Logger.getLogger(CalculatorFactoryRegistryExtensionPoint.class);

    /**
     * Gets the calculator factories which are currently registered.
     * 
     * @return a map of metric ids to appropriate calculator factories.
     */
    public Map<String, IGenericCalculatorFactory> getCalculatorFactories() {
        var result = new HashMap<String, IGenericCalculatorFactory>();
        for (var config : Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID)) {
            var metricId = config.getAttribute(METRIC_ATTRIBUTE);
            if (result.containsKey(metricId)) {
                LOGGER.error(String.format(
                        "Duplicate calculator factory registered for metric id %s. Ignoring the one provided by: %s",
                        metricId, config.getContributor().getName()));
            } else {
                try {
                    var fact = config.createExecutableExtension(FACTORY_ATTRIBUTE);
                    if (fact instanceof IGenericCalculatorFactory) {
                        result.put(metricId, (IGenericCalculatorFactory) fact);
                    } else {
                        LOGGER.error(String.format(
                                "Calculator registered by %s for metric id %s does not conform to type %s",
                                config.getContributor().getName(), metricId,
                                IGenericCalculatorFactory.class.getName()));
                    }
                } catch (CoreException e) {
                    LOGGER.error(String.format("Failed to instantiate the factory registered by %s for metric id %s",
                            config.getContributor().getName(), metricId));
                }
            }
        }
        return result;
    }
}
