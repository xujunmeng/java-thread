package artConcurrentBook.chapter04;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author james
 * @date 2020/6/12
 */
public class WaitNotify {

    static boolean flag = true;

    static Object lock = new Object();

    public static void main(String[] args) {

    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock) {
                //当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "flag is true, wait @" + new SimpleDateFormat("HH:mm:SS").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
