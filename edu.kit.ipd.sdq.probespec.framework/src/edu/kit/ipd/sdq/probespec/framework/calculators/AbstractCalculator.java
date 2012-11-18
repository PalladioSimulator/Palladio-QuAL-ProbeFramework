package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.Probe;

public class AbstractCalculator<OUT> implements ICalculator<OUT> {
    
    private final Probe<OUT> derivedProbe;
    
    public AbstractCalculator(Probe<OUT> derivedProbe) {
        this.derivedProbe = derivedProbe;
    }

    @Override
    public Probe<OUT> getDerivedProbe() {
        return derivedProbe;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [derivedProbe=" + getDerivedProbe() + "]";
    }
    
}
