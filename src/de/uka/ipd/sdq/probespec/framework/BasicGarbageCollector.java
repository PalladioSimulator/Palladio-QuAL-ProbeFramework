package de.uka.ipd.sdq.probespec.framework;

import java.util.HashMap;
import java.util.Map;

public class BasicGarbageCollector implements IGarbageCollector {

	private Map<RequestContext, ProbeSetSampleID> map;
	
	public BasicGarbageCollector() {
		map = new HashMap<RequestContext, ProbeSetSampleID>();
	}
	
	@Override
	public void provide(RequestContext contextID, ProbeSetSampleID sampleID) {
		map.put(contextID, sampleID);
	}

}
