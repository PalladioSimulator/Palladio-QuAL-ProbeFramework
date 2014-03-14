package de.uka.ipd.sdq.probespec.framework.calculator;

import static de.uka.ipd.sdq.probespec.framework.constants.MatchRuleConstants.CURRENT_TIME_MATCH_RULE;

import java.util.LinkedList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.quantity.Quantity;
import javax.measure.unit.SI;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;

import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.ProbeType;
import de.uka.ipd.sdq.probespec.framework.exceptions.CalculatorException;
/**
 * Calculates a time span. It expects two ProbeSets each containing at least a
 * {@link ProbeType#CURRENT_TIME} probe.
 * 
 * @author Faber, Philipp Merkle, Sebastian Lehrig, Steffen Becker
 * 
 */
public abstract class TimeSpanCalculator extends BinaryCalculator {

    public TimeSpanCalculator(ProbeSpecContext ctx, List<MetricDescription> metricDescriptions, Integer startProbeSetID, Integer endProbeSetID) {
        super(ctx, metricDescriptions, startProbeSetID, endProbeSetID);
    }

    /**
	 * @see
	 * de.uka.ipd.sdq.probespec.framework.calculator.Calculator#calculate
	 * (de.uka.ipd.sdq.probespec.framework.ProbeSetSample)
	 */
    @Override
	protected List<Measure<?, ? extends Quantity>> calculate(List<ProbeSetSample> probeSetSamples) throws CalculatorException {
		List<Measure<?, ? extends Quantity>> result = new LinkedList<Measure<?, ? extends Quantity>>();
		
		Measure<Double, Duration> startTimeMeasure = obtainMeasure(probeSetSamples.get(0), CURRENT_TIME_MATCH_RULE);
		Measure<Double, Duration> endTimeMeasure = obtainMeasure(probeSetSamples.get(1), CURRENT_TIME_MATCH_RULE);		
		double timeSpan = endTimeMeasure.doubleValue(SI.SECOND)-startTimeMeasure.doubleValue(SI.SECOND);
		Measure<Double, Duration> timeSpanMeasure = Measure.valueOf(timeSpan, SI.SECOND);
		
		result.add(timeSpanMeasure);
		result.add(endTimeMeasure);
		
		return result;
	}
}
