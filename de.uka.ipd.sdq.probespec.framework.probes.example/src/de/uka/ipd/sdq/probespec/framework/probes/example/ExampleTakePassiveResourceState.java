package de.uka.ipd.sdq.probespec.framework.probes.example;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;

import de.uka.ipd.sdq.probespec.framework.measurements.BasicMeasurement;

public class ExampleTakePassiveResourceState implements IProbeStrategy {

	/**
	 * @param o
	 *            expects a {@link ASimpleActiveResource}
	 */
	@Override
	public BasicMeasurement<Integer, Dimensionless> takeSample(String probeId,
			Object... o) {
		ASimplePassiveResource res = null;
		if (o[0] instanceof ASimplePassiveResource) {
			res = (ASimplePassiveResource) o[0];
		} else {
			throw new IllegalArgumentException("Expected an argument of type "
					+ ASimplePassiveResource.class.getSimpleName() + ".");
		}

		Measure<Integer, Dimensionless> free = Measure.valueOf(res.getFree(),
				Dimensionless.UNIT);
		BasicMeasurement<Integer, Dimensionless> sample = new BasicMeasurement<Integer, Dimensionless>(
				free, probeId, ProbeType.RESOURCE_STATE);

		return sample;
	}

}
