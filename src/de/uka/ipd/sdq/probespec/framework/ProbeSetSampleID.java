package de.uka.ipd.sdq.probespec.framework;

/**
 * 
 * @author Faber
 * 
 */
public class ProbeSetSampleID {

	private String probeSetID;
	private RequestContext ctxID;

	public ProbeSetSampleID(String probeSetID, RequestContext ctxID) {
		super();
		this.probeSetID = probeSetID;
		this.ctxID = ctxID;
	}

	/**
	 * Returns the id of the probe set according to the underlying model.
	 * 
	 * @return The ID of probe set model entity
	 */
	public String getProbeSetID() {
		return probeSetID;
	}

	/**
	 * Returns the identifier for the context in which the contained probe
	 * samples have been taken.
	 * 
	 * @return the context identifier
	 * @see RequestContext
	 */
	public RequestContext getCtxID() {
		return ctxID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ctxID == null) ? 0 : ctxID.hashCode());
		result = prime * result
				+ ((probeSetID == null) ? 0 : probeSetID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ProbeSetSampleID)) {
			return false;
		}
		ProbeSetSampleID other = (ProbeSetSampleID) obj;
		if (ctxID == null) {
			if (other.ctxID != null) {
				return false;
			}
		} else if (!ctxID.equals(other.ctxID)) {
			return false;
		}
		if (probeSetID == null) {
			if (other.probeSetID != null) {
				return false;
			}
		} else if (!probeSetID.equals(other.probeSetID)) {
			return false;
		}
		return true;
	}

	/**
	 * Generates a String of this ProbeSetSampleID.
	 * 
	 * @return String which characterizes this instance.
	 */
	@Override
	public String toString() {
		return probeSetID + "-" + ctxID.toString();
	}

}
