package de.uka.ipd.sdq.probespec.framework;

public interface IGarbageCollector {

	/**
	 * informs the garbage collector that a new sample has been arrived that
	 * eventually needs to be collected
	 */
	public void provide(RequestContext contextID, ProbeSetSampleID sampleID);

}
