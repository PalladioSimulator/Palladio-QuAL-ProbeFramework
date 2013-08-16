package edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.IMetadata;
import edu.kit.ipd.sdq.probespec.framework.ITimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;

public class ConcurrentBlackboard<T> implements IBlackboard<T> {

    private static final Logger logger = Logger.getLogger(ConcurrentBlackboard.class);

    private ThreadManager threadManager;

    private ActionQueue queue;

    private IBlackboard<T> delegatee;

    // TODO
    private boolean weakConsistency;

    public ConcurrentBlackboard(ITimestampGenerator<T> timestampBuilder, ThreadManager threadManager) {
        this(timestampBuilder, threadManager, true);
    }

    public ConcurrentBlackboard(ITimestampGenerator<T> timestampBuilder, ThreadManager threadManager,
            boolean weakConsistency) {
        this.threadManager = threadManager;
        this.queue = new ActionQueue();
        this.delegatee = BlackboardFactory.createBlackboard(BlackboardType.SIMPLE, timestampBuilder, null);
        this.weakConsistency = weakConsistency;

        this.threadManager.startThread(queue, "ProbeSpec Concurrent Blackboard");
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe) {
        queue.put(new AddMeasurementAction<V>(value, probe));
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMetadata metadata) {
        queue.put(new AddMeasurementAction<V>(value, probe, metadata));
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts) {
        queue.put(new AddMeasurementAction<V>(value, probe, contexts));
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMetadata metadata, IMeasurementContext... contexts) {
        queue.put(new AddMeasurementAction<V>(value, probe, metadata, contexts));
    }

    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        queue.put(new DeleteMeasurementsInContextAction(context));
    }

    @Override
    public <V> void deleteMeasurement(Probe<V> probe, IMeasurementContext... contexts) {
        queue.put(new DeleteMeasurementAction<V>(probe, contexts));
    }

    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l, Probe<V> probe) {
        delegatee.addMeasurementListener(l, probe);
    }

    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, T> l) {
        delegatee.addMeasurementListener(l);
    }

    @Override
    public void removeMeasurementListener(IBlackboardListener<?, T> l) {
        delegatee.removeMeasurementListener(l);
    }

    @Override
    public void removeMeasurementListener(IBlackboardListener<?, T> l, Probe<?> probe) {
        delegatee.removeMeasurementListener(l, probe);
    }

    @Override
    public <V> IBlackboardReader<V, T> getReader(Probe<V> probe) {
        return delegatee.getReader(probe);
    }
    
    @Override
    public <V> IBlackboardWriter<V> getWriter(Probe<V> probe) {
        return delegatee.getWriter(probe);
    }

    public void synchronise() {
        final CountDownLatch latch = new CountDownLatch(1);
        queue.put(new IQueuableAction() {
            @Override
            public void execute() {
                latch.countDown();
            }
        });

        // block until queue has processed our synchronisation action added before
        if (logger.isDebugEnabled()) {
            logger.debug("Synchronisation started. Waiting for " + queue.size() + " queued actions to be processed.");
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Synchronisation finished. Queue size is " + queue.size() + ".");
        }
    }

    /**
     * Adds a sample to the blackboard.
     */
    private class AddMeasurementAction<V> implements IQueuableAction {

        private V value;

        private Probe<V> probe;

        private IMetadata metadata;

        private IMeasurementContext[] contexts;

        public AddMeasurementAction(V value, Probe<V> probe, IMeasurementContext... contexts) {
            this(value, probe, IMetadata.EMPTY_METADATA, contexts);
        }

        public AddMeasurementAction(V value, Probe<V> probe, IMetadata metadata, IMeasurementContext... contexts) {
            this.value = value;
            this.probe = probe;
            this.metadata = metadata;
            this.contexts = contexts;
        }

        @Override
        public void execute() {
            if (contexts.length == 0) {
                delegatee.addMeasurement(value, probe, metadata);
            } else {
                delegatee.addMeasurement(value, probe, metadata, contexts);
            }
        }

    }

    private class DeleteMeasurementsInContextAction implements IQueuableAction {

        private IMeasurementContext context;

        public DeleteMeasurementsInContextAction(IMeasurementContext context) {
            this.context = context;
        }

        @Override
        public void execute() {
            delegatee.deleteMeasurements(context);
        }

    }

    private class DeleteMeasurementAction<V> implements IQueuableAction {

        private Probe<V> probe;

        private IMeasurementContext[] contexts;

        public DeleteMeasurementAction(Probe<V> probe, IMeasurementContext... contexts) {
            this.probe = probe;
            this.contexts = contexts;
        }

        @Override
        public void execute() {
            delegatee.deleteMeasurement(probe, contexts);
        }

    }

}
