package edu.kit.ipd.sdq.probespec.palladio.contexts;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.AbstractMeasurementContext;

public class UsageContext extends AbstractMeasurementContext {

    public UsageContext(String id) {
        super(id);
    }

    public UsageContext(String id, AbstractMeasurementContext parent) {
        super(id, parent);
    }
    
}
