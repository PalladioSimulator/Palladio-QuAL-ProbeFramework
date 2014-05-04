package org.palladiosimulator.probeframework.calculator;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.POINT_IN_TIME_METRIC;

import java.util.List;

import org.apache.log4j.Logger;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.MeasurementTupple;
import org.palladiosimulator.measurementspec.listener.IMeasurementSourceListener;
import org.palladiosimulator.measurementspec.listener.MeasurementSource;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.util.builder.MetricSetDescriptionBuilder;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.exceptions.CalculatorException;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.listener.IProbeListener;

/**
 * This class is the abstract super class for all Calculator implementations. All specific
 * Calculators have to inherit from this class.
 * <p>
 * Calculators observe the {@link ISampleBlackboard} for probe set samples (Observer Pattern). As
 * soon as a new probe set sample is published at the blackboard, the
 * {@link #execute(MeasurementTupple)} method is invoked. The calculator have to decide, whether the
 * probe set sample is of interest for the calculation.
 * 
 * TODO Add "remove methods" for listeners
 * 
 * @author Faber, Sebastian Lehrig, Steffen Becker
 * 
 */
public abstract class Calculator extends MeasurementSource implements IProbeListener {

    /** Logger of this class */
    private static final Logger logger = Logger.getLogger(Calculator.class);

    private final ProbeFrameworkContext probeFrameworkContext;

    protected Calculator(final ProbeFrameworkContext ctx, final MetricDescription computedMetric) {
        super(computedMetric);
        this.probeFrameworkContext = ctx;
    }

    /**
     * Calculates measurements based on a given probe sample of a single, unary probe.
     * 
     * @param sample
     *            Sample conforming to the probeSetID of this calculator.
     * @return List of measures that conforms to the static declaration of the metrics of this class
     *         of calculators.
     * @throws CalculatorException
     */
    abstract protected Measurement calculate(List<ProbeMeasurement> measurementMemory) throws CalculatorException;

    protected ProbeFrameworkContext getProbeFrameworkContext() {
        return probeFrameworkContext;
    }

    public void preUnregister() {
        for (final IMeasurementSourceListener l : this.getMeasurementSourceListeners()) {
            l.preUnregister();
            removeObserver(l);
        }
    }

    protected void fireCalculated(final List<ProbeMeasurement> measurementMemory) {
        try {
            final Measurement calculatedMeasures = calculate(measurementMemory);
            notifyMeasurementSourceListener(calculatedMeasures);
        } catch (final CalculatorException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    protected static MetricDescription createMetricSetDescription(final String metricName,
            final String metricDescription, final List<BaseMetricDescription> metricDescriptions) {
        final MetricSetDescription result = MetricSetDescriptionBuilder.newMetricSetDescriptionBuilder()
                .name(metricName).textualDescription(metricDescription).subsumedMetrics(POINT_IN_TIME_METRIC)
                .subsumedMetrics(metricDescriptions).build();

        return result;
    }

    public abstract void detachProbes();

    public abstract void releaseMemory(RequestContext requestContext);
}
