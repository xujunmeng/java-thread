package 手动实现线程池.threadpoll;

import 手动实现线程池.*;
import 手动实现线程池.queue.DenyPolicy;
import 手动实现线程池.queue.LinkedRunnableQueue;
import 手动实现线程池.queue.RunnableQueue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 初始化线程池（实现线程池接口）
 */
public class BasicThreadPool extends Thread implements ThreadPool {

    //初始化线程数量
    private final int initSize;

    //线程池最大线程数
    private final int maxSize;

    //线程池核心线程数
    private final int coreSize;

    //当前活跃数量
    private int activeCount;

    //创建线程所需的工厂
    private final ThreadFactory threadFactory;

    //任务队列
    private final RunnableQueue runnableQueue;

    //线程池是否已经shutdown，默认为false
    private volatile boolean isShutdown = false;

    //工作线程队列
    private final Queue<ThreadTask> threadTaskQueue = new ArrayDeque<>();

    //默认拒绝策略
    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    //默认线程工厂
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int maxSize, int coreSize, ThreadFactory threadFactory, int queueSize,
                           DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY, queueSize, DEFAULT_DENY_POLICY,
                1000, TimeUnit.SECONDS);
    }

    private void newThread() {
        //创建任务线程，并且启动
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);

        threadTaskQueue.offer(threadTask);

        //当前活跃线程+1
        this.activeCount = this.activeCount + 1;

        thread.start();

    }

    private void removeThread() {
        //从线程池移除某个线程
        ThreadTask threadTask = threadTaskQueue.remove();
        threadTask.internalTask.stop();

        //当前活跃线程-1
        this.activeCount = this.activeCount - 1;
    }

    @Override
    public void run() {
        //run方法主要用于维护线程数量，扩容，回收等工作
        while (!isShutdown && isInterrupted()) {
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                    isShutdown = true;
                    break;
            }
            synchronized (this) {
                if(isShutdown) {
                    break;
                }
                //当前队列中有任务尚未处理，并且activeCount < coreSize则继续扩容
                if(runnableQueue.size() > 0 && activeCount < coreSize){
                    for(int i = initSize; i < coreSize; i++){
                        newThread();
                    }
                    //continue的目的在于不想让线程的扩容直接达到maxSize
                    continue;
                }
                //当前的队列中有任务尚未处理，并且activeCount < maxSize则继续扩容
                if(runnableQueue.size() > 0 && activeCount < maxSize){
                    for(int i = coreSize; i < maxSize; i++) {
                        newThread();
                    }
                }
                //如果队列中没有任务，则需要回收，回收到coreSize即可
                if(runnableQueue.size() == 0 && activeCount > coreSize){
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
    }

    //初始化时，先创建initSize个线程
    private void init() {

        start();

        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    private void isShutdownSystemOut(){
        if(this.isShutdown){
            throw new IllegalStateException("The thread pool is destroyed");
        }
    }







    private static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

        private static final ThreadGroup group = new ThreadGroup("MyThreadPool -- " + GROUP_COUNTER.getAndIncrement());

        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndIncrement());
        }
    }

    //ThreadTask只是InternalTask和Thread的一个组合
    private static class ThreadTask {
        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }













    @Override
    public void execute(Runnable runnable) {
        isShutdownSystemOut();
        //提交任务插入任务队列中
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown) {
                return;
            }

            isShutdown = true;

            threadTaskQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });

            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        isShutdownSystemOut();
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        isShutdownSystemOut();
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        isShutdownSystemOut();
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        isShutdownSystemOut();
        return runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (this) {
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }
}
