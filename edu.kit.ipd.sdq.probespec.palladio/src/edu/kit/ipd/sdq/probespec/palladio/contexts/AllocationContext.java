package edu.kit.ipd.sdq.probespec.palladio.contexts;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.AbstractMeasurementContext;

public class AllocationContext extends AbstractMeasurementContext {

    public AllocationContext(String id, AbstractMeasurementContext parent) {
        super(id, parent);
    }

    public AllocationContext(String id) {
        super(id);
    }

}
