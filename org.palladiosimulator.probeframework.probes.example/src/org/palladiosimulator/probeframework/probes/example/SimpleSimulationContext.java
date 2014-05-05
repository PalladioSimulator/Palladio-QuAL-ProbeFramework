package org.palladiosimulator.probeframework.probes.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a simple simulation context for demonstration purposes.
 * <p>
 * The simulation context holds the current simulation time as well as available active resources.
 * 
 * @author pmerkle, Sebastian Lehrig
 * 
 */
public class SimpleSimulationContext {

    /** Passed simulation time */
    private double simulatedTime;

    /** Maps "resource name" -> resource */
    private final Map<String, ASimpleActiveResource> activeResourceMap;

    /**
     * Default constructor.
     */
    public SimpleSimulationContext() {
        this.activeResourceMap = new HashMap<String, ASimpleActiveResource>();
    }

    /**
     * Getter method for passed simulation time.
     * 
     * @return Passed simulation time.
     */
    public double getSimulatedTime() {
        return simulatedTime;
    }

    /**
     * Setter for the passed simulation time.
     * 
     * @param simulatedTime
     *            Passed simulation time.
     */
    public void setSimulatedTime(double simulatedTime) {
        this.simulatedTime = simulatedTime;
    }

    /**
     * Adds an active resource to this simulation.
     * 
     * @param name
     *            The name of the active resource, used as a reference key.
     * @param resource
     *            THe active resource to be added.
     */
    public void addActiveResource(String name, ASimpleActiveResource resource) {
        activeResourceMap.put(name, resource);
    }

    /**
     * Getter method for the active resource identified by the given name.
     * 
     * @param name
     *            The name of the resource to be returned.
     * @return The active resource of interest.
     */
    public ASimpleActiveResource getActiveResource(String name) {
        return activeResourceMap.get(name);
    }

}
