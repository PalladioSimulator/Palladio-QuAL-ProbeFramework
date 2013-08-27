package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;

public interface Calculator<OUT> {

    public void setupBlackboardWriter(BlackboardWriter<OUT> writer);
    
    public void calculate(Probe<?> probe, MeasurementContext... contexts);
    
}
