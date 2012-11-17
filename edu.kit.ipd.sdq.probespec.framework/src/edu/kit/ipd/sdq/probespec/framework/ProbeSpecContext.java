package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ThreadManager;
import edu.kit.ipd.sdq.probespec.framework.calculators.BindingContext;

public class ProbeSpecContext<U> {

    private IBlackboard<U> blackboard;

    private BindingContext<U> bindingContext;

    private ThreadManager threadManager;

    public ProbeSpecContext(ITimestampGenerator<U> timestampBuilder) {
        this(timestampBuilder, BlackboardType.SIMPLE);
    }

    public ProbeSpecContext(ITimestampGenerator<U> timestampBuilder, BlackboardType blackboardType) {
        this.threadManager = new ThreadManager();
        this.blackboard = BlackboardFactory.createBlackboard(blackboardType, timestampBuilder, threadManager);
        this.bindingContext = new BindingContext<U>(blackboard);
    }

    public IBlackboard<U> getBlackboard() {
        return blackboard;
    }

    public BindingContext<U> getBindingContext() {
        return bindingContext;
    }

    public ThreadManager getThreadManager() {
        return threadManager;
    }

}
