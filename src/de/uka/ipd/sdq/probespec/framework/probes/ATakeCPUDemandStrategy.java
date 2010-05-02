package de.uka.ipd.sdq.probespec.framework.probes;

import javax.measure.quantity.Duration;

import de.uka.ipd.sdq.probespec.framework.ProbeSample;

/**
 * Represents an abstract measuring method whose subclasses takes the demand of
 * an entity regarding to the specified CPU.
 * <p>
 * Usually the affected CPU is passed using the optional parameter of the
 * {@link #takeSample(String, Object...)} method.
 * 
 * @author pmerkle
 * 
 */
@Deprecated
public abstract class ATakeCPUDemandStrategy implements IProbeStrategy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract ProbeSample<Double, Duration> takeSample(
			String probeId, Object... o);

}
