package practiceproblems.loanpat;

import java.util.concurrent.ForkJoinPool;

public class ThreadAllocation {
	
	private ForkJoinPool threadPool;
	private int poolSize;
	private static final int DEFAULT_SIZE = Runtime.getRuntime().availableProcessors();
	
	public int getPoolSize() {
		return poolSize;
	}
	
	public ForkJoinPool getPool() {
		return threadPool;
	}
	
	public static ThreadAllocation getInstance(int size) {
		final ThreadAllocation instance = new ThreadAllocation();
		instance.poolSize = size;
		instance.threadPool = new ForkJoinPool(size);
		return instance;
	}
	
	public static ThreadAllocation getInstance() {
		return getInstance(DEFAULT_SIZE);
	}
}
