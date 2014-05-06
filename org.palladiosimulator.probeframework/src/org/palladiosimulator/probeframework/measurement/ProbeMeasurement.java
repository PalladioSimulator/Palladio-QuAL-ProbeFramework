package org.palladiosimulator.probeframework.measurement;

import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.probeframework.probes.Probe;

public class ProbeMeasurement {
    private final Measurement measurement;
    private final ProbeAndRequestContext probeAndContext;
    private final String measuredEntity;

    /**
     * @param measurement
     * @param probeAndContext
     */
    public ProbeMeasurement(final Measurement measurement, final Probe probe,
            final RequestContext requestContext, final String measuredEntity) {
        super();
        this.measurement = measurement;
        this.probeAndContext = new ProbeAndRequestContext(probe, requestContext);
        this.measuredEntity = measuredEntity;
    }

    /**
     * @return the measurement
     */
    public final Measurement getMeasurement() {
        return measurement;
    }

    /**
     * @return the probeAndContext
     */
    public final ProbeAndRequestContext getProbeAndContext() {
        return probeAndContext;
    }

    /**
     * @return the measuredEntity
     */
    public final String getMeasuredEntity() {
        return measuredEntity;
    }

}
