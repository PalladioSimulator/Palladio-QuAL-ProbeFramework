package edu.kit.ipd.sdq.probespec.framework.calculators;

import edu.kit.ipd.sdq.probespec.framework.Calculator;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;

public abstract class AbstractCalculator<OUT> implements Calculator<OUT> {
    
    protected BlackboardWriter<OUT> outWriter;

    @Override
    public void setupBlackboardWriter(BlackboardWriter<OUT> writer) {
        this.outWriter = writer;
    }
    
}
