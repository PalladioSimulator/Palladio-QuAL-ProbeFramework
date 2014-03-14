package de.uka.ipd.sdq.probespec.framework.calculator;

import static de.uka.ipd.sdq.probespec.framework.constants.MatchRuleConstants.CURRENT_TIME_MATCH_RULE;
import static de.uka.ipd.sdq.probespec.framework.constants.MatchRuleConstants.RESOURCE_DEMAND_MATCH_RULE;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.exceptions.CalculatorException;

/**
 * This class is a specific Calculator which composes a 2-tuple containing a
 * time stamp (first tuple element) and the CPU demand of an entity (second
 * tuple element). It needs one ProbeSet containing at least a CURRENT_TIME
 * probe and a CPU_RESOURCE_DEMAND probe.
 * 
 * @author Faber, Philipp Merkle, Sebastian Lehrig, Steffen Becker
 * 
 */
public class DemandCalculator extends UnaryCalculator {

	/**
     * Constructor for the CPUDemandCalculator. It takes a reference of the blackboard and the ID of
     * the probe set element taken from the model.
     * 
     * @param ctx
     *            the {@link ProbeSpecContext}
     * @param probeSetID
     *            ID of the probe set element from the model
     */
    public DemandCalculator(ProbeSpecContext ctx, Integer probeSetID) {
        super(ctx, Arrays.asList(MetricDescriptionConstants.RESOURCE_DEMAND_METRIC), probeSetID);
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
		result.add(obtainMeasure(probeSetSamples.get(0), RESOURCE_DEMAND_MATCH_RULE));
		return result;
	}
}
