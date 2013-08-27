package edu.kit.ipd.sdq.probespec.framework.garbagecollection;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;

/**
 * TODO
 * 
 * @author Philipp Merkle
 * @since 1.0
 *
 * @param <T>
 */
public interface RegionBasedGarbageCollector<T extends MeasurementContext> {
	
	public void enterContext(T regionId);
	
	public void leaveContext(T regionId);

}
