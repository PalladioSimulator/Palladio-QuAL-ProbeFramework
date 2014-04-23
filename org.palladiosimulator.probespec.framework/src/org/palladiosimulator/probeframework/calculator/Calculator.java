package org.palladiosimulator.probeframework.calculator;

import static org.palladiosimulator.metricspec.MetricDescriptionConstants.POINT_IN_TIME_METRIC;

import java.util.List;

import org.apache.log4j.Logger;
import org.palladiosimulator.edp2.models.ExperimentData.BaseMetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataFactory;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage;
import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;
import org.palladiosimulator.measurementspec.IMeasurementSourceListener;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementSet;
import org.palladiosimulator.measurementspec.MeasurementSource;
import org.palladiosimulator.measurementspec.requestcontext.RequestContext;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.exceptions.CalculatorException;

/**
 * This class is the abstract super class for all Calculator implementations.
 * All specific Calculators have to inherit from this class.
 * <p>
 * Calculators observe the {@link ISampleBlackboard} for probe set samples
 * (Observer Pattern). As soon as a new probe set sample is published at the
 * blackboard, the {@link #execute(MeasurementSet)} method is invoked. The
 * calculator have to decide, whether the probe set sample is of interest for
 * the calculation.
 * 
 * TODO Add "remove methods" for listeners
 * 
 * @author Faber, Sebastian Lehrig, Steffen Becker
 * 
 */
public abstract class Calculator extends MeasurementSource implements IMeasurementSourceListener {

    /** Logger of this class */
    private static final Logger logger = Logger.getLogger(Calculator.class);

    /** EMF initialization. Must exist but not be used in the further code. */
    @SuppressWarnings("unused")
    private final static ExperimentDataPackage experimentDataPackage = ExperimentDataPackage.eINSTANCE;

    /** Shortcut to experiment data factory. */
    private final static ExperimentDataFactory experimentDataFactory = ExperimentDataFactory.eINSTANCE;

    private final ProbeFrameworkContext probeFrameworkContext;

    protected Calculator(final ProbeFrameworkContext ctx, final MetricDescription computedMetric) {
        super(computedMetric);
        this.probeFrameworkContext = ctx;
    }

    @Override
    abstract public void newMeasurementAvailable(final Measurement measurement);

    /**
     * Calculates measurements based on a given probe sample of a single, unary probe.
     * 
     * @param sample Sample conforming to the probeSetID of this calculator.
     * @return List of measures that conforms to the static declaration of the metrics of this class of calculators.
     * @throws CalculatorException
     */
    abstract protected Measurement calculate(List<Measurement> probeMeasurements) throws CalculatorException;

    protected ProbeFrameworkContext getProbeFrameworkContext() {
        return probeFrameworkContext;
    }

    @Override
	public void preUnregister() {
        for (final IMeasurementSourceListener l : this.getMeasurementSourceListeners()) {
            l.preUnregister();
            removeObserver(l);
        }
    }

    protected void fireCalculated(final List<Measurement> probeSetMeasurements) {
        try {
            final Measurement calculatedMeasures = calculate(probeSetMeasurements);
            notifyMeasurementSourceListener(calculatedMeasures);
        } catch (final CalculatorException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    protected static MetricDescription createMetricSetDescription(final String metricName, final String metricDescription, final List<BaseMetricDescription> metricDescriptions) {
        final MetricSetDescription result = experimentDataFactory.createMetricSetDescription();
        result.setName(metricName);
        result.setTextualDescription(metricDescription);
        result.getSubsumedMetrics().add(POINT_IN_TIME_METRIC);
        result.getSubsumedMetrics().addAll(metricDescriptions);

        return result;
    }

    public abstract void detachProbes();

    public abstract void releaseMemory(RequestContext requestContext);
}