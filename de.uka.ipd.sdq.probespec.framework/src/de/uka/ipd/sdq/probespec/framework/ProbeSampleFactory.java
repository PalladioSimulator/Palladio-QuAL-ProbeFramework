package de.uka.ipd.sdq.probespec.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

/**
 * Handles the creation and deletion of {@link ProbeSample}s.
 * {@link ProbeSample}s should only be created and deleted over this factory!
 * 
 * It internally holds an object pool of {@link ProbeSample}s and reuses them to bare with 
 * unnecessarily much object instantiations.
 * 
 * @author Daniel.Schmidt
 *
 */
public class ProbeSampleFactory {

	private static ProbeSampleFactory factory;
	private List<ProbeSample<?, ?>> pool;
	
	/**
	 * C'tor
	 * 
	 * Initializes the object pool.
	 */
	private ProbeSampleFactory() {
		pool = Collections.synchronizedList(new ArrayList<ProbeSample<?, ?>>());
	}
	
	public static synchronized ProbeSampleFactory getFactory() {
		if(factory == null) {
			factory = new ProbeSampleFactory();
		}
		
		return factory;
	}
	
	/**
	 * Creates a {@link ProbeSample} with the given measure, probe id and type.
	 * 
	 * In contrast to an usual instantiation the method tries to reuse a {@link ProbeSample} out of the pool.
	 * New objects are created only if the pool is currently empty.
	 * 
	 * @param measure
	 * @param probeID
	 * @param probeType
	 * @return a {@link ProbeSample} with the given measure, id and type.
	 */
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
	
	/**
	 * Deletes the specified {@link ProbeSample} by setting its values to null and storing
	 * it in the object pool.
	 * 
	 * @param sample
	 */
	public <V, Q extends Quantity> void deleteSample(ProbeSample<V,Q> sample) {
		sample.setMeasure(null);
		sample.setProbeID(null);
		sample.setProbeType(null);
		
		pool.add(sample);
	}
}
