package org.palladiosimulator.probeframework.measurement;

import org.palladiosimulator.measurementspec.MeasurementTupple;
import org.palladiosimulator.metricspec.metricentity.MetricEntity;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Represents a (ProbeSet, {@link RequestContext})-pair. The ProbeSet is
 * identified by its ID.
 * <p>
 * It is used to uniquely identify {@link MeasurementTupple}.
 * 
 * @author Faber
 * @author Philipp Merkle
 * 
 */
public final class ProbeAndRequestContext {

    // the ID representing the ProbeSet
    private final Probe measurementSource;

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
    public ProbeAndRequestContext(
            final Probe measuredProbe,
            final RequestContext requestContext) {
        this.measurementSource = measuredProbe;
        this.requestContext = requestContext;
    }

    /**
     * Returns the ID of the ProbeSet component.
     * 
     * @return The ID representing the ProbeSet
     */
    public final MetricEntity getMeasurementSource() {
        return measurementSource;
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
                + ((measurementSource == null) ? 0 : measurementSource.hashCode());
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
        if (measurementSource == null) {
            if (other.measurementSource != null) {
                return false;
            }
        } else if (!measurementSource.equals(other.measurementSource)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return measurementSource + "-" + requestContext.toString();
    }

}
