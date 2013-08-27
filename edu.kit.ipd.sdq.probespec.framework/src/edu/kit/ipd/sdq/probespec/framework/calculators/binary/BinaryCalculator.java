package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.calculators.Calculator;

public interface BinaryCalculator<IN1, IN2, OUT, T> extends Calculator<OUT> {

    public void setupBlackboardReader(BlackboardReader<IN1, T> proxy1, BlackboardReader<IN2, T> proxy2);
    
    public void setupBinding(Probe<IN1> in1Probe, Probe<IN2> in2Probe);

    public Class<IN1> getIn1Class();

    public Class<IN2> getIn2Class();

    public Class<OUT> getOutClass();

}
