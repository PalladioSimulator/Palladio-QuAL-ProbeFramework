package org.palladiosimulator.probeframework.probes.example;

/**
 * Represents a mutex for demonstration purposes. Such a mutex is a special case of a passive
 * resource with size 1.
 * 
 * @author pmerkle, Sebastian Lehrig
 */
public class SimpleMutEx extends ASimplePassiveResource {

    /**
     * Default constructor. A mutex is a passive resource of size 1.
     */
    public SimpleMutEx() {
        super(1);
    }

}
