package de.uka.ipd.sdq.probespec.framework.calculator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.palladiosimulator.edp2.models.ExperimentData.MetricSetDescription;

import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet;
import de.uka.ipd.sdq.probespec.framework.probes.Probe;

/**
 * This class is a specific Calculator which composes a 2-tuple containing a time stamp (first tuple
 * element) and the state of a resource (second tuple element). It needs one ProbeSet containing at
 * least a CURRENT_TIME probe and an {@link ProbeType#RESOURCE_STATE} probe.
 * 
 * @author Faber, Philipp Merkle, Sebastian Lehrig, Steffen Becker
 */
public class StateCalculator extends UnaryCalculator {

    /**
     * Constructor. It takes a reference of the blackboard and the ID of the probe set element taken
     * from the model.
     * 
     * @param ctx
     *            the {@link ProbeSpecContext}
     * @param probeSetID
     *            ID of the probe set element from the model
     */
    public StateCalculator(final ProbeSpecContext ctx, final Probe probe) {
        super(ctx, Calculator.createMetricSetDescription(Arrays.asList(MetricDescriptionConstants.CPU_STATE_METRIC)), probe);
    }

    /**
     * @see de.uka.ipd.sdq.probespec.framework.calculator.Calculator#calculate
     *      (de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet)
     */
    @Override
    protected Measurement calculate(final List<Measurement> probeMeasurements) {
        final List<Measurement> result = new LinkedList<Measurement>();
        result.add(obtainMeasurement(probeMeasurements.get(0), MetricDescriptionConstants.POINT_IN_TIME_METRIC));
        result.add(obtainMeasurement(probeMeasurements.get(0), MetricDescriptionConstants.CPU_STATE_METRIC));
        return new MeasurementSet(result, (MetricSetDescription) this.metricDesciption, this);
    }
}
