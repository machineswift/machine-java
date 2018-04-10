package com.machine.socket.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerExecutorPool {
	private ExecutorService executor = null;

	public HandlerExecutorPool(int maxPoolSize, int queueSize) {

		this.executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
	}

	public void exeute(Runnable task) {
		this.executor.execute(task);
	}

}
