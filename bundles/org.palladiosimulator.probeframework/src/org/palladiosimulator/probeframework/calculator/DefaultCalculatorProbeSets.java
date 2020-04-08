package org.palladiosimulator.probeframework.calculator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.palladiosimulator.probeframework.probes.Probe;

/**
 * This class contains factory methods and identifier for the
 * <code>CalculatorProbeSets</code> which are supported by the standard
 * calculators of the probe framework.
 * 
 * This class is by design not an enum as extensions which require a custom
 * probe set should implement a similar ProbeSet helper class for and as part of
 * their specific extension.
 * 
 * @author Sebastian Krach
 *
 */
public final class DefaultCalculatorProbeSets {
	/** The sole probe contained in a configuration */
	public static final String SINGULAR_PROBE = "org.palladiosimulator.probeframework.probes.singular";

	/** The start probe of a StartStopProbeConfiguration */
	public static final String START_PROBE = "org.palladiosimulator.probeframework.probes.start";

	/** The stop probe of a StartStopProbeConfiguration */
	public static final String STOP_PROBE = "org.palladiosimulator.probeframework.probes.stop";

	/**
	 * Returns a new CalculatorProbeSet containing only a single probe, e.g. as used
	 * by <code>IdentityCalculator</code>.
	 * 
	 * @param probe the single probe
	 * @return a CalculatorProbeSet
	 */
	public static CalculatorProbeSet createSingularProbeConfiguration(final Probe probe) {
		return id -> {
			switch (id) {
			case SINGULAR_PROBE:
				return Optional.of(probe);
			default:
				return Optional.empty();
			}
		};
	}

	/**
	 * Returns a new CalculatorProbeSet containing a start and a stop probe, e.g. as
	 * used by <code>TimeSpanCalculator</code>.
	 * 
	 * @param startProbe the start probe
	 * @param stopProbe  the stop probe
	 * @return a CalculatorProbeSet
	 */
	public static CalculatorProbeSet createStartStopProbeConfiguration(final Probe startProbe, final Probe stopProbe) {
		return id -> {
			switch (id) {
			case START_PROBE:
				return Optional.of(startProbe);
			case STOP_PROBE:
				return Optional.of(stopProbe);
			default:
				return Optional.empty();
			}
		};
	}

	/**
	 * Returns a new custom CalculatorProbeSet containing the probes according to
	 * the provided mapping.
	 * 
	 * @param identifierToProbeMapping a map of probe identifier to probe
	 * @return the CalculatorProbeSet
	 */
	public static CalculatorProbeSet createCustomProbeSet(final Map<String, Probe> identifierToProbeMapping) {
		final var map = Collections.unmodifiableMap(new HashMap<>(identifierToProbeMapping));
		return id -> Optional.ofNullable(map.get(id));
	}

}
