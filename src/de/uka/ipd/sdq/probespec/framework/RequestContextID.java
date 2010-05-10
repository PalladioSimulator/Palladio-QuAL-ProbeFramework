package de.uka.ipd.sdq.probespec.framework;

/**
 * This class represents the the execution context of a request. Normally the
 * execution context of a request is the thread which processes this request.
 * Then the thread's id is a unique identifier for this execution context.
 * <p>
 * A RequestContextID simply wraps an identifier that represents the execution
 * context clearly. The thread id is such an identifier.
 * <p>
 * In order to represent a sequence of requests so that request1 spawned
 * request2, which in turn spawned request3 and so forth, a parent context can
 * be specified by using constructor
 * {@link #RequestContextID(String, RequestContextID)}. In this way the
 * execution of forks can be represented.
 * 
 * @author Faber
 * @author Philipp Merkle
 * 
 */
public class RequestContextID {

	private String requestID;

	private RequestContextID parentContext;

	/**
	 * This Constructor creates a ContextID.
	 * 
	 * @param contextId
	 *            The RequestID which is set initially.
	 */
	public RequestContextID(String contextId) {
		this(contextId, null);
	}

	public RequestContextID(String contextId, RequestContextID parentContext) {
		this.requestID = contextId;
		this.parentContext = parentContext;
	}

	/**
	 * Returns the reqeuestID.
	 * 
	 * @return Returns the requestID for this ContextID
	 */
	public String getRequestID() {
		return requestID;
	}

	public RequestContextID getParentContext() {
		return parentContext;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((parentContext == null) ? 0 : parentContext.hashCode());
		result = prime * result
				+ ((requestID == null) ? 0 : requestID.hashCode());
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
		if (!(obj instanceof RequestContextID)) {
			return false;
		}
		RequestContextID other = (RequestContextID) obj;
		if (parentContext == null) {
			if (other.parentContext != null) {
				return false;
			}
		} else if (!parentContext.equals(other.parentContext)) {
			return false;
		}
		if (requestID == null) {
			if (other.requestID != null) {
				return false;
			}
		} else if (!requestID.equals(other.requestID)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return parentContext != null ? parentContext.toString() + " -> "
				+ requestID : requestID;
	}

}
