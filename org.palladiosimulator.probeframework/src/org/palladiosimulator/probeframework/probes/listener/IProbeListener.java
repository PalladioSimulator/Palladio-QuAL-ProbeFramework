package org.palladiosimulator.probeframework.probes.listener;

import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;

public interface IProbeListener {

    public void newProbeMeasurementAvailable(ProbeMeasurement newMeasurement);

}
