package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.calculators.ICalculator;

public interface IUnaryCalculator<IN, OUT, T> extends ICalculator<OUT> {

    public void setupBlackboardReader(IBlackboardReader<IN, T> proxy);
    
    public void setupBinding(Probe<IN> inProbe);
    
    public Class<IN> getInClass();

    public Class<OUT> getOutClass();

}
