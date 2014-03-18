package de.uka.ipd.sdq.probespec.framework.calculator;

import static de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants.POINT_IN_TIME_METRIC;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.apache.log4j.Logger;
import org.palladiosimulator.edp2.models.ExperimentData.BaseMetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataFactory;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage;
import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;

import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.exceptions.CalculatorException;
import de.uka.ipd.sdq.probespec.framework.measurements.BasicMeasurement;
import de.uka.ipd.sdq.probespec.framework.measurements.IMeasurementSourceListener;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSource;

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

    /** copy on write enables listeners to unregister during event processing. */
    private final Set<ICalculatorListener> listeners;

    /** Error Message */
    private static final String MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS = "Mesurements do not conform to declared metrics";

    private final ProbeSpecContext probeSpecContext;

    protected Calculator(final ProbeSpecContext ctx, final MetricDescription computedMetric) {
        super(computedMetric);
        this.probeSpecContext = ctx;
        this.listeners = new HashSet<ICalculatorListener>();
    }

    @Override
    abstract public void measurementTaken(final Measurement measurement);

    /**
     * Calculates measurements based on a given probe sample of a single, unary probe.
     * 
     * @param sample Sample conforming to the probeSetID of this calculator.
     * @return List of measures that conforms to the static declaration of the metrics of this class of calculators.
     * @throws CalculatorException
     */
    abstract protected Measurement calculate(List<Measurement> probeMeasurements) throws CalculatorException;

    protected ProbeSpecContext getProbeSpecContext() {
        return probeSpecContext;
    }

    public void registerCalculatorListener(final ICalculatorListener l) {
        this.listeners.add(l);
    }

    public void unregisterCalculatorListeners() {
        for (final ICalculatorListener l : this.listeners) {
            l.preUnregister();
        }
        this.listeners.removeAll(listeners);
    }

    protected void fireCalculated(final List<Measurement> probeSetMeasurements) {
        Measurement calculatedMeasures;

        try {
            calculatedMeasures = calculate(probeSetMeasurements);
        } catch (final CalculatorException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }

        //        if(calculatedMeasures.size() != getMetricDesciption().getSubsumedMetrics().size()) {
        //            logger.error(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
        //            throw new RuntimeException(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
        //        }
        //
        //        for(int i=0; i<calculatedMeasures.size(); i++) {
        //            final Measure<?, ? extends Quantity> calculatedMeasure = calculatedMeasures.get(i);
        //            final Description description = metricSetDescription.getSubsumedMetrics().get(i);
        //
        //            if(description instanceof BaseMetricDescription) {
        //                final BaseMetricDescription baseMetricDescription = (BaseMetricDescription) description;
        //
        //                final Class<?> valueDataType;
        //                switch(baseMetricDescription.getCaptureType()) {
        //                case IDENTIFIER:
        //                    valueDataType = String.class;
        //                    break;
        //                case INTEGER_NUMBER:
        //                    valueDataType = Long.class;
        //                    break;
        //                case REAL_NUMBER:
        //                    valueDataType = Double.class;
        //                    break;
        //                default:
        //                    valueDataType = null;
        //                    break;
        //                }
        //
        //                if(calculatedMeasure.getValue().getClass() != valueDataType) {
        //                    logger.error(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
        //                    throw new RuntimeException(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
        //                }
        //
        //                if(baseMetricDescription instanceof NumericalBaseMetricDescription) {
        //                    final NumericalBaseMetricDescription numericalBaseMetricDescription =
        //                            (NumericalBaseMetricDescription) baseMetricDescription;
        //                    if(!calculatedMeasure.getUnit().isCompatible(numericalBaseMetricDescription.getDefaultUnit())) {
        //                        logger.error(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
        //                        throw new RuntimeException(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
        //                    }
        //                }
        //            }
        //        }

        for (final ICalculatorListener l : this.listeners) {
            l.measurementTaken(calculatedMeasures);
        }
    }

    /**
     * Obtains a measure from a given probe set sample by applying a given
     * matching rule.
     * 
     * @param measurementSet The ProbeSetSample to get a measurement from.
     * @param matchRule A filter for the measure of interest.
     * @return The measure as filtered by the matching rule.
     * @throws CalculatorException In case the matching rule does not result in exactly one measure.
     */
    protected Measurement obtainMeasurement(final Measurement measurement, final MetricDescription wantedMetric) {

        List<Measurement> measurements = null;
        if (measurement instanceof MeasurementSet) {
            measurements = ((MeasurementSet)measurement).getSubsumedMeasurements();
        } else if (measurement instanceof BasicMeasurement<?,?>) {
            measurements = Arrays.asList(measurement);
        }

        for (final Measurement childMeasurement : measurements) {
            if (childMeasurement.getMetricDesciption() == wantedMetric) {
                return childMeasurement;
            }
        }
        throw new RuntimeException("Requested metric not found in measurments.");
    }

    @SuppressWarnings("unchecked")
    protected <V, Q extends Quantity> Measure<V, Q> obtainMeasure(final Measurement measurements, final MetricDescription wantedMetric) {
        final Measurement measurement = obtainMeasurement(measurements, wantedMetric);
        return ((BasicMeasurement<V,Q>)measurement).getMeasure();
    }


    protected static MetricDescription createMetricSetDescription(final String metricName, final String metricDescription, final List<BaseMetricDescription> metricDescriptions) {
        final MetricSetDescription result = experimentDataFactory.createMetricSetDescription();
        result.setName(metricName);
        result.setTextualDescription(metricDescription);
        result.getSubsumedMetrics().add(POINT_IN_TIME_METRIC);
        result.getSubsumedMetrics().addAll(metricDescriptions);

        return result;
    }

}
