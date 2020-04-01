package org.palladiosimulator.probeframework.probes;

import java.util.Optional;

/**
 * This class contains factory methods and identifier for common
 * <code>ProbeConfiguration</code>s.
 * 
 * @author Sebastian Krach
 *
 */
public final class ProbeConfigurations {
    /** The sole probe contained in a configuration */
    public static final String SINGULAR_PROBE = "org.palladiosimulator.probeframework.probes.singular";

    /** The start probe of a StartStopProbeConfiguration */
    public static final String START_PROBE = "org.palladiosimulator.probeframework.probes.start";

    /** The stop probe of a StartStopProbeConfiguration */
    public static final String STOP_PROBE = "org.palladiosimulator.probeframework.probes.stop";

    /**
     * Returns a new ProbeConfiguration containing only a single probe, e.g. as used
     * by <code>IdentityCalculator</code>.
     * 
     * @param probe the single probe
     * @return a probe configuration
     */
    public static ProbeConfiguration createSingularProbeConfiguration(final Probe probe) {
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
     * Returns a new ProbeConfiguration containing a start and a stop probe, e.g. as
     * used by <code>TimeSpanCalculator</code>.
     * 
     * @param startProbe the start probe
     * @param stopProbe the stop probe
     * @return a probe configuration
     */
    public static ProbeConfiguration createStartStopProbeConfiguration(final Probe startProbe, final Probe stopProbe) {
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

}
