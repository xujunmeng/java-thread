package CompletableFuture用法;

import java.util.concurrent.*;

/**
 * @author xujunmeng
 * @date 2021/6/9
 */
public class Main {


    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(8, 16, 15, TimeUnit.MINUTES, new SynchronousQueue<>(), (thread) -> new Thread(thread,"订单预热线程"));

        for(int i = 0 ; i < 9 ; i++){
            int finalI = i;
            CompletableFuture.runAsync(() -> {

                System.out.println(Thread.currentThread().getId() + " " + finalI);

            }, executorService);
        }



        System.out.println("sadfasdf");

        executorService.shutdown();
    }

}
