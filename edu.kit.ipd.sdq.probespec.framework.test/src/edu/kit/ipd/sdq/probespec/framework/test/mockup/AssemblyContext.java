package edu.kit.ipd.sdq.probespec.framework.test.mockup;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.AbstractMeasurementContext;

public class AssemblyContext extends AbstractMeasurementContext {

    public AssemblyContext(String id) {
        super(id);
    }

    public AssemblyContext(String id, AbstractMeasurementContext parent) {
        super(id, parent);
    }

}