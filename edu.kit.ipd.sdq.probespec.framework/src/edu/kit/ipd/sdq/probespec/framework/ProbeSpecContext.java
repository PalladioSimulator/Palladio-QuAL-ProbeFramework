package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.calculators.BindingContext;

public class ProbeSpecContext<U> {

    private IBlackboard<U> blackboard;

    private BindingContext<U> bindingContext;

    public ProbeSpecContext(ITimestampGenerator<U> timestampBuilder) {
        this(BlackboardFactory.createBlackboard(BlackboardType.SIMPLE, timestampBuilder, null));
    }

    public ProbeSpecContext(IBlackboard<U> blackboard) {
        this.blackboard = blackboard;
        this.bindingContext = new BindingContext<U>(blackboard);
    }

    public IBlackboard<U> getBlackboard() {
        return blackboard;
    }

    public BindingContext<U> getBindingContext() {
        return bindingContext;
    }

}
