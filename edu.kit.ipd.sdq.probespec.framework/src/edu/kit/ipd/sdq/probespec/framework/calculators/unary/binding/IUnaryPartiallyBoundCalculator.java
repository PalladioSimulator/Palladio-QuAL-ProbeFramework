package edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding;

import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface IUnaryPartiallyBoundCalculator<OUT> {
    
    public IUnaryBoundCalculator bindOutput(Probe<OUT> outProbe);
    
}
