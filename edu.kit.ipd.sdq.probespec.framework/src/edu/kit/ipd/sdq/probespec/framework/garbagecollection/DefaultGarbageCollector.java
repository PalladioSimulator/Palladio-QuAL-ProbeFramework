package edu.kit.ipd.sdq.probespec.framework.garbagecollection;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

public class DefaultGarbageCollector extends RegionBasedGarbageCollector<IMeasurementContext> {

    public DefaultGarbageCollector(IBlackboard<?> blackboard) {
        super(blackboard);
    }

    @Override
    public void cleanRegion(IMeasurementContext context) {
        getBlackboard().deleteMeasurements(context);
    }

}
