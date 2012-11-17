package edu.kit.ipd.sdq.probespec.framework.blackboard.listener;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;

public interface IBlackboardListener<T, U> {

    public void measurementArrived(IBlackboard<U> blackboard, Measurement<T, U> measurement, Probe<T> probe,
            IMeasurementContext... contexts);

    public Class<T> getGenericType();

}
