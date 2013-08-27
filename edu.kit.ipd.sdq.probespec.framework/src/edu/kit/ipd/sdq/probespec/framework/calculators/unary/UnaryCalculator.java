package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.calculators.Calculator;

public interface UnaryCalculator<IN, OUT, T> extends Calculator<OUT> {

    public void setupBlackboardReader(BlackboardReader<IN, T> proxy);
    
    public void setupBinding(Probe<IN> inProbe);
    
    public Class<IN> getInClass();

    public Class<OUT> getOutClass();

}
