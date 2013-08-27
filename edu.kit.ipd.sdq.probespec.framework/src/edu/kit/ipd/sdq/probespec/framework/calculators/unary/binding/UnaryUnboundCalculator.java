package edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding;

import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface UnaryUnboundCalculator<IN, OUT> {

    public UnaryPartiallyBoundCalculator<OUT> bindInput(Probe<IN> inProbe);
    
}
