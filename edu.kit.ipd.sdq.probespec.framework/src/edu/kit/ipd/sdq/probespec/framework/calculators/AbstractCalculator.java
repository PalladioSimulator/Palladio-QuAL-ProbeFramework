package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.DerivedProbe;

public abstract class AbstractCalculator<OUT> implements ICalculator<OUT> {
    
    private final DerivedProbe<OUT> outputProbe;
    
    public AbstractCalculator(DerivedProbe<OUT> outputProbe) {
        this.outputProbe = outputProbe;
    }

    @Override
    public DerivedProbe<OUT> getOutputProbe() {
        return outputProbe;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [outputProbe=" + getOutputProbe() + "]";
    }
    
}
