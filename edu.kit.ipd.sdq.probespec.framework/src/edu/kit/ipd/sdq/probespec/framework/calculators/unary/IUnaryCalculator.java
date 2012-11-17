package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;

public interface IUnaryCalculator<IN, OUT, T> {

    public OUT calculate(IN p);
    
    public void measurementArrived(Measurement<IN, T> measurement, Probe<IN> probe, IBlackboard<T> blackboard);

    public Class<IN> getInClass();

    public Class<OUT> getOutClass();

}
