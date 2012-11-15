package edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent;

/**
 * An {@link IQueuableAction} encapsulate an action which can be added to an {@link ActionQueue} to
 * postpone its processing.
 * 
 * @author Philipp Merkle
 * 
 */
public interface IQueuableAction {

    void execute();

}
