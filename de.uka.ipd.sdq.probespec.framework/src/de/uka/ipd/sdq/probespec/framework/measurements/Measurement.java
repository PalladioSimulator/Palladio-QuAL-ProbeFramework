package de.uka.ipd.sdq.probespec.framework.measurements;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;

import de.uka.ipd.sdq.probespec.framework.requestcontext.ProbeAndRequestContext;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;

public abstract class Measurement extends MeasurementSource {

    private final ProbeAndRequestContext probeAndRequestContext;

    /** The id of the annotated model element */
    private final String modelElementID;


    /**
     * @param measuredMetric
     * @param measuredProbe
     * @param modelElementID
     */
    protected Measurement(final RequestContext requestContext, final MetricDescription measuredMetric, final MeasurementSource measuredProbe, final String modelElementID) {
        super(measuredMetric);
        this.probeAndRequestContext = new ProbeAndRequestContext(measuredProbe, requestContext);
        this.modelElementID = modelElementID;
    }

    /**
     * @return the measuredProbe
     */
    public final MeasurementSource getMeasuredProbe() {
        return probeAndRequestContext.getProbe();
    }

    /**
     * Returns the id of the model element which is annotated by the underlying
     * probe set.
     * 
     * @return the model element id
     */
    public final String getModelElementID() {
        return modelElementID;
    }

    public RequestContext getRequestContext() {
        return this.probeAndRequestContext.getRequestContext();
    }

}
