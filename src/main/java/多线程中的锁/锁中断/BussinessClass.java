package 多线程中的锁.锁中断;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果其他线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。也就使说，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，而线程B只有等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程。
 *
 *  【注意是：等待的那个线程B可以被中断，不是正在执行的A线程被中断】
 * @author james
 * @date 2019/9/19
 */
public class BussinessClass {

    private Lock lock = new ReentrantLock();

    //业务方法
    public void bFunction() {
        String tName = Thread.currentThread().getName();

        try {
            System.out.println(tName + " 开始获取锁...");
            lock.lockInterruptibly();

            System.out.println(tName + " 获取到锁了...睡觉3秒 ");
            Thread.sleep(3000);
            System.out.println(tName + " 睡醒了,... ");

            for (int i = 0; i < 5; i++) {
                System.out.println(tName + " : " + i);
            }

        } catch (InterruptedException e) {
            System.out.println(tName + " 我好像被中断了! ");
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(tName + " 释放了锁 ");
        }
    }


}
