package 手动实现线程池;

import 手动实现线程池.threadpoll.BasicThreadPool;
import 手动实现线程池.threadpoll.ThreadPool;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {

        final ThreadPool threadPool = new BasicThreadPool(4, 8, 6, 100);

        for (int i = 0; i < 30; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " have done.");
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("getActiveCount:"+threadPool.getActiveCount());
            System.out.println("getQueueSize:"+threadPool.getQueueSize());
            System.out.println("getCoreSize:"+threadPool.getCoreSize());
            System.out.println("getMaxSize:"+threadPool.getMaxSize());
            System.out.println("======================================");
            TimeUnit.SECONDS.sleep(1);
        }

        threadPool.shutdown();
    }

}
