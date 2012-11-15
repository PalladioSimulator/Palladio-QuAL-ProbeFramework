package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.ProbeRepository;
import edu.kit.ipd.sdq.probespec.probespecFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.calculators.BindingContext;

public class ProbeSpecContext {

    private IBlackboard blackboard;

    private static final ProbeRepository EMPTY_REPOSITORY = probespecFactory.eINSTANCE.createProbeRepository();

    private static final IBlackboard DEFAULT_BLACKBOARD = BlackboardFactory.createBlackboard(BlackboardType.SIMPLE,
            null);

    private BindingContext bindingContext;

    public ProbeSpecContext() {
        this(DEFAULT_BLACKBOARD);
    }

    public ProbeSpecContext(IBlackboard blackboard) {
        this.blackboard = blackboard;
        this.bindingContext = new BindingContext(blackboard);
    }

    public IBlackboard getBlackboard() {
        return blackboard;
    }

    public BindingContext getBindingContext() {
        return bindingContext;
    }

}
