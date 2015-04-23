package org.palladiosimulator.probeframework.calculator.internal;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC_TUPLE;

import java.util.List;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.probeframework.probes.Probe;

/**
 * Calculates a time span representing the reconfiguration time as defined by the
 * RECONFIGURATION_TIME_METRIC_TUPLE. It expects a probe giving the start and a probe giving the end
 * point in time of an reconfiguration. The final result is a (start point in time, reconfiguration
 * time)-tuple.
 * 
 * @author Matthias Becker
 * @see TimeSpanCalculator
 */
public class ReconfigurationTimeCalculator extends TimeSpanCalculator {

    /**
     * Default Constructor.
     * 
     * @param measuringPoint
     *            MeasuringPoint as needed by the superclass.
     * @param probes
     *            The two probes for starting point of the operation call and final point of the
     *            operation call.
     */
    public ReconfigurationTimeCalculator(final MeasuringPoint measuringPoint, final List<Probe> probes) {
        super(RECONFIGURATION_TIME_METRIC_TUPLE, measuringPoint, probes);
    }

}
