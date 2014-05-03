package org.palladiosimulator.probeframework.calculator.internal;

import java.util.Arrays;

import org.palladiosimulator.measurementspec.MeasurementTupple;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * <p>
 * This abstract class represents a unary calculator. Unary calculators expect
 * exactly one probe, thus, restricting n-ary calculators to n=1.
 * </p>
 * 
 * <p>
 * As soon as a sample arrives that originates from this probe set, the unary
 * calculator does its calculation by invoking the template method
 * {@link #calculate(MeasurementTupple, MeasurementTupple)}.
 * </p>
 * 
 * @author Sebastian Lehrig, Steffen Becker
 */
public abstract class UnaryCalculator extends NaryCalculator {

	protected UnaryCalculator(final ProbeFrameworkContext ctx,
			final MetricDescription metricDescription, final Probe childProbe) {
		super(ctx, metricDescription, Arrays.asList(childProbe));
	}

}
