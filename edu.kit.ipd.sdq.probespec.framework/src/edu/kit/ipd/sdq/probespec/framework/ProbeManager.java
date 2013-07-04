package edu.kit.ipd.sdq.probespec.framework;

import java.util.List;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ConcurrentBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ThreadManager;
import edu.kit.ipd.sdq.probespec.framework.calculators.CalculatorRegistry;
import edu.kit.ipd.sdq.probespec.framework.calculators.ICalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding.BinaryCalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding.IBinaryUnboundCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.IUnaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding.IUnaryUnboundCalculator;

/**
 * 
 * @author Philipp Merkle
 * 
 * @param <T>
 *            the type of timestamps. Must be equal to the the type parameter {@code T} of the
 *            {@link ITimestampGenerator} to be used.
 */
public class ProbeManager<T> {

    private static final Logger logger = Logger.getLogger(ProbeManager.class);

    private IBlackboard<T> blackboard;

    private CalculatorRegistry<T> calculatorRegistry;

    private ThreadManager threadManager;

    private BlackboardType blackboardType;

    public ProbeManager(ITimestampGenerator<T> timestampBuilder) {
        this(timestampBuilder, BlackboardType.SIMPLE);
    }

    public ProbeManager(ITimestampGenerator<T> timestampBuilder, BlackboardType blackboardType) {
        logger.info("Initialising ProbeSpecification with " + blackboardType.toString() + " blackboard...");

        this.blackboardType = blackboardType;
        this.threadManager = new ThreadManager();
        this.blackboard = BlackboardFactory.createBlackboard(blackboardType, timestampBuilder, threadManager);
        this.calculatorRegistry = new CalculatorRegistry<T>(blackboard);
    }

    protected IBlackboard<T> getBlackboard() {
        return blackboard;
    }

    public CalculatorRegistry<T> getCalculatorRegistry() {
        return calculatorRegistry;
    }

    public ThreadManager getThreadManager() {
        return threadManager;
    }

    public BlackboardType getBlackboardType() {
        return blackboardType;
    }

    public void shutdown() {
        // TODO where to put this check!?
        List<ICalculatorBinding> unboundCalculators = getCalculatorRegistry().getUnboundCalculators();
        for (ICalculatorBinding c : unboundCalculators) {
            logger.warn("Encountered unbound calculator: " + c);
        }

        threadManager.stopThreads();

        logger.info("Shut down ProbeSpecification");
    }

    // TODO where to put this method!?
    public void synchronise() {
        if (blackboardType.equals(BlackboardType.CONCURRENT)) {
            ((ConcurrentBlackboard<T>) getBlackboard()).synchronise();
        }
    }

    public <IN, OUT> IUnaryUnboundCalculator<IN, OUT> installCalculator(IUnaryCalculator<IN, OUT, T> calculator) {
        return getCalculatorRegistry().add(calculator);
    }

    public <IN1, IN2, OUT> IBinaryUnboundCalculator<IN1, IN2, OUT> installCalculator(
            IBinaryCalculator<IN1, IN2, OUT, T> calculator) {
        return getCalculatorRegistry().add(calculator);
    }
    
}
