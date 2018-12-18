package org.palladiosimulator.probeframework.probes.example;

/**
 * Represents an abstract passive resource for demonstration purposes.
 * <p>
 * The state of a concrete example passive resource can be manually controlled.
 * 
 * @author pmerkle, Sebastian Lehrig
 */
public abstract class ASimplePassiveResource {

    /** Size of the passive resource. */
    private final int size;

    /** Number of free resources. */
    private int free;

    /**
     * Default constructor. Creates a passive resource with the given resource size.
     * 
     * @param size
     *            Size of the passive resource for resources.
     */
    public ASimplePassiveResource(int size) {
        this.size = size;
        this.free = size;
    }

    /**
     * Aquires a resource from the passive resource.
     */
    public void acquire() {
        --free;
    }

    /**
     * Releases a resource from the passive resource.
     */
    public void release() {
        ++free;
    }

    /**
     * States whether free resources are left within the passive resource.
     * 
     * @return <code>true</code> if free resources are left, <code>false</code> otherwise.
     */
    public boolean canAcquire() {
        return free > 0;
    }

    /**
     * Returns the maximum size of this passive resource.
     * 
     * @return The size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the number of currently free resources within this passive resource.
     * 
     * @return The number of free resources.
     */
    public int getFree() {
        return free;
    }

}
