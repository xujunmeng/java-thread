package 闭锁;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author james
 * @date 2019/3/27
 */
public class TestLatch {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(3);

        Work w1 = new Work(latch,"张三");
        Work w2 = new Work(latch,"李四");
        Work w3 = new Work(latch,"王二");

        Boss boss = new Boss(latch);

        executor.execute(w3);
        executor.execute(w2);
        executor.execute(w1);
        executor.execute(boss);

        executor.shutdown();
    }
}
