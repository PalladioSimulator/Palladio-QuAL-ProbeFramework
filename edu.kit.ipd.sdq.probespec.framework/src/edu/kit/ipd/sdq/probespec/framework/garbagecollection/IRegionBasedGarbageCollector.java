package edu.kit.ipd.sdq.probespec.framework.garbagecollection;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

/**
 * TODO
 * 
 * @author Philipp Merkle
 * @since 1.0
 *
 * @param <T>
 */
public interface IRegionBasedGarbageCollector<T extends IMeasurementContext> {
	
	public void enterContext(T regionId);
	
	public void leaveContext(T regionId);

}
