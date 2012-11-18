package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.Probe;

public interface ICalculator<OUT> {

    public Probe<OUT> getDerivedProbe();
    
}
