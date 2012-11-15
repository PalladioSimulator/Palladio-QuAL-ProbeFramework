package edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent;

/**
 * A runnable which can be stopped gracefully without having to use {@link Thread#stop()}.
 * 
 * @author Philipp Merkle
 * @since 1.0
 * 
 */
public interface StoppableRunnable extends Runnable {

    /**
     * Try to stop the {@link StoppableRunnable}. Must not throw any exceptions, be silent if the
     * {@link StoppableRunnable} is already stopped.
     */
    public void stop();

}
