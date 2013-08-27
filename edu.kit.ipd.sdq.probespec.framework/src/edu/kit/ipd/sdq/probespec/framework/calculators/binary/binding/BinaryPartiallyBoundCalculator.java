package edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding;

import edu.kit.ipd.sdq.probespec.framework.probes.Probe;


public interface BinaryPartiallyBoundCalculator<OUT> {
    
    public BinaryBoundCalculator bindOutput(Probe<OUT> outProbe);
    
}
