package de.uka.ipd.sdq.probespec.framework.concurrency;

import java.util.ArrayList;

public class ThreadManager {

	private ArrayList<RunnableThreadPair> runnableThreadList;
	
	public ThreadManager() {
		runnableThreadList = new ArrayList<RunnableThreadPair>();
	}
	
	public void startThread(StoppableRunnable runnable, String threadName) {
		Thread thread = new Thread(runnable);
		runnableThreadList.add(new RunnableThreadPair(runnable, thread));
		thread.setName(threadName);
		thread.start();
	}
	
	private class RunnableThreadPair {
		
		private StoppableRunnable runnable;
		
		private Thread thread;
		
		public RunnableThreadPair(StoppableRunnable runnable, Thread thread) {
			this.runnable = runnable;
			this.thread = thread;
		}

		public StoppableRunnable getRunnable() {
			return runnable;
		}

		public Thread getThread() {
			return thread;
		}
		
	}
	
	public void stopThreads() {
		for (RunnableThreadPair p : runnableThreadList) {
			p.getRunnable().stop();
		}
		
		for (RunnableThreadPair p : runnableThreadList) {
			// Wait for the thread to finish
			try {
				// TODO: maybe use join with parameter in order to avoid waiting forever
				p.getThread().join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
