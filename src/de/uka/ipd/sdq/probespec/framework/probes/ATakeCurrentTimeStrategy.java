package de.uka.ipd.sdq.probespec.framework.probes;

import javax.measure.quantity.Duration;

import org.jscience.mathematics.number.Number;

import de.uka.ipd.sdq.probespec.framework.ProbeSample;

/**
 * Represents an abstract measuring method whose subclasses takes the current
 * time.
 * 
 * @author pmerkle
 * 
 */
public abstract class ATakeCurrentTimeStrategy implements IProbeStrategy {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public abstract ProbeSample<? extends Number, Duration> takeSample(String probeId,
			Object... o);

}
