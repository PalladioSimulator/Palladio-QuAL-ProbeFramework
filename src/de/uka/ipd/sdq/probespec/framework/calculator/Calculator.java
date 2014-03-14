package de.uka.ipd.sdq.probespec.framework.calculator;

import static de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants.POINT_IN_TIME_METRIC;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.palladiosimulator.edp2.models.ExperimentData.BaseMetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.Description;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataFactory;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage;
import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;
import org.palladiosimulator.edp2.models.ExperimentData.NumericalBaseMetricDescription;

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

	/** Logger of this class */
	private static final Logger logger = Logger.getLogger(Calculator.class);     
	
    /** EMF initialization. Must exist but not be used in the further code. */
    @SuppressWarnings("unused")
    private final static ExperimentDataPackage experimentDataPackage = ExperimentDataPackage.eINSTANCE;
    
    /** Shortcut to experiment data factory. */
    private final static ExperimentDataFactory experimentDataFactory = ExperimentDataFactory.eINSTANCE;
    
	/** Contains metric definitions for measurements, e.g., the POINT_IN_TIME_METRIC. */
	public final MetricSetDescription metricSetDescription = experimentDataFactory.createMetricSetDescription();
	
	/** copy on write enables listeners to unregister during event processing. */
	private final Set<ICalculatorListener> listeners;
	
	/** Error Message */
	private static final String MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS = "Mesurements do not conform to declared metrics";
	
	private final ProbeSpecContext probeSpecContext;

	private String calculatorId; 
	
	protected Calculator(ProbeSpecContext ctx, List<MetricDescription> metricDescriptions) {
		super();		
	    this.probeSpecContext = ctx;
		this.listeners = new HashSet<ICalculatorListener>();
		this.metricSetDescription.getSubsumedMetrics().add(POINT_IN_TIME_METRIC);
		this.metricSetDescription.getSubsumedMetrics().addAll(metricDescriptions);
		this.metricSetDescription.eAdapters().add(new AdapterImpl() {
			@Override
			public void notifyChanged(Notification notification) {
		        //throw new RuntimeException("FIXME: Notfication received from the data model. Data model has changed!!!");
		      }
		});
	}
	
	/**
	 * This method is called to return meta data about the result tuples of the
	 * calculator, i.e., it is used initialize the pipe and filter chain.
	 * 
	 * @return Meta data declaration of the returned metrics.
	 */
	public final MetricSetDescription getMetricDescriptions() {
		return metricSetDescription;
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

	public void registerCalculatorListener(ICalculatorListener l) {
		this.listeners.add(l);
	}
	
	public void unregisterCalculatorListeners() {
		for (ICalculatorListener l : this.listeners) {
			l.preUnregister();
		}
		this.listeners.removeAll(listeners);
	}

	protected void fireCalculated(List<ProbeSetSample> probeSetSamples) {
		List<Measure<?, ? extends Quantity>> calculatedMeasures;
		
		try {
			calculatedMeasures = calculate(probeSetSamples);
		} catch (CalculatorException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
		
		if(calculatedMeasures.size() != metricSetDescription.getSubsumedMetrics().size()) {			
			logger.error(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
			throw new RuntimeException(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
		}
		
		for(int i=0; i<calculatedMeasures.size(); i++) {
			Measure<?, ? extends Quantity> calculatedMeasure = calculatedMeasures.get(i);
			Description description = metricSetDescription.getSubsumedMetrics().get(i);
			
			if(description instanceof BaseMetricDescription) {				
				BaseMetricDescription baseMetricDescription = (BaseMetricDescription) description;
				
				final Class<?> valueDataType;
				switch(baseMetricDescription.getCaptureType()) {
				case IDENTIFIER:
					valueDataType = String.class; 
					break;
				case INTEGER_NUMBER:
					valueDataType = Long.class;
					break;
				case REAL_NUMBER:
					valueDataType = Double.class;
					break;
				default:
					valueDataType = null;
					break;					
				}
				
				if(calculatedMeasure.getValue().getClass() != valueDataType) {
					logger.error(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
					throw new RuntimeException(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
				}
				
				if(baseMetricDescription instanceof NumericalBaseMetricDescription) {
					NumericalBaseMetricDescription numericalBaseMetricDescription =
							(NumericalBaseMetricDescription) baseMetricDescription;
					if(!calculatedMeasure.getUnit().isCompatible(numericalBaseMetricDescription.getDefaultUnit())) {
						logger.error(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
						throw new RuntimeException(MESUREMENTS_DO_NOT_CONFORM_TO_DECLARED_METRICS);
					}		
				}
			}
		}
		
		for (ICalculatorListener l : this.listeners) {
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

	public String getCalculatorId() {
		return this.calculatorId;
	}

	public void setCalculatorId(String calculatorId) {
		this.calculatorId = calculatorId;
	}
}
