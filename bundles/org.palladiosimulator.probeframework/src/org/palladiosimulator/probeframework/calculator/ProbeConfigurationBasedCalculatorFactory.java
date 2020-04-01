package org.palladiosimulator.probeframework.calculator;

import java.util.Arrays;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.calculator.internal.IdentityCalculator;
import org.palladiosimulator.probeframework.calculator.internal.TimeSpanCalculator;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.probeframework.probes.ProbeConfiguration;
import org.palladiosimulator.probeframework.probes.ProbeConfigurations;

/**
 * This factory is not supposed to be used directly, but as a fallback
 * implementation in case no specialized calculator factory is available. It
 * uses information about the available probes to select the appropriate
 * calculator.
 * 
 * This factory uses a simple heuristic: - if a single probe is provided, an
 * <code>IdentityCalculator<code> for the respective metric is created.
 *   - is there is a start and stop probe, a <code>TimeSpanCalculator</code> is
 * created.
 * 
 * 
 * @author Sebastian Krach
 *
 */
public class ProbeConfigurationBasedCalculatorFactory implements IGenericCalculatorFactory {

	@Override
	public Calculator buildCalculator(MetricDescription metric, MeasuringPoint measuringPoint,
			ProbeConfiguration probeConfiguration) {
		if (probeConfiguration.getProbe(ProbeConfigurations.SINGULAR_PROBE).isPresent()) {
			return new IdentityCalculator(metric, measuringPoint,
					probeConfiguration.getProbe(ProbeConfigurations.SINGULAR_PROBE).get());
		} else if (probeConfiguration.getProbe(ProbeConfigurations.START_PROBE).isPresent()
				&& probeConfiguration.getProbe(ProbeConfigurations.STOP_PROBE).isPresent()) {
			return new TimeSpanCalculator(metric, measuringPoint,
					Arrays.asList(new Probe[] { probeConfiguration.getProbe(ProbeConfigurations.START_PROBE).get(),
							probeConfiguration.getProbe(ProbeConfigurations.STOP_PROBE).get() }));
		}
		throw new IllegalArgumentException(String.format(
				"The %s is not compatible with metrics of type %s. If your metric deviates "
						+ "from the standard, make sure to provide a suitable calculator factory.",
				getClass().getName(), metric.getName()));
	}

}
