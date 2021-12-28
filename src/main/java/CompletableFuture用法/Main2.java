package CompletableFuture用法;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author xujunmeng
 * @date 2021/12/28
 */
public class Main2 {

    ExecutorService executorService = new ThreadPoolExecutor(8, 16, 15, TimeUnit.MINUTES, new SynchronousQueue<>(), (thread) -> new Thread(thread,"test-thread"));

    TestMain testMain = new TestMain();

    @Test
    public void testxdf() {
        long startTime = System.currentTimeMillis();
        CompletableFuture<List<String>> test1CompletableFuture = CompletableFuture
                .supplyAsync(() -> {
                    List<String> aaa = testMain.test1("aaa");
                    return aaa;
                    }, executorService)
                .exceptionally(e -> {
                    System.out.println(e);
                    List<String> exceptionResult = new ArrayList<>();
                    return exceptionResult;
                });

        CompletableFuture<List<String>> test2CompletableFuture = CompletableFuture
                .supplyAsync(() -> {
                    List<String> bbb = testMain.test2("bbb");
                    return bbb;
                }, executorService)
                .exceptionally(e -> {
                    System.out.println(e);
                    List<String> exceptionResult = new ArrayList<>();
                    return exceptionResult;
                });

        CompletableFuture<List<String>> test3CompletableFuture = CompletableFuture
                .supplyAsync(() -> {
                    List<String> ccc = testMain.test3("ccc");
                    return ccc;
                }, executorService)
                .exceptionally(e -> {
                    System.out.println(e);
                    List<String> exceptionResult = new ArrayList<>();
                    return exceptionResult;
                });

        CompletableFuture.allOf(test1CompletableFuture, test2CompletableFuture, test3CompletableFuture).join();

        System.out.println("cost1 : " + (System.currentTimeMillis() - startTime));


        List<String> test1Result = null;
        try {
            test1Result = test1CompletableFuture.get(0, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println(e);
            test1Result = new ArrayList<>();
        }
        System.out.println(test1Result);

        List<String> test2Result = null;
        try {
            test2Result = test2CompletableFuture.get(0, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println(e);
            test2Result = new ArrayList<>();
        }
        System.out.println(test2Result);

        List<String> test3Result = null;
        try {
            test3Result = test3CompletableFuture.get(0, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println(e);
            test3Result = new ArrayList<>();
        }
        System.out.println(test3Result);

        System.out.println("cost2 : " + (System.currentTimeMillis() - startTime));


    }

    @Test
    public void setdf() {
        long startTime = System.currentTimeMillis();

        List<String> aaa = testMain.test1("aaa");
        System.out.println(aaa);

        List<String> bbb = testMain.test2("bbb");
        System.out.println(bbb);

        List<String> ccc = testMain.test3("ccc");
        System.out.println(ccc);

        System.out.println("cost : " + (System.currentTimeMillis() - startTime));
    }


}
