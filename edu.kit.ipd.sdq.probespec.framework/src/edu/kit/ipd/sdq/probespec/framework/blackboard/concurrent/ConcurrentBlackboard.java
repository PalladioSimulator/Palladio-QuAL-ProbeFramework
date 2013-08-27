package edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.Metadata;
import edu.kit.ipd.sdq.probespec.framework.TimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Blackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.BlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public class ConcurrentBlackboard<T> implements Blackboard<T> {

    private static final Logger logger = Logger.getLogger(ConcurrentBlackboard.class);

    private ThreadManager threadManager;

    private ActionQueue queue;

    private Blackboard<T> delegatee;

    // TODO
    private boolean weakConsistency;

    public ConcurrentBlackboard(TimestampGenerator<T> timestampBuilder, ThreadManager threadManager) {
        this(timestampBuilder, threadManager, true);
    }

    public ConcurrentBlackboard(TimestampGenerator<T> timestampBuilder, ThreadManager threadManager,
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
    public <V> void addMeasurement(V value, Probe<V> probe, Metadata metadata) {
        queue.put(new AddMeasurementAction<V>(value, probe, metadata));
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, MeasurementContext... contexts) {
        queue.put(new AddMeasurementAction<V>(value, probe, contexts));
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, Metadata metadata, MeasurementContext... contexts) {
        queue.put(new AddMeasurementAction<V>(value, probe, metadata, contexts));
    }

    @Override
    public void deleteMeasurements(MeasurementContext context) {
        queue.put(new DeleteMeasurementsInContextAction(context));
    }

    @Override
    public <V> void deleteMeasurement(Probe<V> probe, MeasurementContext... contexts) {
        queue.put(new DeleteMeasurementAction<V>(probe, contexts));
    }

    @Override
    public <V> void addMeasurementListener(BlackboardListener<V, T> l, Probe<V> probe) {
        delegatee.addMeasurementListener(l, probe);
    }

    @Override
    public <V> void addMeasurementListener(BlackboardListener<V, T> l) {
        delegatee.addMeasurementListener(l);
    }

    @Override
    public void removeMeasurementListener(BlackboardListener<?, T> l) {
        delegatee.removeMeasurementListener(l);
    }

    @Override
    public void removeMeasurementListener(BlackboardListener<?, T> l, Probe<?> probe) {
        delegatee.removeMeasurementListener(l, probe);
    }

    @Override
    public <V> BlackboardReader<V, T> getReader(Probe<V> probe) {
        return delegatee.getReader(probe);
    }
    
    @Override
    public <V> BlackboardWriter<V> getWriter(Probe<V> probe) {
        return delegatee.getWriter(probe);
    }

    public void synchronise() {
        final CountDownLatch latch = new CountDownLatch(1);
        queue.put(new QueuableAction() {
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
    private class AddMeasurementAction<V> implements QueuableAction {

        private V value;

        private Probe<V> probe;

        private Metadata metadata;

        private MeasurementContext[] contexts;

        public AddMeasurementAction(V value, Probe<V> probe, MeasurementContext... contexts) {
            this(value, probe, Metadata.EMPTY_METADATA, contexts);
        }

        public AddMeasurementAction(V value, Probe<V> probe, Metadata metadata, MeasurementContext... contexts) {
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

    private class DeleteMeasurementsInContextAction implements QueuableAction {

        private MeasurementContext context;

        public DeleteMeasurementsInContextAction(MeasurementContext context) {
            this.context = context;
        }

        @Override
        public void execute() {
            delegatee.deleteMeasurements(context);
        }

    }

    private class DeleteMeasurementAction<V> implements QueuableAction {

        private Probe<V> probe;

        private MeasurementContext[] contexts;

        public DeleteMeasurementAction(Probe<V> probe, MeasurementContext... contexts) {
            this.probe = probe;
            this.contexts = contexts;
        }

        @Override
        public void execute() {
            delegatee.deleteMeasurement(probe, contexts);
        }

    }

}
