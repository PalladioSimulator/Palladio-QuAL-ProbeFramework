package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;


public interface IBinaryCalculator<IN1, IN2, OUT, U> {

    public OUT calculate(IN1 firstProbe, IN2 secondProbe);
    
    public void firstMeasurementArrived(Measurement<IN1, U> measurement, Probe<IN1> probe, IBlackboard<U> blackboard);

    public void secondMeasurementArrived(Measurement<IN2, U> measurement, Probe<IN2> probe, IBlackboard<U> blackboard);

    public Class<IN1> getIn1Class();

    public Class<IN2> getIn2Class();

    public Class<OUT> getOutClass();

}
