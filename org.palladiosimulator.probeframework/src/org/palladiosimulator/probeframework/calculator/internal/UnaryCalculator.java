package org.palladiosimulator.probeframework.calculator.internal;

import java.util.Arrays;

import org.palladiosimulator.measurementspec.MeasurementTupple;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * <p>This abstract class represents a unary calculator. A unary calculator
 * expects one probe set, which is represented by its particular ID.</p>
 * 
 * <p>As soon as a sample arrives that originates from this probe set, the
 * unary calculator does its calculation by invoking the template method
 * {@link #calculate(MeasurementTupple, MeasurementTupple)}.</p>
 * 
 * @author Sebastian Lehrig, Steffen Becker
 */
public abstract class UnaryCalculator extends NaryCalculator {

    protected UnaryCalculator(final ProbeFrameworkContext ctx, final MetricDescription metricDescription, final Probe childProbe) {
        super(ctx, metricDescription, Arrays.asList(childProbe));
    }

}
