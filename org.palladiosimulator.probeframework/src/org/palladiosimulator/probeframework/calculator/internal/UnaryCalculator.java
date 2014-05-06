package org.palladiosimulator.probeframework.calculator.internal;

import java.util.Arrays;

import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * <p>
 * This abstract class represents a unary calculator. Unary calculators expect exactly one probe,
 * thus, restricting calculators to only one probe.
 * </p>
 * 
 * @author Sebastian Lehrig, Steffen Becker
 */
public abstract class UnaryCalculator extends Calculator {

    /**
     * Default constructor. Restricts number of observed probes to exactly one.
     * 
     * @param context
     *            ProbeFrameworkContext as needed by the superclass.
     * @param metricDescription
     *            MetricDescriptions as needed by the superclass.
     * @param childProbe
     *            The observed probe.
     */
    protected UnaryCalculator(final ProbeFrameworkContext context, final MetricDescription metricDescription,
            final Probe childProbe) {
        super(context, metricDescription, Arrays.asList(childProbe));
    }

}
