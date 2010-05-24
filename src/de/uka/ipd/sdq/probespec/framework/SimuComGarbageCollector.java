package de.uka.ipd.sdq.probespec.framework;

/**
 * A {@link RegionBasedGarbageCollector}, specialised to be used within SimuCom.
 * It enables a {@link RequestContext} to be used as region identifier. More
 * precisely, the specified {@link RequestContext} gets translated to the
 * appropriate region identifier.
 * 
 * @author Philipp Merkle
 * 
 */
public class SimuComGarbageCollector extends RegionBasedGarbageCollector<RequestContext> {

	public SimuComGarbageCollector(SampleBlackboard blackboard) {
		super(blackboard);
	}

	@Override
	public RequestContext obtainRegionId(
			ProbeSetAndRequestContext sampleId) {
		return sampleId.getCtxID().rootContext();
	}

}
