package de.uka.ipd.sdq.probespec.framework.calculator;

import static de.uka.ipd.sdq.probespec.framework.constants.MatchRuleConstants.CURRENT_TIME_MATCH_RULE;
import static de.uka.ipd.sdq.probespec.framework.constants.MatchRuleConstants.RESOURCE_DEMAND_MATCH_RULE;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.quantity.Quantity;
import javax.measure.unit.SI;

import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.exceptions.CalculatorException;

/**
 * Calculates the waiting time for resources in environments where the stop of
 * the waiting period cannot be observed directly. Rather the following values
 * (respectively events) should be observable.
 * <ul>
 * <li><code>start</code> - "request for processing"-event</li>
 * <li><code>stop</code> - "end of processing"-event (Notice: This is different
 * from the waiting period stop)</li>
 * <li><code>demand</code> - the demanded time</li>
 * </ul>
 * The waiting time results from calculating
 * <code>(stop - start) - demand </code>.
 * 
 * @author pmerkle, Sebastian Lehrig, Steffen Becker
 * 
 */
public class DemandBasedWaitingTimeCalculator extends WaitingTimeCalculator {

    public DemandBasedWaitingTimeCalculator(ProbeSpecContext ctx, Integer startWaitingProbeSetID,
            Integer stopProcessingProbeSetID) {
        super(ctx, startWaitingProbeSetID, stopProcessingProbeSetID);
    }
	
    /**
	 * @see
	 * de.uka.ipd.sdq.probespec.framework.calculator.Calculator#calculate
	 * (de.uka.ipd.sdq.probespec.framework.ProbeSetSample)
	 */
	@Override
	protected List<Measure<?, ? extends Quantity>> calculate(List<ProbeSetSample> probeSetSamples) throws CalculatorException {
		// raw measures
		Measure<Double, Duration> startTimeMeasure = obtainMeasure(probeSetSamples.get(0), CURRENT_TIME_MATCH_RULE);
		Measure<Double, Duration> demandMeasure = obtainMeasure(probeSetSamples.get(0), RESOURCE_DEMAND_MATCH_RULE);
		Measure<Double, Duration> endTimeMeasure = obtainMeasure(probeSetSamples.get(1), CURRENT_TIME_MATCH_RULE);		
		
		// time span
		double timeSpan = endTimeMeasure.doubleValue(SI.SECOND)-startTimeMeasure.doubleValue(SI.SECOND);
		
		// waiting time
		// TODO Check whether demands can be used as seconds. Note that
		//      the demand metric is a natural number metric and no time metric. 
		double waitingTime = timeSpan - demandMeasure.doubleValue(SI.SECOND);		
		if (waitingTime < 0) { // necessary due to double precision errors
				waitingTime = 0;
		}
		Measure<Double, Duration> waitingTimeMeasure = Measure.valueOf(waitingTime, SI.SECOND);
				
		List<Measure<?, ? extends Quantity>> result = new ArrayList<Measure<?, ? extends Quantity>>(2);
		
		result.add(waitingTimeMeasure);
		result.add(endTimeMeasure);

		return result;
	}

}
