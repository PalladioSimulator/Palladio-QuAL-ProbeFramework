package edu.kit.ipd.sdq.probespec.framework.garbagecollection;

import edu.kit.ipd.sdq.probespec.framework.blackboard.Blackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;

public class DefaultGarbageCollector extends AbstractRegionBasedGarbageCollector<MeasurementContext> {

    public DefaultGarbageCollector(Blackboard<?> blackboard) {
        super(blackboard);
    }

    @Override
    public void cleanRegion(MeasurementContext context) {
        getBlackboard().deleteMeasurements(context);
    }

}
