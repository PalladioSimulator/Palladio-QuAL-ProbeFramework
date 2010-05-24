package de.uka.ipd.sdq.probespec.framework;


public interface IRegionBasedGarbageCollector<T> {
	
	public void enterRegion(T regionId);
	
	public void leaveRegion(T regionId);
	

}
