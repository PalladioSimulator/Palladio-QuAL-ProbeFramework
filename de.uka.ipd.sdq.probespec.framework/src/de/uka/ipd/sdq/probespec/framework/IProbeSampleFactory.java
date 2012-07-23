package de.uka.ipd.sdq.probespec.framework;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

public interface IProbeSampleFactory {

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
	public abstract <V, Q extends Quantity> ProbeSample<V, Q> createSample(
			final Measure<V, Q> measure, final String probeID,
			final ProbeType probeType);

	/**
	 * Deletes the specified {@link ProbeSample} by setting its values to null and storing
	 * it in the object pool.
	 * 
	 * @param sample
	 */
	public abstract <V, Q extends Quantity> void deleteSample(
			ProbeSample<V, Q> sample);
	
	/**
	 * Clears and restores the factory.
	 */
	public abstract void clear();

}