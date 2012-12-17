package edu.kit.ipd.sdq.probespec.pcm.contexts;

import edu.kit.ipd.sdq.probespec.framework.blackboard.AbstractMeasurementContext;

public class AssemblyContext extends AbstractMeasurementContext {

    public AssemblyContext(String id, AbstractMeasurementContext parent) {
        super(id, parent);
    }

    public AssemblyContext(String id) {
        super(id);
    }

}
