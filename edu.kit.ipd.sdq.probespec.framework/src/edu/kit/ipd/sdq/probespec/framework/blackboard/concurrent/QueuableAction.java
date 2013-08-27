package edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent;

/**
 * An {@link QueuableAction} encapsulate an action which can be added to an {@link ActionQueue} to
 * postpone its processing.
 * 
 * @author Philipp Merkle
 * 
 */
public interface QueuableAction {

    void execute();

}
