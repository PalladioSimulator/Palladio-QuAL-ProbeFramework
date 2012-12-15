package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.calculators.ICalculator;

public interface IBinaryCalculator<IN1, IN2, OUT, T> extends ICalculator<OUT> {

    public void setupBlackboardAccess(IBlackboardReader<IN1, T> proxy1, IBlackboardReader<IN2, T> proxy2);

    public Class<IN1> getIn1Class();

    public Class<IN2> getIn2Class();

    public Class<OUT> getOutClass();

}
