package edu.kit.ipd.sdq.probespec.framework;

import java.util.List;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Blackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ConcurrentBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ThreadManager;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.BlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.calculators.CalculatorRegistry;
import edu.kit.ipd.sdq.probespec.framework.calculators.CalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.BinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding.BinaryUnboundCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.UnaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding.UnaryUnboundCalculator;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;
import edu.kit.ipd.sdq.probespec.framework.probes.ProbeRegistry;

/**
 * 
 * @author Philipp Merkle
 * 
 * @param <T>
 *            the type of timestamps. Must be equal to the the type parameter {@code T} of the
 *            {@link TimestampGenerator} to be used.
 */
public abstract class AbstractProbeManager<T> implements ProbeManager<T> {

    private static final Logger logger = Logger.getLogger(AbstractProbeManager.class);

    private Blackboard<T> blackboard;

    private CalculatorRegistry<T> calculatorRegistry;
    
    private ProbeRegistry<T> probeRegistry;

    private ThreadManager threadManager;

    private BlackboardType blackboardType;

    public AbstractProbeManager(TimestampGenerator<T> timestampBuilder) {
        this(timestampBuilder, BlackboardType.SIMPLE);
    }

    public AbstractProbeManager(TimestampGenerator<T> timestampBuilder, BlackboardType blackboardType) {
        logger.info("Initialising ProbeSpecification with " + blackboardType.toString() + " blackboard...");

        this.blackboardType = blackboardType;
        this.threadManager = new ThreadManager();
        this.blackboard = BlackboardFactory.createBlackboard(blackboardType, timestampBuilder, threadManager);
        this.calculatorRegistry = new CalculatorRegistry<T>(blackboard);
        this.probeRegistry = new ProbeRegistry<T>();
    }

    protected Blackboard<T> getBlackboard() {
        return blackboard;
    }

    private CalculatorRegistry<T> getCalculatorRegistry() {
        return calculatorRegistry;
    }
    
    public <V> Probe<V> getProbe(Object entity, Class<? extends Probe<V>> probeType) {
        return probeRegistry.getProbe(entity, probeType);
    }
    
    public <V> Probe<V> getProbe(Object entity, Object mountPoint, Class<? extends Probe<V>> probeType) {
        return probeRegistry.getProbe(entity, mountPoint, probeType);
    }

    public ThreadManager getThreadManager() {
        return threadManager;
    }

    public BlackboardType getBlackboardType() {
        return blackboardType;
    }

    public void shutdown() {
        // TODO where to put this check!?
        List<CalculatorBinding> unboundCalculators = getCalculatorRegistry().getUnboundCalculators();
        for (CalculatorBinding c : unboundCalculators) {
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
    
    public <V> void mountProbe(Probe<V> probe, Object entity, Object mountPoint) {
        probeRegistry.mountProbe(probe, entity, mountPoint);
        probe.setBlackboard(blackboard);
    }
    
    public <V> void mountProbe(Probe<V> probe, Object entity) {
        probeRegistry.mountProbe(probe, entity);
        probe.setBlackboard(blackboard);
    }
    
    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#installCalculator(edu.kit.ipd.sdq.probespec.framework.calculators.unary.IUnaryCalculator)
     */
    @Override
    public <IN, OUT> UnaryUnboundCalculator<IN, OUT> installCalculator(UnaryCalculator<IN, OUT, T> calculator) {
        return getCalculatorRegistry().add(calculator);
    }

    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#installCalculator(edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator)
     */
    @Override
    public <IN1, IN2, OUT> BinaryUnboundCalculator<IN1, IN2, OUT> installCalculator(
            BinaryCalculator<IN1, IN2, OUT, T> calculator) {
        return getCalculatorRegistry().add(calculator);
    }
    

    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#getReader(edu.kit.ipd.sdq.probespec.framework.Probe)
     */
    @Override
    public <V> BlackboardReader<V, T> getReader(Probe<V> probe) {
        return getBlackboard().getReader(probe);
    }
    

    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#getWriter(edu.kit.ipd.sdq.probespec.framework.Probe)
     */
    @Override
    public <V> BlackboardWriter<V> getWriter(Probe<V> probe) {
        return getBlackboard().getWriter(probe);
    }
    
    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#addMeasurementListener(edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener, edu.kit.ipd.sdq.probespec.framework.Probe)
     */
    @Override
    public <V> void addMeasurementListener(BlackboardListener<V, T> l, Probe<V> probe) {
        getBlackboard().addMeasurementListener(l, probe);
    }

    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#addMeasurementListener(edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener)
     */
    @Override
    public <V> void addMeasurementListener(BlackboardListener<V, T> l) {
        getBlackboard().addMeasurementListener(l);
    }
    
    /* (non-Javadoc)
     * @see edu.kit.ipd.sdq.probespec.framework.IProbeManager#removeMeasurementListener(edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener)
     */
    @Override
    public <V> void removeMeasurementListener(BlackboardListener<V, T> l) {
        getBlackboard().removeMeasurementListener(l);
    }
    
}
