package edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

public class ActionQueue implements StoppableRunnable {

    private static final Logger logger = Logger.getLogger(ActionQueue.class);

    private boolean keepRunning = true;

    private LinkedBlockingQueue<QueuableAction> queue;

    public ActionQueue() {
        this.queue = new LinkedBlockingQueue<QueuableAction>();
    }

    @Override
    public void run() {
        logger.debug("Runnable " + this.getClass().getSimpleName() + " started running");
        while (keepRunning | !queue.isEmpty()) {
            try {
                queue.take().execute();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        logger.debug("Runnable " + this.getClass().getSimpleName() + " stopped running");
    }

    @Override
    public void stop() {
        try {
            queue.put(new ShutdownQueueAction());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void put(QueuableAction action) {
        try {
            queue.put(action);
        } catch (InterruptedException e) {
            logger.error("Error while enqueuing an action", e);
            e.printStackTrace();
        }
    }
    
    public int size() {
        return queue.size();
    }

    /**
     * When added to the queue, this action shuts down the thread responsible for processing
     * enqueued actions. Sometimes, this is also referred to as "poison pill".
     */
    private class ShutdownQueueAction implements QueuableAction {

        @Override
        public void execute() {
            keepRunning = false;
        }

    }

}
