package edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding;

import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface BinaryUnboundCalculator<IN1, IN2, OUT> {

    public BinaryPartiallyBoundCalculator<OUT> bindInput(Probe<IN1> in1Probe, Probe<IN2> in2Probe);
    
}
