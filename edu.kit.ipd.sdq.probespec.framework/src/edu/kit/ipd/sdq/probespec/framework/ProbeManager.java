package edu.kit.ipd.sdq.probespec.framework;

import java.util.List;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ConcurrentBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ThreadManager;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.calculators.CalculatorRegistry;
import edu.kit.ipd.sdq.probespec.framework.calculators.ICalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator;
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
public class ProbeManager<T> implements IProbeManager<T> {

    private static final Logger logger = Logger.getLogger(ProbeManager.class);

    private IBlackboard<T> blackboard;

    private CalculatorRegistry<T> calculatorRegistry;
    
    private ProbeRegistry<T> probeRegistry;

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
        this.probeRegistry = new ProbeRegistry<T>();
    }

    protected IBlackboard<T> getBlackboard() {
        return blackboard;
    }

    private CalculatorRegistry<T> getCalculatorRegistry() {
        return calculatorRegistry;
    }
    
    public <V> Probe<V> getProbe(Object annotatedEntity, Class<? extends Probe<V>> probeType) {
        return probeRegistry.getProbe(annotatedEntity, probeType);
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
    
    public <V> void registerProbe(Probe<V> probe, Object mountPoint) {
        // TODO
        probeRegistry.registerProbe(probe, mountPoint);
        probe.setBlackboard(blackboard);
    }
    
    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#installCalculator(edu.kit.ipd.sdq.probespec.framework.calculators.unary.IUnaryCalculator)
     */
    @Override
    public <IN, OUT> IUnaryUnboundCalculator<IN, OUT> installCalculator(IUnaryCalculator<IN, OUT, T> calculator) {
        return getCalculatorRegistry().add(calculator);
    }

    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#installCalculator(edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator)
     */
    @Override
    public <IN1, IN2, OUT> IBinaryUnboundCalculator<IN1, IN2, OUT> installCalculator(
            IBinaryCalculator<IN1, IN2, OUT, T> calculator) {
        return getCalculatorRegistry().add(calculator);
    }
    

    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#getReader(edu.kit.ipd.sdq.probespec.framework.Probe)
     */
    @Override
    public <V> IBlackboardReader<V, T> getReader(Probe<V> probe) {
        return getBlackboard().getReader(probe);
    }
    

    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#getWriter(edu.kit.ipd.sdq.probespec.framework.Probe)
     */
    @Override
    public <V> IBlackboardWriter<V> getWriter(Probe<V> probe) {
        return getBlackboard().getWriter(probe);
    }
    
    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#addMeasurementListener(edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener, edu.kit.ipd.sdq.probespec.framework.Probe)
     */
    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l, Probe<V> probe) {
        getBlackboard().addMeasurementListener(l, probe);
    }

    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#addMeasurementListener(edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener)
     */
    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l) {
        getBlackboard().addMeasurementListener(l);
    }
    
    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#removeMeasurementListener(edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener)
     */
    @Override
    public <V> void removeMeasurementListener(IBlackboardListener<V, T> l) {
        getBlackboard().removeMeasurementListener(l);
    }
    
}
