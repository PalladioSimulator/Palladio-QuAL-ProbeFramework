package org.palladiosimulator.probeframework.calculator;

import java.util.Arrays;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.calculator.internal.IdentityCalculator;
import org.palladiosimulator.probeframework.calculator.internal.TimeSpanCalculator;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * This factory is not supposed to be used directly, but as a fallback
 * implementation in case no specialized calculator factory is available. It
 * uses information about the available probes to select the appropriate
 * calculator.
 * 
 * This factory uses a simple heuristic:
 * <ul>
 *   <li> if a single probe is provided, an <code>IdentityCalculator<code> 
 * 	      for the respective metric is created.</li>
 *   <li> if there is a start and stop probe, a <code>TimeSpanCalculator</code>
 *        is created.</li>
 * </ul>
 * 
 * 
 * @author Sebastian Krach
 *
 */
public class CalculatorProbeSetBasedInferingCalculatorFactory implements IGenericCalculatorFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Calculator buildCalculator(MetricDescription metric, MeasuringPoint measuringPoint,
			CalculatorProbeSet probeConfiguration) {
		if (probeConfiguration.getProbe(DefaultCalculatorProbeSets.SINGULAR_PROBE).isPresent()) {
			return new IdentityCalculator(metric, measuringPoint,
					probeConfiguration.getProbe(DefaultCalculatorProbeSets.SINGULAR_PROBE).get());
		} else if (probeConfiguration.getProbe(DefaultCalculatorProbeSets.START_PROBE).isPresent()
				&& probeConfiguration.getProbe(DefaultCalculatorProbeSets.STOP_PROBE).isPresent()) {
			return new TimeSpanCalculator(metric, measuringPoint,
					Arrays.asList(
							new Probe[] { probeConfiguration.getProbe(DefaultCalculatorProbeSets.START_PROBE).get(),
									probeConfiguration.getProbe(DefaultCalculatorProbeSets.STOP_PROBE).get() }));
		}
		throw new IllegalArgumentException(String.format(
				"The %s is not compatible with metrics of type %s. If your metric deviates "
						+ "from the standard, make sure to provide a suitable calculator factory.",
				getClass().getName(), metric.getName()));
	}

}
