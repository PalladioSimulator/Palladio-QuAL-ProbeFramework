package de.uka.ipd.sdq.probespec.framework.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * This class supports the generation of numeric probe set ids required when
 * using the Probe Specification.
 * 
 * @author Philipp Merkle
 * 
 */
public class ProbeSetIDGenerator {

	private Map<String, Integer> idMap = new HashMap<String, Integer>();

	/*
	 * While transforming the PCM models to simulation code, SimuCom already
	 * generates some IDs, starting by 1. But SimuCom does not use this class.
	 * Thus the highest assigned ID is not known here and we have to assume it
	 * is lower than a constant value, e.g. 1000.
	 * 
	 * TODO: Use setHighestId after SimuCom generated the simulation code
	 */
	private int highestId = 1000;

	public ProbeSetIDGenerator() {
		idMap = new HashMap<String, Integer>();
	}

	/**
	 * Sets the highest probe set id to be used for subsequent calls of
	 * {@link #obtainId(String)}. The next call will return <code>id + 1</code>.
	 * 
	 * @param id
	 */
	public void setHighestId(Integer id) {
		highestId = id;
	}

	/**
	 * Generates a numeric probe set id satisfying two conditions: When the
	 * String passed by the parameter id has not yet been passed before, the
	 * returned probe set id will be greater than all probe set ids generated
	 * before. Else the same probe set id generated previously will be returned.
	 * Thus, the same values for id will be mapped to the same numerical probe
	 * set id.
	 * 
	 * @param ID
	 * @return
	 */
	public int obtainId(String ID) {
		Integer foundId = idMap.get(ID);
		if (foundId == null) {
			idMap.put(ID, ++highestId);
			return highestId;
		}
		return foundId;
	}

}
