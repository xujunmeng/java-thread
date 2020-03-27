package 闭锁;

import java.util.concurrent.CountDownLatch;

/**
 * @author james
 * @date 2019/3/27
 */
public class Boss implements Runnable{

    private CountDownLatch downLatch;

    public Boss(CountDownLatch downLatch){
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
        System.out.println("老板正在等所有的工人干完活......" + "=======" + Thread.currentThread().getName());
        try {
            this.downLatch.await();
        } catch (InterruptedException e) {

        }
        System.out.println("工人活都干完了，老板开始检查了！" + "=======" + Thread.currentThread().getName());
    }

}
