package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface Calculator<OUT> {

    public void setupBlackboardWriter(BlackboardWriter<OUT> writer);
    
    public void calculate(Probe<?> probe, MeasurementContext... contexts);
    
}
