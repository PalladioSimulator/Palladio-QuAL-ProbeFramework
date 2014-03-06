package de.uka.ipd.sdq.probespec.framework.calculator;

import static de.uka.ipd.sdq.probespec.framework.constants.MeasurementMetricConstants.POINT_IN_TIME_METRIC;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.probespec.framework.BlackboardVote;
import de.uka.ipd.sdq.probespec.framework.IBlackboardListener;
import de.uka.ipd.sdq.probespec.framework.ISampleBlackboard;
import de.uka.ipd.sdq.probespec.framework.ProbeSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.exceptions.CalculatorException;
import de.uka.ipd.sdq.probespec.framework.matching.IMatchRule;

/**
 * This class is the abstract super class for all Calculator implementations.
 * All specific Calculators have to inherit from this class.
 * <p>
 * Calculators observe the {@link ISampleBlackboard} for probe set samples
 * (Observer Pattern). As soon as a new probe set sample is published at the
 * blackboard, the {@link #execute(ProbeSetSample)} method is invoked. The
 * calculator have to decide, whether the probe set sample is of interest for
 * the calculation.
 * 
 * TODO Add "remove methods" for listeners
 * 
 * @author Faber, Sebastian Lehrig, Steffen Becker
 * 
 */
public abstract class Calculator implements IBlackboardListener {
	
	/** Error Message */
	private static final String MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS = "Mesurements do not conform to declared metrics";

	/** Logger of this class */
	private static final Logger logger = Logger.getLogger(Calculator.class);
	
    private final ProbeSpecContext probeSpecContext;    
	
	/** List of metric definitions for measurements, e.g., the POINT_IN_TIME_METRIC */
	protected final List<MeasurementMetric> measurementMetrics = new LinkedList<MeasurementMetric>();
	
	/** copy on write enables listeners to unregister during event processing. */
	private final CopyOnWriteArrayList<ICalculatorListener> listeners;
	
	protected Calculator(ProbeSpecContext ctx, List<MeasurementMetric> measurementMetrics) {
		super();		
	    this.probeSpecContext = ctx;
		this.listeners = new CopyOnWriteArrayList<ICalculatorListener>();
		this.measurementMetrics.add(POINT_IN_TIME_METRIC);
		this.measurementMetrics.addAll(measurementMetrics);
	}
	
	/**
	 * This method is called to return meta data about the result tuples of the
	 * calculator, i.e., it is used initialize the pipe and filter chain.
	 * 
	 * @return Meta data declaration of the returned metrics.
	 */
	public final List<MeasurementMetric> getMeasurementMetrics() {
		return measurementMetrics;
	}

	@Override
	abstract public BlackboardVote sampleArrived(ProbeSetSample probeSetSample);
	
	/**
     * Calculates measurements based on a given probe sample of a single, unary probe.
     * 
     * @param sample Sample conforming to the probeSetID of this calculator.
     * @return List of measures that conforms to the static declaration of the metrics of this class of calculators.
     * @throws CalculatorException
     */
	abstract protected List<Measure<?, ? extends Quantity>> calculate(List<ProbeSetSample> probeSetSamples) throws CalculatorException;
	
    protected ProbeSpecContext getProbeSpecContext() {
        return probeSpecContext;
    }

	public void addCalculatorListener(ICalculatorListener l) {
		listeners.add(l);
	}

	protected void fireCalculated(List<ProbeSetSample> probeSetSamples) {
		List<Measure<?, ? extends Quantity>> calculatedMeasures;
		
		try {
			calculatedMeasures = calculate(probeSetSamples);
		} catch (CalculatorException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
		
		if(calculatedMeasures.size() != measurementMetrics.size()) {			
			logger.error(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
			throw new RuntimeException(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
		}
		
		for(int i=0; i<calculatedMeasures.size(); i++) {
			Measure<?, ? extends Quantity> calculatedMeasure = calculatedMeasures.get(i);
			MeasurementMetric metric = measurementMetrics.get(i);
			
			if(!calculatedMeasure.getUnit().isCompatible(metric.getUnit())) {
				logger.error(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
				throw new RuntimeException(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
			}
			
			if(calculatedMeasure.getValue().getClass() != metric.getValueType()) {
				logger.error(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
				throw new RuntimeException(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
			}
		}
		
		for (ICalculatorListener l : listeners) {
			l.calculated(calculatedMeasures);
		}
	}	
	
	/**
	 * Obtains a measure from a given probe set sample by applying a given
	 * matching rule.
	 * 
	 * @param sample The ProbeSetSample to get a measurement from.
	 * @param matchRule A filter for the measure of interest.
	 * @return The measure as filtered by the matching rule.
	 * @throws CalculatorException In case the matching rule does not result in exactly one measure.
	 */
	@SuppressWarnings("unchecked")
	protected <V, Q extends Quantity> Measure<V, Q> obtainMeasure(ProbeSetSample sample,
			IMatchRule matchRule) throws CalculatorException {
		List<ProbeSample<?, ? extends Quantity>> probeSample = sample.getProbeSamples(matchRule);
		
		if (probeSample == null || probeSample.size() != 1) {
			throw new CalculatorException(
					"Could not access all needed probe samples.");			
		}
		
		return (Measure<V, Q>) probeSample.get(0).getMeasure();
	}
}
