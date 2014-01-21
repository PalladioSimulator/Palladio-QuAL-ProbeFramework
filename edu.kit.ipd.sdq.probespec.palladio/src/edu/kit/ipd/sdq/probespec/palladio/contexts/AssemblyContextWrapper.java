package edu.kit.ipd.sdq.probespec.palladio.contexts;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.AbstractMeasurementContext;

public class AssemblyContextWrapper extends AbstractMeasurementContext {
    
    public AssemblyContextWrapper(AssemblyContext ctx, AssemblyContextWrapper parent) {
        super(ctx.getId(), parent);
    }

    public AssemblyContextWrapper(AssemblyContext ctx) {
        super(ctx.getId());
    }

}
