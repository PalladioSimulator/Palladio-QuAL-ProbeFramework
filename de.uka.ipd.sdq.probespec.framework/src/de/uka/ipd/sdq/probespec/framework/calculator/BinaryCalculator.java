package de.uka.ipd.sdq.probespec.framework.calculator;

import java.util.Arrays;
import java.util.List;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;

import de.uka.ipd.sdq.probespec.framework.BlackboardVote;
import de.uka.ipd.sdq.probespec.framework.ProbeSetAndRequestContext;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.utils.ProbeSpecUtils;

/**
 * This abstract class represents a binary calculator. A binary calculator
 * expects two probe sets, which are represented by their particular ID. The
 * first probe set is denoted start probe set, the latter is denoted end probe
 * set.
 * <p>
 * As soon as a sample arrives that originates from the end probe set, the
 * binary calculator does its calculation by invoking the template method
 * {@link #calculate(ProbeSetSample, ProbeSetSample)}. When a sample originating
 * from the start probe set arrives, the calculator does nothing.
 * 
 * @author Philipp Merkle, Sebastian Lehrig, Steffen Becker
 * @see Calculator
 * 
 */
public abstract class BinaryCalculator extends Calculator { 	
	
	private final Integer startProbeSetID;

	private final Integer endProbeSetID;

    public BinaryCalculator(ProbeSpecContext ctx, List<MetricDescription> metricDescriptions, Integer startProbeSetID, Integer endProbeSetID) {
	    super(ctx, metricDescriptions);
		this.startProbeSetID = startProbeSetID;
		this.endProbeSetID = endProbeSetID;
		
		ctx.getSampleBlackboard().addBlackboardListener(this, startProbeSetID, endProbeSetID);
	}

	/**
	 * <p>This method is called by the {@link #update(java.util.Observable, Object)} 
	 * method as soon as a new ProbeSetSample arrives at the blackboard (Observer
	 * Pattern). If <code>probeSetSample</code> is an end probe set sample, the 
	 * method tries to get the corresponding start ProbeSetSample and invokes the
	 * {@link #calculate(ProbeSetSample, ProbeSetSample)} method. If
	 * <code>probeSetSample</code> is an start ProbeSetSample, this method will
	 * do nothing.</p>
	 * 
	 * <p> After the calculation the result is passed to the pipes-and-filters
	 * chain.</p>
	 * 
	 * <p>Here, we make the assumption that the start ProbeSetSample always arrives
	 * before the end ProbeSetSample. Without this assumption all binary
	 * calculators would also have to try to get the end ProbeSetSample when the
	 * start ProbeSetSample arrives. Probably this would have a negative effect
	 * on the performance.</p>
	 * 
	 * TODO Move calculate into the calculator class using a List of ProbeSetSample.
	 *      Then, enable checking of list size and consistency between metrics and
	 *      measures.
	 * 
	 * @param probeSetSample
	 *            the last ProbeSetSample which was added to the
	 *            SampleBlackboard and so triggered this Calculator.
	 * @see
	 * de.uka.ipd.sdq.probespec.framework.calculator.Calculator#execute
	 * (de.uka.ipd.sdq.probespec.framework.ProbeSetSample)
	 */
	@Override
	public BlackboardVote sampleArrived(ProbeSetSample probeSetSample) {
		int probeSetID = probeSetSample.getProbeSetAndRequestContext().getProbeSetID();
		
		if (endProbeSetID.equals(probeSetID)) {
			ProbeSetSample endSetSample = probeSetSample;
			ProbeSetSample startSetSample = getProbeSpecContext().getSampleBlackboard().getSample(
					new ProbeSetAndRequestContext(startProbeSetID, probeSetSample
							.getProbeSetAndRequestContext().getCtxID()));
			if (startSetSample != null) {
				fireCalculated(Arrays.asList(startSetSample, endSetSample));
			} else {
                throw new RuntimeException("Could not find sample for ProbeSetID "
                        + ProbeSpecUtils.ProbeSetIdToString(startProbeSetID, this.getProbeSpecContext())
                        + " within context " + probeSetSample.getProbeSetAndRequestContext().getCtxID());
			}
			return BlackboardVote.DISCARD;
		} else if (startProbeSetID.equals(probeSetID)) {
			return BlackboardVote.RETAIN;
		} else {
			return BlackboardVote.DISCARD;
		}
	}

}
