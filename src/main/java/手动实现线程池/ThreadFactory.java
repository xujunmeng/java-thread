package 手动实现线程池;

/**
 * 定义一个ThreadFactory，提供创建线程的接口，以便个性化定制Thread,优先级，线程名字等
 */
public interface ThreadFactory {

    Thread createThread(Runnable runnable);

}
