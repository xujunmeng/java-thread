package 手动实现线程池.queue;

/**
 * 定义一个任务队列RunnableQueue
 */
public interface RunnableQueue {

    //当有新任务进来时首先会offer到队列中
    void offer(Runnable runnable);

    //工作线程通过take方法获取Runnable
    Runnable take();

    //获取任务队列中任务数量
    int size();


}
