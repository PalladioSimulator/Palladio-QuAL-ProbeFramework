package edu.kit.ipd.sdq.probespec.framework.blackboard.listener;

import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;

public interface _IBlackboardReader_<V, T> {

    public void setupBlackboardReader(BlackboardReader<V, T> blackboard);
    
    public void measurementArrived(IMeasurementContext... contexts);

    public Class<V> getGenericType();

}
