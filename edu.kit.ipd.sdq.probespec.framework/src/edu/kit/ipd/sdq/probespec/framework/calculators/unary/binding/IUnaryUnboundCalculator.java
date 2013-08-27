package edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding;

import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface IUnaryUnboundCalculator<IN, OUT> {

    public IUnaryPartiallyBoundCalculator<OUT> bindInput(Probe<IN> inProbe);
    
}
