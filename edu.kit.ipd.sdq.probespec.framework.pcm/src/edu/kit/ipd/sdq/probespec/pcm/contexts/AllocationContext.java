package edu.kit.ipd.sdq.probespec.pcm.contexts;

import edu.kit.ipd.sdq.probespec.framework.blackboard.AbstractMeasurementContext;

public class AllocationContext extends AbstractMeasurementContext {

    public AllocationContext(String id, AbstractMeasurementContext parent) {
        super(id, parent);
    }

    public AllocationContext(String id) {
        super(id);
    }

}
