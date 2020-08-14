package 线程池.不存储元素的阻塞队列;

/**
 * @author james
 * @date 2020/6/30
 */
public class ThreadTask implements Runnable {

    public ThreadTask() {
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

}
