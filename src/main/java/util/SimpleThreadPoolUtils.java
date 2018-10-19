package util;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * * SimpleThreadPoolUtils工具类 * *
 * 
 * @author *
 * @date 2018/9/13 16:51
 */
public class SimpleThreadPoolUtils {

	/**
	 * * 定义一个固定线程大小的线程池 corePoolSize 10 maximumPoolSize 10
	 */
	private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>(1024), new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build(),
			new ThreadPoolExecutor.AbortPolicy() {
				// 队列已满,而且当前线程数已经超过最大线程数时的异常处理策略
				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
					super.rejectedExecution(r, e);
				}
			});

	private static class ThreadPoolUtilsHolder {
		private static SimpleThreadPoolUtils instance = new SimpleThreadPoolUtils();
	}

	public static SimpleThreadPoolUtils getInstance() {
		return ThreadPoolUtilsHolder.instance;
	}

	/**
	 * 异步线程处理
	 * 
	 * @param runnable
	 */
	public void asyncThreadHandler(Runnable runnable) {
		threadPoolExecutor.execute(runnable);
	}

	public <T> Future<T> submitCallable(Callable<T> callable) {
		return threadPoolExecutor.submit(callable);
	}
}