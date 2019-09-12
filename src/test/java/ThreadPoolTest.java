import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ThreadPoolTest implements Runnable {
	static Logger log = LoggerFactory.getLogger(ThreadPoolTest.class);
	@Override
    public void run() {
		 synchronized(this) {
			 try{
				 log.info(Thread.currentThread().getName());
			 Thread.sleep(3000);
			 }catch (InterruptedException e){
			 e.printStackTrace();
			 }
		  }
		    }
		 
    public static void main(String[] args) {
    	BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(4); //固定为4的线程队列
    	ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 1000,TimeUnit.MINUTES, queue);
    	executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor2) {
            	log.info("文件迁移线程池阻塞队列已满，开始等待进入线程池");
                if (!executor2.isShutdown()) {
                    try {
                        executor2.getQueue().put(r);
                    } catch (InterruptedException e) {
                    	log.info("尝试加入线程池等待错误");
                    }
                }
            }
        });
    	for (int i = 0; i <= 20; i++) {
    		log.info("執行第"+i+"個");
    		executor.execute(new Thread(new ThreadPoolTest(),"TestThread".concat(""+i)));
    		int threadSize = queue.size();
    		log.info("线程队列大小为-->"+threadSize);
    	}
    	executor.shutdown();
    }
}
