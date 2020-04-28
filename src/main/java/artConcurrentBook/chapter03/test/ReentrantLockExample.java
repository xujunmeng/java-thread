package artConcurrentBook.chapter03.test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author james
 * @date 2020/4/26
 */
public class ReentrantLockExample {

    int a = 0;

    ReentrantLock lock = new ReentrantLock();

    public void writer() {
        lock.lock();           //获取锁
        try {
            a = a + 1;
        } finally {
            lock.unlock();     //释放锁
        }
    }

    public void reader() {
        lock.lock();           //获取锁
        try {
            int i = a;
            //...
        } finally {
            lock.unlock();     //释放锁
        }
    }

}
