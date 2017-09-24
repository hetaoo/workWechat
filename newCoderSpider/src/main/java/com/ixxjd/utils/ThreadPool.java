package com.ixxjd.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadPool {

	private static final ThreadPool INSTANCE = new ThreadPool();
	
	private ThreadPool(){
		executor = Executors.newCachedThreadPool(new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				
				final AtomicLong threadNum = new AtomicLong(1);
				
				Thread thread = new Thread(Thread.currentThread().getThreadGroup(), r,"ThreadPool:"+threadNum.getAndIncrement(),0);
				thread.setDaemon(true);
				if (thread.getPriority() != Thread.MIN_PRIORITY) {
					thread.setPriority(Thread.MIN_PRIORITY);
				}
				return thread;
			}
		});
	};
	
	public static ThreadPool getInstance(){
		return INSTANCE;
	}
	
	private ExecutorService executor = null;
	
	public void execute(Runnable r){
		this.executor.submit(r);
	}
}
