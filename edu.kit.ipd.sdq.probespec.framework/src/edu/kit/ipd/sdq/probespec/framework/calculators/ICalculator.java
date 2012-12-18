package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;

public interface ICalculator<OUT> {

    public void setupBlackboardWriter(IBlackboardWriter<OUT> writer);
    
    public void calculate(Probe<?> probe, IMeasurementContext... contexts);
    
    public DerivedProbe<OUT> getOutputProbe();
    
}
