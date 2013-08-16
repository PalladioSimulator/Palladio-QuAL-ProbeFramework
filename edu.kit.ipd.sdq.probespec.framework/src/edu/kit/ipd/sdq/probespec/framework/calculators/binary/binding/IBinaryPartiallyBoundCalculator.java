package edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding;

import edu.kit.ipd.sdq.probespec.framework.Probe;


public interface IBinaryPartiallyBoundCalculator<OUT> {
    
    public IBinaryBoundCalculator bindOutput(Probe<OUT> outProbe);
    
}
