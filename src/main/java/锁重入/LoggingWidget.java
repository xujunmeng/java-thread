package 锁重入;

/**
 * @author james
 * @date 2019/3/9
 */
public class LoggingWidget extends Widget {

    @Override
    public synchronized void doSomething() {
        String name = Thread.currentThread().getName();
        System.out.println("我是子类中的doSomething, threadName : " + name);
        super.doSomething();
        super.doSomething();
    }

}
