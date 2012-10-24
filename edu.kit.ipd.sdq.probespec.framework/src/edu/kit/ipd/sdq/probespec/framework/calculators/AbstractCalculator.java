package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.ProbeSpecContext;

public class AbstractCalculator<OUT> {

    protected ProbeSpecContext ctx;
    
    private Probe<OUT> boundedProbe;
    
    public AbstractCalculator(ProbeSpecContext ctx) {
        this.ctx = ctx;
    }
    
    public void setBoundedProbe(Probe<OUT> probe) {
        this.boundedProbe = probe;
    }

    public Probe<OUT> getBoundedProbe() {
        return boundedProbe;
    }

}
