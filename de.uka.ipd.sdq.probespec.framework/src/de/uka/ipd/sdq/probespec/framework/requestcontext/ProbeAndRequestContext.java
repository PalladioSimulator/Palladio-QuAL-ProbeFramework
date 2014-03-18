package de.uka.ipd.sdq.probespec.framework.requestcontext;

import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSource;

/**
 * Represents a (ProbeSet, {@link RequestContext})-pair. The ProbeSet is
 * identified by its ID.
 * <p>
 * It is used to uniquely identify {@link MeasurementSet}.
 * 
 * @author Faber
 * @author Philipp Merkle
 * 
 */
public final class ProbeAndRequestContext {

    // the ID representing the ProbeSet
    private final MeasurementSource probe;

    private final RequestContext requestContext;

    /**
     * Default constructor. Constructs a pair consisting of a ProbeSet and a
     * {@link RequestContext}.
     * 
     * @param probeSetId
     *            the ID representing the ProbeSet
     * @param requestContext
     *            the RequestContext
     */
    public ProbeAndRequestContext(final MeasurementSource measuredProbe,
            final RequestContext requestContext) {
        this.probe = measuredProbe;
        this.requestContext = requestContext;
    }

    /**
     * Returns the ID of the ProbeSet component.
     * 
     * @return The ID representing the ProbeSet
     */
    public final MeasurementSource getProbe() {
        return probe;
    }

    /**
     * Returns the {@link RequestContext} component.
     * 
     * @return the context
     */
    public final RequestContext getRequestContext() {
        return requestContext;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((requestContext == null) ? 0 : requestContext.hashCode());
        result = prime * result
                + ((probe == null) ? 0 : probe.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ProbeAndRequestContext)) {
            return false;
        }
        final ProbeAndRequestContext other = (ProbeAndRequestContext) obj;
        if (requestContext == null) {
            if (other.requestContext != null) {
                return false;
            }
        } else if (!requestContext.equals(other.requestContext)) {
            return false;
        }
        if (probe == null) {
            if (other.probe != null) {
                return false;
            }
        } else if (!probe.equals(other.probe)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return probe + "-" + requestContext.toString();
    }

}
