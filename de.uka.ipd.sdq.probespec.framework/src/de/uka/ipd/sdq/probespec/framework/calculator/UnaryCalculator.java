package de.uka.ipd.sdq.probespec.framework.calculator;

import java.util.List;
import java.util.Vector;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import de.uka.ipd.sdq.pipesandfilters.framework.MeasurementMetric;
import de.uka.ipd.sdq.probespec.framework.BlackboardVote;
import de.uka.ipd.sdq.probespec.framework.ProbeSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSetSample;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.RequestContext;
import de.uka.ipd.sdq.probespec.framework.exceptions.CalculatorException;

public abstract class UnaryCalculator extends Calculator {

	private Integer probeSetID;
	
    protected UnaryCalculator(ProbeSpecContext ctx, Integer probeSetID) {
        super(ctx);
        this.probeSetID = probeSetID;
     
        ctx.getSampleBlackboard().addBlackboardListener(this, probeSetID);
    }
	
	abstract protected Vector<Measure<?, ? extends Quantity>> calculate(
			List<ProbeSample<?, ? extends Quantity>> samples)
			throws CalculatorException;
	
	@Override
	abstract protected Vector<MeasurementMetric> getConcreteMeasurementMetrics();

	@Override
	protected BlackboardVote execute(List<ProbeSample<?, ? extends Quantity>> samples,
			RequestContext requestContextID, Integer probeSetId) throws CalculatorException {
		if (probeSetID.equals(probeSetId)) {
			Vector<Measure<?, ? extends Quantity>> resultTuple = calculate(samples);
			fireCalculated(resultTuple);
		}
		return BlackboardVote.DISCARD;
	}

}
