package 手动实现线程池;

import 手动实现线程池.queue.RunnableQueue;

/**
 * 定义一个InternalTask，主要用于线程池内部，该类会用到RunnableQueue，
 * 不断从queue中取出某个Runnable,并执行器run方法。
 */
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        //如果当前任务为running并且没有被中断，则将其不断地从queue中获取Runnable，
        //然后运行run方法
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable runnable = runnableQueue.take();
                runnable.run();
            } catch (Exception e) {
                running = false;
                break;
            }
        }
    }

    //停止当前任务，主要会在线程池的shutdown方法中使用
    public void stop() {
        this.running = false;
    }
}
