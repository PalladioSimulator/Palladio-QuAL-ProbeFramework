package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;

public interface Calculator<OUT> {

    public void setupBlackboardWriter(BlackboardWriter<OUT> writer);
    
    public void calculate(Probe<?> probe, MeasurementContext... contexts);
    
}
