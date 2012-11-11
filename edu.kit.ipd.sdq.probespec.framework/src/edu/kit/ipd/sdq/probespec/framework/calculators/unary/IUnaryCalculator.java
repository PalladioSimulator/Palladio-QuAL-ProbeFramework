package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;

public interface IUnaryCalculator<IN, OUT> {

    public OUT calculate(IN p);
    
    public void measurementArrived(IN measurement, Probe<IN> probe, IBlackboard blackboard);

    public Class<IN> getInClass();

    public Class<OUT> getOutClass();

}
