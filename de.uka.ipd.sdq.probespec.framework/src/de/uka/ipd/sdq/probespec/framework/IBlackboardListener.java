package de.uka.ipd.sdq.probespec.framework;

/**
 * Listener interface for the blackboard.
 * 
 * @author Sebastian Lehrig
 */
public interface IBlackboardListener {

	/**
	 * Callback method when ProbeSpec sample arrived.
	 * 
	 * @param pss The arriving ProbeSPec sample
	 * @return Vote whether sample should stay on blackboard
	 */
	public BlackboardVote sampleArrived(ProbeSetSample pss);
	
}
