package de.uka.ipd.sdq.probespec.framework;

import java.util.List;

import javax.measure.quantity.Quantity;

/**
 * This blackboard discards any measurement that is published at the blackboard. Thus, the
 * {@link #getSample(ProbeSetAndRequestContext)} method returns always null.
 * <p>
 * Use this blackboard, when no measurements are supposed to be stored.
 * 
 * @author Philipp Merkle
 * 
 */
public class NullSampleBlackboard implements ISampleBlackboard {

    @Override
    public void addBlackboardListener(IBlackboardListener l, Integer... topics) {
        // nothing to do
    }

	@Override
	public void addSample(ProbeSample<?, ? extends Quantity> sample,
			RequestContext requestContextID, Integer probeSetId) {
		// nothing to do
	}
	
    @Override
    public void addSample(List<ProbeSample<?, ? extends Quantity>> samples,
			RequestContext requestContextID, Integer probeSetId) {
        // nothing to do
    }

    @Override
    public void deleteSampleSet(RequestContext requestContext, Integer probeSetID) {
        // nothing to do
    }

    @Override
    public void deleteSamplesInRequestContext(RequestContext requestContext) {
        // nothing to do
    }

    @Override
    public ProbeSetSample getSample(RequestContext requestContext, Integer probeSetID) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

}
