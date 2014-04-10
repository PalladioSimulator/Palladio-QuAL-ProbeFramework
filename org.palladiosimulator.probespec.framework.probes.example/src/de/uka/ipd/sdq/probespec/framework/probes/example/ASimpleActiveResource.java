package de.uka.ipd.sdq.probespec.framework.probes.example;

import java.util.Observable;

/**
 * Represents an abstract active resource for demonstration purposes.
 * <p>
 * The state of a concrete example active resource can be manually controlled.
 * 
 * @author pmerkle
 * 
 */
public abstract class ASimpleActiveResource extends Observable {

    /** Amount of jobs */
    private int jobs;

    /**
     * Returns the amount of currently assigned jobs.
     * 
     * @return the currently assigned job count
     */
    public int getJobs() {
        return jobs;
    }

    /**
     * Sets the amount of currently assigned jobs.
     * 
     * @param jobs
     *            the job count to be assigned
     */
    public void setJobs(final int jobs) {
        this.jobs = jobs;
    }

    public void demand(final double demand) {
        this.setChanged();
        this.notifyObservers(demand);
    }

}
