package de.uka.ipd.sdq.probespec.framework.calculator;

import java.util.Arrays;
import java.util.List;

import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.probespec.framework.BlackboardVote;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;

/**
 * <p>This abstract class represents a unary calculator. A unary calculator
 * expects one probe set, which is represented by its particular ID.</p>
 * 
 * <p>As soon as a sample arrives that originates from this probe set, the
 * unary calculator does its calculation by invoking the template method
 * {@link #calculate(ProbeSetSample, ProbeSetSample)}.</p>
 * 
 * @author Sebastian Lehrig, Steffen Becker
 */
public abstract class UnaryCalculator extends Calculator {

	private final Integer probeSetID;	
	
    protected UnaryCalculator(ProbeSpecContext ctx, List<MeasurementMetric> measurementMetrics, Integer probeSetID) {
        super(ctx, measurementMetrics);
        this.probeSetID = probeSetID;
     
        ctx.getSampleBlackboard().addBlackboardListener(this, probeSetID);
    }
	
	/**
	 * @see
	 * de.uka.ipd.sdq.probespec.framework.calculator.Calculator#execute
	 * (de.uka.ipd.sdq.probespec.framework.ProbeSetSample)
	 */
	@Override
	public BlackboardVote sampleArrived(ProbeSetSample probeSetSample) {
		int probeSetID = probeSetSample.getProbeSetAndRequestContext().getProbeSetID();
		
		if (this.probeSetID.equals(probeSetID)) {
			fireCalculated(Arrays.asList(probeSetSample));
		}
		
		return BlackboardVote.DISCARD;
	}
}
