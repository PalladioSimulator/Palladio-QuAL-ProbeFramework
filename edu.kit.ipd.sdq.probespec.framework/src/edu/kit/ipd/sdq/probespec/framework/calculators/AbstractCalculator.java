package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.Probe;

public class AbstractCalculator<OUT> {
    
    private final Probe<OUT> boundedProbe;
    
    public AbstractCalculator(Probe<OUT> boundedProbe) {
        this.boundedProbe = boundedProbe;
    }

    public Probe<OUT> getBoundedProbe() {
        return boundedProbe;
    }

}
