package edu.kit.ipd.sdq.probespec.framework.test.mockup;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.AbstractMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

public class UsageContext extends AbstractMeasurementContext {

    public UsageContext(String id) {
        super(id);
    }

    public UsageContext(String id, AbstractMeasurementContext parent) {
        super(id, parent);
    }

}
