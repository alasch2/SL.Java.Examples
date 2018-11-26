package io.sl.ex.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.sl.ex.utils.Functions.F0;
import io.sl.ex.utils.Functions.Fs;
import io.sl.ex.utils.Functions.P0;

public class ThreadUtil {

	private static final ExecutorService executorService = Executors.newCachedThreadPool();
	public static void runStrong(final P0 p0) {
		executorService.execute(Fs.runnable(p0));
	}
	
	
	public static <A> ThreadLocalCache<A> threadLocalCache(final F0<A> f) {
		
		final ThreadLocal<A> threadLocal = new ThreadLocal<A>();
		
		ThreadLocalCache<A> ret = new ThreadLocalCache<A>();
		
		ret.cacheF = new F0<A>() {public A e() {
			A a = threadLocal.get();
			if (a==null) {
				a = f.e();
				threadLocal.set(a);
			}
			return a;
		}};
		ret.removeF = new F0<A>() {public A e() {
			A a = threadLocal.get();
			threadLocal.set(null);
			return a;
		}};
		
		return ret;
	}
	
	public static class ThreadLocalCache <A> {
		public F0<A> cacheF;
		public F0<A> removeF;
	}
	
	/**
	 * Sleep and wake on InterruptedException
	 * @param timeToSleep in milliseconds
	 */
	public static void sleep(long timeToSleep) {
		if (timeToSleep <=0)
			return;
		try {
			Thread.sleep(timeToSleep);
		} catch (InterruptedException e) {
		}
	}
	
}