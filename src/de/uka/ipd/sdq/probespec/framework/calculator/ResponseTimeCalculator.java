package de.uka.ipd.sdq.probespec.framework.calculator;

import java.util.Vector;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.quantity.Quantity;
import javax.measure.unit.SI;

import de.uka.ipd.sdq.pipesandfilters.framework.CaptureType;
import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.pipesandfilters.framework.Scale;
import de.uka.ipd.sdq.probespec.framework.IMatchRule;
import de.uka.ipd.sdq.probespec.framework.ProbeSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeType;
import de.uka.ipd.sdq.probespec.framework.ProbeTypeMatchRule;
import de.uka.ipd.sdq.probespec.framework.SampleBlackboard;
import de.uka.ipd.sdq.probespec.framework.exceptions.CalculatorException;

/**
 * Calculates a time span representing the response time. It needs two ProbeSets
 * each containing at least a CURRENT_TIME probe.
 * 
 * @author Faber, Philipp Merkle
 * 
 */
public class ResponseTimeCalculator extends BinaryCalculator {

	private static Vector<MeasurementMetric> concreteMeasurementMetrics;

	/**
	 * Default Constructor.
	 * 
	 * @param blackboard
	 *            the blackboard this calculator will observe
	 * @param startProbeSetID
	 *            ID of the start probe set element from the model
	 * @param endProbeSetID
	 *            ID of the end probe set element from the model
	 */
	public ResponseTimeCalculator(SampleBlackboard blackboard,
			String startProbeSetID, String endProbeSetID) {
		super(blackboard, startProbeSetID, endProbeSetID);
	}

	/**
	 * Calculates the response time.
	 * 
	 * @param start
	 *            the ProbeSample of the start ProbeSet (ProbeType.CURRENT_TIME)
	 * @param end
	 *            the ProbeSample of the end ProbeSet (ProbeType.CURRENT_TIME)
	 */
	@Override
	protected Vector<Measure<?, ? extends Quantity>> calculate(
			ProbeSetSample start, ProbeSetSample end)
			throws CalculatorException {
		// Obtain start time sample
		ProbeSample<Double, Duration> startTimeSample = obtainCurrentTimeProbeSample(start);
		if (startTimeSample == null) {
			throw new CalculatorException(
					"Could not access start probe sample.");
		}

		// Obtain end time sample
		ProbeSample<Double, Duration> endTimeSample = obtainCurrentTimeProbeSample(end);
		if (endTimeSample == null) {
			throw new CalculatorException("Could not access end probe sample.");
		}

		// Calculate response time
		double endTime = endTimeSample.getMeasure().doubleValue(
				startTimeSample.getMeasure().getUnit());
		double startTime = startTimeSample.getMeasure().doubleValue(
				startTimeSample.getMeasure().getUnit());
		double responseTime = endTime - startTime;

		// Create result tuple
		Measure<Double, Duration> result = Measure.valueOf(responseTime,
				startTimeSample.getMeasure().getUnit());
		Vector<Measure<?, ? extends Quantity>> resultTuple = new Vector<Measure<?, ? extends Quantity>>();
		resultTuple.add(result);

		return resultTuple;
	}

	@SuppressWarnings("unchecked")
	private ProbeSample<Double, Duration> obtainCurrentTimeProbeSample(
			ProbeSetSample probeSetSample) {
		IMatchRule[] rules = new IMatchRule[1];
		rules[0] = new ProbeTypeMatchRule(ProbeType.CURRENT_TIME);
		Vector<ProbeSample<?, ? extends Quantity>> result = probeSetSample
				.getProbeSamples(rules);

		if (result != null && result.size() > 0)
			return (ProbeSample<Double, Duration>) result.get(0);

		return null;
	}

	/**
	 * Initializes the metric information for the result of this calculator
	 * type. The method is called by the constructor of the super class.
	 */
	@Override
	protected synchronized Vector<MeasurementMetric> getConcreteMeasurementMetrics() {
		if (concreteMeasurementMetrics == null) {
			concreteMeasurementMetrics = new Vector<MeasurementMetric>();
			MeasurementMetric mm = new MeasurementMetric(
					CaptureType.NATURAL_NUMBER, SI.MILLI(SI.SECOND),
					Scale.ORDINAL);
			mm.setDescription("This measure represents the response time");
			mm.setMonotonic(false);
			mm.setName("Response Time");
			mm.setStrongMonotonic(false);
			concreteMeasurementMetrics.add(mm);
		}
		return concreteMeasurementMetrics;
	}

}
