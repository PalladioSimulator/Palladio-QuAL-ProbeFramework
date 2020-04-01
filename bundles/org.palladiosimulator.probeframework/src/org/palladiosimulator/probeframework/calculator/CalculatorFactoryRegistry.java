package org.palladiosimulator.probeframework.calculator;

import java.util.Collections;
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
public enum CalculatorFactoryRegistry {

    /** The singleton instance of the calculator factory registry */
    INSTANCE;

    /**
     * Gets the calculator factories which are currently registered.
     * 
     * @return a map of metric ids to appropriate calculator factories.
     */
    public Map<String, IGenericCalculatorFactory> getCalculatorFactories() {
        if (!Platform.isRunning())
            return Collections.emptyMap();

        var result = new HashMap<String, IGenericCalculatorFactory>();
        for (var config : Platform.getExtensionRegistry()
                .getConfigurationElementsFor("org.palladiosimulator.probeframework.calculator.factories")) {
            var metricId = config.getAttribute("metricId");
            if (result.containsKey(config.getAttribute("metricId"))) {
                Logger.getLogger(getClass()).error(String.format(
                        "Duplicate calculator factory registered for metric id %s. Ignoring the one provided by: %s",
                        metricId, config.getContributor().getName()));
            } else {
                try {
                    var fact = config.createExecutableExtension("factoryClass");
                    if (fact instanceof IGenericCalculatorFactory) {
                        result.put(metricId, (IGenericCalculatorFactory) fact);
                    } else {
                        Logger.getLogger(getClass())
                                .error(String.format(
                                        "Calculator registered by %s for metric id %s does not conform to type %s",
                                        config.getContributor().getName(), metricId,
                                        IGenericCalculatorFactory.class.getName()));
                    }
                } catch (CoreException e) {
                    Logger.getLogger(getClass())
                            .error(String.format("Failed to instantiate the factory registered by %s for metric id %s",
                                    config.getContributor().getName(), metricId));
                }
            }
        }
        return result;
    }

}
