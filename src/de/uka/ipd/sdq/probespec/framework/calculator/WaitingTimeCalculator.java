package de.uka.ipd.sdq.probespec.framework.calculator;

import java.util.Vector;

import javax.measure.unit.SI;

import de.uka.ipd.sdq.pipesandfilters.framework.CaptureType;
import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.pipesandfilters.framework.Scale;
import de.uka.ipd.sdq.probespec.framework.ProbeType;
import de.uka.ipd.sdq.probespec.framework.SampleBlackboard;

/**
 * Calculates a time span representing the waiting time. It expects two
 * ProbeSets each containing at least a {@link ProbeType#CURRENT_TIME} probe.
 * 
 * @author Faber, Philipp Merkle
 * @see BinaryCalculator
 * @see Calculator
 */
public class WaitingTimeCalculator extends TimeSpanCalculator {

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
	public WaitingTimeCalculator(SampleBlackboard blackboard,
			String startProbeSetID, String endProbeSetID) {
		super(blackboard, startProbeSetID, endProbeSetID);
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
			mm.setDescription("This measure represents the waiting time");
			mm.setMonotonic(false);
			mm.setName("Waiting Time");
			mm.setStrongMonotonic(false);
			concreteMeasurementMetrics.add(mm);
		}
		return concreteMeasurementMetrics;
	}

}
