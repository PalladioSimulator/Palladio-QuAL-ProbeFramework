package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;

public abstract class AbstractCalculator<OUT> implements ICalculator<OUT> {
    
    protected IBlackboardWriter<OUT> outWriter;

    @Override
    public void setupBlackboardWriter(IBlackboardWriter<OUT> writer) {
        this.outWriter = writer;
    }
    
}
