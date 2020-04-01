package org.palladiosimulator.probeframework.calculator;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.probes.ProbeConfiguration;

/**
 * Factory interface to create calculator objects. This factory was created to
 * support extending calculators for additional metrics, as well as provide new
 * types of calculators.
 */
public interface IGenericCalculatorFactory {

    /**
     * Creates a new Calculator for the given MetricDescription
     * 
     * @param metric             the metric which is calculated based on the probe
     *                           measurements.
     * @param measuringPoint     the measuring point where this calculator is used
     * @param probeConfiguration the configuration of probes required by the
     *                           calculator of the metric.
     * @return the desired calculator
     */
    public Calculator buildCalculator(MetricDescription metric, MeasuringPoint measuringPoint,
            ProbeConfiguration probeConfiguration);
    
}
