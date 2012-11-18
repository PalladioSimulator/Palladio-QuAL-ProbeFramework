package edu.kit.ipd.sdq.probespec.framework;

import java.util.List;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ThreadManager;
import edu.kit.ipd.sdq.probespec.framework.calculators.CalculatorRegistry;
import edu.kit.ipd.sdq.probespec.framework.calculators.IBindableCalculator;

public class ProbeSpecContext<U> {

    private static final Logger logger = Logger.getLogger(ProbeSpecContext.class);

    private IBlackboard<U> blackboard;

    private CalculatorRegistry<U> bindingContext;

    private ThreadManager threadManager;

    private BlackboardType blackboardType;

    public ProbeSpecContext(ITimestampGenerator<U> timestampBuilder) {
        this(timestampBuilder, BlackboardType.SIMPLE);
    }

    public ProbeSpecContext(ITimestampGenerator<U> timestampBuilder, BlackboardType blackboardType) {
        logger.info("Initialising ProbeSpecification with " + blackboardType.toString() + " blackboard...");
        
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

    public void shutdown() {
        // TODO where to put this check!?
        List<IBindableCalculator> unboundCalculators = getCalculatorRegistry().getUnboundCalculators();
        for (IBindableCalculator c : unboundCalculators) {
            logger.warn("Encountered unbound calculator: " + c);
        }

        threadManager.stopThreads();
        
        logger.info("Shut down ProbeSpecification");
    }

}
