package org.palladiosimulator.probeframework.calculator;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.eclipse.core.runtime.Platform;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;

/**
 * This factory allows to use specialized factories for Calculators of dedicated
 * metrics. Calculator factories can be registered based on the id of the
 * metric. In case no dedicated factory is registered, a fallback factory can be
 * provided.
 * 
 * By default the factory uses the
 * <code>CalculatorFactoryRegistryExtensionPoint</code> to look up specialized
 * factories and uses <code>ProbeConfigurationBasedCalculatorFactory</code> as
 * fallback. Therefore, without providing additional registrations, the factory
 * provides support for either probe measurements which are passed through
 * directly, and time span measurements.
 * 
 * @author Sebastian Krach
 *
 */
public class ExtensibleCalculatorFactoryDelegatingFactory implements IGenericCalculatorFactory {
    private final Map<String, IGenericCalculatorFactory> delegateFactories;
    private final IGenericCalculatorFactory fallbackFactory;

    /**
     * Creates a new instance using the eclipse extension point (if available) and a
     * calculator probe set based heuristic for identity and time span calculators.
     */
    public ExtensibleCalculatorFactoryDelegatingFactory() {
        this(Platform.isRunning() ? CalculatorFactoryRegistryExtensionPoint.INSTANCE.getCalculatorFactories()
                : Collections.emptyMap(), new CalculatorProbeSetBasedInferingCalculatorFactory());
    }

    /**
     * Creates a new instance with the provided specializations and the fallback
     * factory.
     * 
     * @param delegateFactories a map of metric id to specialized factory.
     * @param fallbackFactory   the factory which is called if there is no
     *                          specialized factory.
     */
    public ExtensibleCalculatorFactoryDelegatingFactory(final Map<String, IGenericCalculatorFactory> delegateFactories,
            IGenericCalculatorFactory fallbackFactory) {
        this.delegateFactories = Collections.unmodifiableMap(Objects.requireNonNull(delegateFactories));
        this.fallbackFactory = Objects.requireNonNull(fallbackFactory);
    }

    /**
     * { {@inheritDoc} }
     */
    @Override
    public Calculator buildCalculator(MetricDescription metric, MeasuringPoint measuringPoint,
            CalculatorProbeSet probeConfiguration) {
        return delegateFactories.getOrDefault(metric.getId(), fallbackFactory).buildCalculator(metric, measuringPoint,
                probeConfiguration);
    }

}
