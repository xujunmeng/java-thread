package semaphore用法;

import java.util.concurrent.Semaphore;

/**
 * @author james
 * @date 2020/3/27
 */
public class Worker  extends Thread{

    private int num;

    private Semaphore semaphore;

    public Worker(int num, Semaphore semaphore){
        this.num = num;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(" 线程 : " + Thread.currentThread().getName() + " 工人 : " + num + " 占用了一个机器在生产... ");
            Thread.sleep(2000);
            System.out.println(" 线程 : " + Thread.currentThread().getName() + " 工人 : " + num + " 释放出机器.... ");
            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
