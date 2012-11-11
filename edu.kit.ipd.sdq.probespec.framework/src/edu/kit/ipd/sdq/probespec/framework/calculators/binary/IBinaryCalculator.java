package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;


public interface IBinaryCalculator<IN1, IN2, OUT> {

    public OUT calculate(IN1 firstProbe, IN2 secondProbe);

//    public IBlackboardListener<IN1> getFirstListener();
//
//    public IBlackboardListener<IN2> getSecondListener();
    
    public abstract void firstMeasurementArrived(IN1 measurement, Probe<IN1> probe, IBlackboard blackboard);

    public abstract void secondMeasurementArrived(IN2 measurement, Probe<IN2> probe, IBlackboard blackboard);

    public Class<IN1> getIn1Class();

    public Class<IN2> getIn2Class();

    public Class<OUT> getOutClass();

}
