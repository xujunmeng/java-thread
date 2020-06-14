package artConcurrentBook.chapter04.connectPool;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jgsoft on 2020/6/14.
 */
public class ConnectionPoolTest {

    static ConnectionPool pool = new ConnectionPool(10);

    //保证所有ConnectionRunner能够同时开始
    static CountDownLatch start = new CountDownLatch(1);

    //main线程将会等待所有ConnectionRunner结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) {
        //线程数量，可以修改线程数量进行观察

    }

}
