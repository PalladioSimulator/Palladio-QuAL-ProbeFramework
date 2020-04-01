package org.palladiosimulator.probeframework.probes;

import java.util.Optional;

/**
 * The ProbeConfiguration interface captures a set of probes required by a
 * calculator. It replaces the <code>List<Probe></code> which was passed to a calculator
 * factory method. Probes contained in a configuration can be referred to by a
 * semantic identifier.
 * 
 * Usually it is not necessary to implement this interface directly, instead use
 * an appropriate factory method of <code>ProbeConfigurations</code>. The only
 * exception is an extension which contributes a new type of calculator, to
 * which the current kinds of configurations are not suitable.
 * 
 * @author Sebastian Krach
 *
 */
@FunctionalInterface
public interface ProbeConfiguration {
    /**
     * Gets the probe identified by <code>probeIdentifier</code>
     * 
     * @param probeIdentifier the identifier of the probe.
     * @return
     */
    Optional<Probe> getProbe(String probeIdentifier);
}
