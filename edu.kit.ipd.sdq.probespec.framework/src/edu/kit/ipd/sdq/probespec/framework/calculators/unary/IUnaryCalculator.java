package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;

public interface IUnaryCalculator<IN, OUT, U> {

    public OUT calculate(IN p);
    
    public void measurementArrived(Measurement<IN, U> measurement, Probe<IN> probe, IBlackboard<U> blackboard);

    public Class<IN> getInClass();

    public Class<OUT> getOutClass();

}
