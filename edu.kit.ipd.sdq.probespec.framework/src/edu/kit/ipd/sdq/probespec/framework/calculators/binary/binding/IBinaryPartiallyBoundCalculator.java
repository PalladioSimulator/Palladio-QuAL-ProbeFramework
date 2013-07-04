package edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding;

import edu.kit.ipd.sdq.probespec.DerivedProbe;

public interface IBinaryPartiallyBoundCalculator<OUT> {
    
    public IBinaryBoundCalculator bindOutput(DerivedProbe<OUT> outProbe);
    
}
