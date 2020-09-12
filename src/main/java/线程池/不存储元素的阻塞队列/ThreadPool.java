package 线程池.不存储元素的阻塞队列;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author james
 * @date 2020/6/30
 */
public class ThreadPool {

    private static ThreadPoolExecutor threadPoolExecutor;

    public static void main(String[] args) {
        //maximumPoolSize 设置为2，拒绝策略为AbortPolic策略，直接抛出异常
        threadPoolExecutor = new ThreadPoolExecutor(
                1,
                2,
                1000,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.execute(new ThreadTask());
        }

        threadPoolExecutor.shutdown();
    }

}
