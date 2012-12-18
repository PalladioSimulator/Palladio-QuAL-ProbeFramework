package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;

public abstract class AbstractCalculator<OUT> implements ICalculator<OUT> {
    
    private final DerivedProbe<OUT> outputProbe;
    
    protected IBlackboardWriter<OUT> outWriter;
    
    public AbstractCalculator(DerivedProbe<OUT> outputProbe) {
        this.outputProbe = outputProbe;
    }

    @Override
    public void setupBlackboardWriter(IBlackboardWriter<OUT> writer) {
        this.outWriter = writer;
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
