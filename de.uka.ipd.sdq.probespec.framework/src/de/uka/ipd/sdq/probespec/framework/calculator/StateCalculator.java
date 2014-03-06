package de.uka.ipd.sdq.probespec.framework.calculator;

import static de.uka.ipd.sdq.probespec.framework.constants.MatchRuleConstants.CURRENT_TIME_MATCH_RULE;
import static de.uka.ipd.sdq.probespec.framework.constants.MatchRuleConstants.RESOURCE_STATE_MATCH_RULE;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.ProbeType;
import de.uka.ipd.sdq.probespec.framework.constants.MeasurementMetricConstants;
import de.uka.ipd.sdq.probespec.framework.exceptions.CalculatorException;

/**
 * This class is a specific Calculator which composes a 2-tuple containing a
 * time stamp (first tuple element) and the state of a resource (second tuple
 * element). It needs one ProbeSet containing at least a CURRENT_TIME probe and
 * an {@link ProbeType#RESOURCE_STATE} probe.
 * 
 * @author Faber, Philipp Merkle, Sebastian Lehrig, Steffen Becker
 */
public class StateCalculator extends UnaryCalculator {	
	
	/**
     * Constructor. It takes a reference of the blackboard and the ID of the probe set element taken
     * from the model.
     * 
     * @param ctx
     *            the {@link ProbeSpecContext}
     * @param probeSetID
     *            ID of the probe set element from the model
     */
	public StateCalculator(ProbeSpecContext ctx, Integer probeSetID) {
		super(ctx, Arrays.asList(MeasurementMetricConstants.CPU_STATE_METRIC), probeSetID);
	}

	/**
	 * @see
	 * de.uka.ipd.sdq.probespec.framework.calculator.Calculator#calculate
	 * (de.uka.ipd.sdq.probespec.framework.ProbeSetSample)
	 */
	@Override
	protected List<Measure<?, ? extends Quantity>> calculate(List<ProbeSetSample> probeSetSamples) throws CalculatorException {
		List<Measure<?, ? extends Quantity>> result = new LinkedList<Measure<?, ? extends Quantity>>();
		result.add(obtainMeasure(probeSetSamples.get(0), CURRENT_TIME_MATCH_RULE));
		result.add(obtainMeasure(probeSetSamples.get(0), RESOURCE_STATE_MATCH_RULE));
		return result;
	}
}
