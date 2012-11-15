package edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public class ConcurrentBlackboard implements IBlackboard {

    private static final Logger logger = Logger.getLogger(ConcurrentBlackboard.class);

    private ThreadManager threadManager;

    private ActionQueue queue;

    private IBlackboard delegatee;

    private boolean weakConsistency;

    public ConcurrentBlackboard(ThreadManager threadManager) {
        this(threadManager, true);
    }

    public ConcurrentBlackboard(ThreadManager threadManager, boolean weakConsistency) {
        this.threadManager = threadManager;
        this.queue = new ActionQueue();
        this.delegatee = BlackboardFactory.createBlackboard(BlackboardType.SIMPLE, null);
        this.weakConsistency = weakConsistency;

        this.threadManager.startThread(queue, "ProbeSpec Concurrent Blackboard");
    }

    @Override
    public <T> void addMeasurement(T measurement, Probe<T> probe) {
        queue.put(new AddMeasurementAction<T>(measurement, probe));
    }

    @Override
    public <T> void addMeasurement(T measurement, Probe<T> probe, IMeasurementContext... contexts) {
        queue.put(new AddMeasurementAction<T>(measurement, probe, contexts));
    }

    @Override
    public <T> T getLatestMeasurement(Probe<T> probe) {
        if (weakConsistency) {
            return delegatee.getLatestMeasurement(probe);
        } else {
            // TODO
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    @Override
    public <T> T getLatestMeasurement(Probe<T> probe, IMeasurementContext... contexts) {
        if (weakConsistency) {
            return delegatee.getLatestMeasurement(probe, contexts);
        } else {
            // TODO
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        queue.put(new DeleteMeasurementsInContextAction(context));
    }

    @Override
    public <T> void deleteMeasurement(Probe<T> probe, IMeasurementContext... contexts) {
        queue.put(new DeleteMeasurementAction<T>(probe, contexts));
    }

    @Override
    public <T> void addMeasurementListener(IBlackboardListener<T> l, Probe<T> probe) {
        delegatee.addMeasurementListener(l, probe);
    }

    @Override
    public <T> void addMeasurementListener(IBlackboardListener<T> l) {
        delegatee.addMeasurementListener(l);
    }

    @Override
    public <T> void removeMeasurementListener(IBlackboardListener<T> l) {
        delegatee.removeMeasurementListener(l);
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
    private class AddMeasurementAction<T> implements IQueuableAction {

        private T measurement;

        private Probe<T> probe;

        private IMeasurementContext[] contexts;

        public AddMeasurementAction(T measurement, Probe<T> probe, IMeasurementContext... contexts) {
            this.measurement = measurement;
            this.probe = probe;
            this.contexts = contexts;
        }

        @Override
        public void execute() {
            if (contexts.length == 0) {
                delegatee.addMeasurement(measurement, probe);
            } else {
                delegatee.addMeasurement(measurement, probe, contexts);
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

    private class DeleteMeasurementAction<T> implements IQueuableAction {

        private Probe<T> probe;

        private IMeasurementContext[] contexts;

        public DeleteMeasurementAction(Probe<T> probe, IMeasurementContext... contexts) {
            this.probe = probe;
            this.contexts = contexts;
        }

        @Override
        public void execute() {
            delegatee.deleteMeasurement(probe, contexts);
        }

    }

}
