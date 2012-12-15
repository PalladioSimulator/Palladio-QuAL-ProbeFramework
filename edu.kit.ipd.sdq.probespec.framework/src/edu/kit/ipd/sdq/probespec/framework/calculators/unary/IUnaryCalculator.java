package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.calculators.ICalculator;

public interface IUnaryCalculator<IN, OUT, T> extends ICalculator<OUT> {

    public void setupBlackboardAccess(IBlackboardReader<IN, T> proxy);
    
    public Class<IN> getInClass();

    public Class<OUT> getOutClass();

}
