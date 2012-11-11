package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardListener;

public interface IUnaryCalculator<IN, OUT> {

    public OUT calculate(IN p);

    public IBlackboardListener<IN> getListener();

    public Class<IN> getInClass();

    public Class<OUT> getOutClass();

}
