package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface ICalculator<OUT> {

    public void setupBlackboardWriter(IBlackboardWriter<OUT> writer);
    
    public void calculate(Probe<?> probe, IMeasurementContext... contexts);
    
}
