package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.DerivedProbe;

public interface ICalculator<OUT> {

    public DerivedProbe<OUT> getOutputProbe();
    
}
