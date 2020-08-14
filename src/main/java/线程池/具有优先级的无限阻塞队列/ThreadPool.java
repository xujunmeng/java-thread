package 线程池.具有优先级的无限阻塞队列;


import java.util.concurrent.*;

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
                new PriorityBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new ThreadTask(i));
        }
    }

}
