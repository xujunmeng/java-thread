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
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();

        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock) {
                //当条件不满足时，释放了lock的锁
                //当条件满足时，继续wait
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "flag is true, wait @" + new SimpleDateFormat("HH:mm:SS").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("");
                }
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock) {
                //获取lock的锁，然后进行通知，通知时不会释放lock的锁，
                //直到当前线程释放了lock后，waitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:SS").format(new Date()));

                lock.notifyAll();

                flag = false;
                SleepUtils.second(5);
            }

            //再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. notify @ " + new SimpleDateFormat("HH:mm:SS").format(new Date()));

                SleepUtils.second(5);
            }
        }
    }


}
