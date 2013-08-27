package edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding;

import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface UnaryPartiallyBoundCalculator<OUT> {
    
    public UnaryBoundCalculator bindOutput(Probe<OUT> outProbe);
    
}