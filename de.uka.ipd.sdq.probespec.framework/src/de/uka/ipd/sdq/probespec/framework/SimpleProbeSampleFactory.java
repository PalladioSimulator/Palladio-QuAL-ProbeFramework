package de.uka.ipd.sdq.probespec.framework;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

/**
 * Simple {@link IProbeSampleFactory} that creates new {@link ProbeSample}s for
 * each method call. Deletion of {@link ProbeSample}s have no effect and are
 * handled by the garbage collector.
 * 
 * 
 * @author Daniel.Schmidt
 */
public class SimpleProbeSampleFactory implements IProbeSampleFactory {

	@Override
	public <V, Q extends Quantity> ProbeSample<V, Q> createSample(
			Measure<V, Q> measure, String probeID, ProbeType probeType) {
		return new ProbeSample<V, Q>(measure, probeID, probeType);
	}

	@Override
	public <V, Q extends Quantity> void deleteSample(ProbeSample<V, Q> sample) {
		//do nothing
	}

	@Override
	public void clear() {
		// do nothing
	}

}
