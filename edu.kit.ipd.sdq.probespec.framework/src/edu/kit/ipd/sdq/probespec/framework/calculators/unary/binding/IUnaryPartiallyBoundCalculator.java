package edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding;

import edu.kit.ipd.sdq.probespec.framework.DerivedProbe;

public interface IUnaryPartiallyBoundCalculator<OUT> {
    
    public IUnaryBoundCalculator bindOutput(DerivedProbe<OUT> outProbe);
    
}
