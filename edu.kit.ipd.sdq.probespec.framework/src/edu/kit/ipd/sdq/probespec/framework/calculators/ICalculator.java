package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

public interface ICalculator<OUT> {

    public OUT calculate(Probe<?> probe, IMeasurementContext... contexts);
    
    public DerivedProbe<OUT> getOutputProbe();
    
}
