package org.palladiosimulator.probespec.framework.calculator.internal;

import java.util.Arrays;

import org.palladiosimulator.edp2.models.ExperimentData.MetricDescription;
import org.palladiosimulator.probespec.framework.ProbeSpecContext;
import org.palladiosimulator.probespec.framework.measurements.MeasurementSet;
import org.palladiosimulator.probespec.framework.probes.Probe;

/**
 * <p>This abstract class represents a unary calculator. A unary calculator
 * expects one probe set, which is represented by its particular ID.</p>
 * 
 * <p>As soon as a sample arrives that originates from this probe set, the
 * unary calculator does its calculation by invoking the template method
 * {@link #calculate(MeasurementSet, MeasurementSet)}.</p>
 * 
 * @author Sebastian Lehrig, Steffen Becker
 */
public abstract class UnaryCalculator extends NaryCalculator {

    protected UnaryCalculator(final ProbeSpecContext ctx, final MetricDescription metricDescription, final Probe childProbe) {
        super(ctx, metricDescription, Arrays.asList(childProbe));
    }

}