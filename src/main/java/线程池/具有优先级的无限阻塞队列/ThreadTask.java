package 线程池.具有优先级的无限阻塞队列;

/**
 * @author james
 * @date 2020/6/30
 */
public class ThreadTask implements Runnable, Comparable<ThreadTask> {

    private int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ThreadTask() {
    }

    public ThreadTask(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(ThreadTask o) {
        return this.priority > o.priority ? -1 : 1;
    }

    @Override
    public void run() {
        //让线程阻塞，使后续任务进入缓存队列
        try {
            Thread.sleep(1000);
            System.out.println("priority : " + this.priority + ", ThreadName : " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
