package org.palladiosimulator.probeframework.measurement;

import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.probeframework.probes.Probe;

public class ProbeMeasurement {
    private final Measurement measurement;
    private final ProbeAndRequestContext sourceAndContext;
    private final String measuredEntity;

    /**
     * @param measurement
     * @param sourceAndContext
     */
    public ProbeMeasurement(final Measurement measurement, final Probe measuredProbe,
            final RequestContext requestContext, final String measuredEntity) {
        super();
        this.measurement = measurement;
        this.sourceAndContext = new ProbeAndRequestContext(measuredProbe, requestContext);
        this.measuredEntity = measuredEntity;
    }

    /**
     * @return the measurement
     */
    public final Measurement getMeasurement() {
        return measurement;
    }

    /**
     * @return the sourceAndContext
     */
    public final ProbeAndRequestContext getSourceAndContext() {
        return sourceAndContext;
    }

    /**
     * @return the measuredEntity
     */
    public final String getMeasuredEntity() {
        return measuredEntity;
    }

}
