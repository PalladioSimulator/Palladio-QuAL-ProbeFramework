package de.uka.ipd.sdq.probespec.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

/**
 * Realization of an {@link IProbeSampleFactory} that handles the creation and 
 * deletion of {@link ProbeSample}s.
 * 
 * It internally holds an object pool of {@link ProbeSample}s and reuses them to bare with 
 * unnecessarily much object instantiations.
 * 
 * @author Daniel.Schmidt
 *
 */
public class ProbeSampleFactoryWithObjectPool implements IProbeSampleFactory {

	private List<ProbeSample<?, ?>> pool;
	
	/**
	 * C'tor
	 * 
	 * Initializes the object pool.
	 */
	public ProbeSampleFactoryWithObjectPool() {
		pool = Collections.synchronizedList(new ArrayList<ProbeSample<?, ?>>());
	}
	
	/* (non-Javadoc)
	 * @see de.uka.ipd.sdq.probespec.framework.IProbeSampleFactory#createSample(javax.measure.Measure, java.lang.String, de.uka.ipd.sdq.probespec.framework.ProbeType)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <V, Q extends Quantity> ProbeSample<V,Q> createSample(final Measure<V,Q> measure, final String probeID, final ProbeType probeType) {
		ProbeSample<V,Q> sample;
		if(pool.isEmpty()) {
			sample = new ProbeSample<V,Q>(measure, probeID, probeType);
		} else {
			sample = (ProbeSample<V, Q>) pool.remove(0);
			sample.setMeasure(measure);
			sample.setProbeID(probeID);
			sample.setProbeType(probeType);
		}
		
		return sample;
	}
	
	/* (non-Javadoc)
	 * @see de.uka.ipd.sdq.probespec.framework.IProbeSampleFactory#deleteSample(de.uka.ipd.sdq.probespec.framework.ProbeSample)
	 */
	@Override
	public <V, Q extends Quantity> void deleteSample(ProbeSample<V,Q> sample) {
		pool.add(sample);
	}

	@Override
	public void clear() {
		pool.clear();
	}
}
