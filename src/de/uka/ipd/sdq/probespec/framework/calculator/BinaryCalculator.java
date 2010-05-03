package de.uka.ipd.sdq.probespec.framework.calculator;

import java.util.Vector;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSampleID;
import de.uka.ipd.sdq.probespec.framework.SampleBlackboard;
import de.uka.ipd.sdq.probespec.framework.exceptions.CalculatorException;

/**
 * This abstract class represents a binary calculator. A binary calculator
 * expects two probe sets, which are represented by their particular ID. The
 * first probe set is denoted start probe set, the latter is denoted end probe
 * set.
 * <p>
 * As soon as a sample arrives that originates from the end probe set, the
 * binary calculator does its calculation by invoking
 * {@link #calculate(ProbeSetSample, ProbeSetSample)}. When a sample originating
 * from the start probe set arrives, the calculator does nothing.
 * 
 * @author Philipp Merkle
 * @see Calculator
 * 
 */
public abstract class BinaryCalculator extends Calculator {

	private String startProbeSetID;

	private String endProbeSetID;

	public BinaryCalculator(SampleBlackboard blackboard,
			String startProbeSetID, String endProbeSetID) {
		super(blackboard);
		this.startProbeSetID = startProbeSetID;
		this.endProbeSetID = endProbeSetID;
	}

	abstract protected Vector<Measure<?, ? extends Quantity>> calculate(
			ProbeSetSample start, ProbeSetSample end)
			throws CalculatorException;

	@Override
	abstract protected Vector<MeasurementMetric> getConcreteMeasurementMetrics();

	/**
	 * This method is called by the
	 * {@link #update(java.util.Observable, Object)} method as soon as a new
	 * ProbeSetSample arrives at the blackboard (Observer Pattern). If
	 * <code>pss</code> is an end probe set sample, the method tries to get the
	 * corresponding start ProbeSetSample and invokes the
	 * {@link #calculate(ProbeSetSample, ProbeSetSample)} method. If
	 * <code>pss</code> is an start ProbeSetSample, this method will do nothing.
	 * <p>
	 * After the calculation the result is passed to the pipes-and-filters
	 * chain.
	 * <p>
	 * Here we make the assumption that the start ProbeSetSample always arrives
	 * before the end ProbeSetSample. Without this assumption all binary
	 * calculators would also have to try to get the end ProbeSetSample when the
	 * start ProbeSetSample arrives. Probably this would have a negative effect
	 * on the performance.
	 * 
	 * @param pss
	 *            the last ProbeSetSample which was added to the
	 *            SampleBlackboard and so triggered this Calculator.
	 */
	@Override
	protected void execute(ProbeSetSample pss) throws CalculatorException {
		/*
		 * Execute only if second ProbeSetSample arrives. Here we make the
		 * assumption that the start ProbeSetSample always arrives before the
		 * end ProbeSetSample. See JavaDoc comment above.
		 */
		if (endProbeSetID.equals(pss.getProbeSetSampleID().getProbeSetID())) {
			ProbeSetSample endSetSample = pss;
			ProbeSetSample startSetSample = blackboard
					.getProbeSetSample(new ProbeSetSampleID(startProbeSetID,
							pss.getProbeSetSampleID().getCtxID()));
			if (startSetSample != null) {
				Vector<Measure<?, ? extends Quantity>> resultTuple = calculate(
						startSetSample, endSetSample);

				passToPipe(resultTuple);
				fireCalculated(resultTuple);
			} else {
				throw new CalculatorException(
						"Could not access the corresponding start ProbeSetSample.");
			}
		}
	}

}
