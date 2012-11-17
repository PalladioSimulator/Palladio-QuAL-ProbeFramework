package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ThreadManager;
import edu.kit.ipd.sdq.probespec.framework.calculators.CalculatorRegistry;

public class ProbeSpecContext<U> {

    private IBlackboard<U> blackboard;

    private CalculatorRegistry<U> bindingContext;

    private ThreadManager threadManager;
    
    private BlackboardType blackboardType;

    public ProbeSpecContext(ITimestampGenerator<U> timestampBuilder) {
        this(timestampBuilder, BlackboardType.SIMPLE);
    }

    public ProbeSpecContext(ITimestampGenerator<U> timestampBuilder, BlackboardType blackboardType) {
        this.blackboardType = blackboardType;
        this.threadManager = new ThreadManager();
        this.blackboard = BlackboardFactory.createBlackboard(blackboardType, timestampBuilder, threadManager);
        this.bindingContext = new CalculatorRegistry<U>(blackboard);
    }

    public IBlackboard<U> getBlackboard() {
        return blackboard;
    }

    public CalculatorRegistry<U> getCalculatorRegistry() {
        return bindingContext;
    }

    public ThreadManager getThreadManager() {
        return threadManager;
    }

    public BlackboardType getBlackboardType() {
        return blackboardType;
    }

}
