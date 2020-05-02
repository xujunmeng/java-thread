package artConcurrentBook.chapter03.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author james
 * @date 2020/4/23
 */
public class VolatileExampleTest {
    int              a    = 0;
    volatile boolean flag = false;

    public void writer() {
        a = 1; //1
        flag = true; //2
    }

    public void reader() {
        if (flag) { //3
            int i = a; //4
            //бнбн
        }
    }

    public static void main(String[] args) {

        List<Thread> ts = new ArrayList<>(600);
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(() -> {
                for (int i1 = 0; i1 < 100; i1++) {
                    VolatileExampleTest volatileExampleTest = new VolatileExampleTest();
                    volatileExampleTest.writer();
                    volatileExampleTest.reader();
                    System.out.println(volatileExampleTest.a);
                    System.out.println(volatileExampleTest.flag);
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }
        // 等待所有线程执行完成
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
