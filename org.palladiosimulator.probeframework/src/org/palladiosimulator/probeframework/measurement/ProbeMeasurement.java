package org.palladiosimulator.probeframework.measurement;

import org.palladiosimulator.measurementframework.BasicMeasurement;
import org.palladiosimulator.measurementframework.Measurement;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Data class representing a ({@link BasicMeasurement}, ProbeAndRequestContext, String)-tuple, where
 * the String element refers to a measured entity. Therefore, concrete data objects can store a
 * measurement coming from a given ProbeAndRequestContext that probes the given measured entity.
 * 
 * TODO Update JavaDoc
 * 
 * TODO Use MeasuringPoint instead of String
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public class ProbeMeasurement {

    /** The measurement to be stored. */
    private final Measurement measurement;

    /** The probe and the request context. */
    private final ProbeAndRequestContext probeAndContext;

    /** The measured entity. */
    private final String measuredEntity;

    /**
     * Default constructor. Constructs the object tuple.
     * 
     * @param measurement
     *            The measurement to be stored.
     * @param probe
     *            The referred probe.
     * @param requestContext
     *            The referred request context.
     * @param measuredEntity
     *            A String representing the probed entity.
     */
    public ProbeMeasurement(final Measurement measurement, final Probe probe,
            final RequestContext requestContext, final String measuredEntity) {
        super();
        this.measurement = measurement;
        this.probeAndContext = new ProbeAndRequestContext(probe, requestContext);
        this.measuredEntity = measuredEntity;
    }

    /**
     * Getter for the measurement.
     * 
     * @return The measurement.
     */
    public final Measurement getMeasurement() {
        return this.measurement;
    }

    /**
     * Getter for the probe and request context.
     * 
     * @return The probeAndContext.
     */
    public final ProbeAndRequestContext getProbeAndContext() {
        return this.probeAndContext;
    }

    /**
     * Getter for the measured entity String.
     * 
     * @return The measuredEntity.
     */
    public final String getMeasuredEntity() {
        return this.measuredEntity;
    }

}
