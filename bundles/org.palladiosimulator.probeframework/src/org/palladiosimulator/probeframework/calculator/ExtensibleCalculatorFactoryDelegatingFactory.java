package org.palladiosimulator.probeframework.calculator;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.probes.ProbeConfiguration;

/**
 * This factory allows to use specialized factories for Calculators of dedicated
 * metrics. Calculator factories can be registered based on the id of the
 * metric. In case no dedicated factory is registered, a fallback factory can be
 * provided.
 * 
 * By default the factory uses the <code>CalculatorFactorRegistry</code> to look
 * up specialized factories and uses
 * <code>ProbeConfigurationBasedCalculatorFactory</code> as fallback. Therefore,
 * without providing additional registrations, the factory provides support for
 * either probe measurements which are passed through directly, and time span
 * measurements.
 * 
 * @author Sebastian Krach
 *
 */
public class ExtensibleCalculatorFactoryDelegatingFactory implements IGenericCalculatorFactory {
	private final Map<String, IGenericCalculatorFactory> delegateFactories;
	private final IGenericCalculatorFactory fallbackFactory;

	/**
	 * Creates a new instance without any specialized calculators and a probe
	 * configuration based heuristic for identity and time span calculators.
	 */
	public ExtensibleCalculatorFactoryDelegatingFactory() {
		this(Collections.emptyMap(), new ProbeConfigurationBasedCalculatorFactory());
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
			ProbeConfiguration probeConfiguration) {
		return delegateFactories.getOrDefault(metric.getId(), fallbackFactory).buildCalculator(metric, measuringPoint,
				probeConfiguration);
	}

}
