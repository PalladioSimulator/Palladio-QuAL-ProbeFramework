package edu.kit.ipd.sdq.probespec.framework.blackboard.listener;

import edu.kit.ipd.sdq.probespec.framework.blackboard.ProbeMeasurementsProxy;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;

public interface IBlackboardConsumer<V, T> {

    public void setBlackboardProxy(ProbeMeasurementsProxy<V, T> blackboard);
    
    public void measurementArrived(IMeasurementContext... contexts);

    public Class<V> getGenericType();

}
