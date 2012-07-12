package de.uka.ipd.sdq.probespec.framework;

import java.util.List;

import javax.measure.quantity.Quantity;

public interface IBlackboardListener {

	public BlackboardVote sampleArrived(List<ProbeSample<?, ? extends Quantity>> samples,
			RequestContext requestContextID, Integer probeSetId);
	
}
