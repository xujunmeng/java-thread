package 锁重入;

/**
 * @author james
 * @date 2019/3/9
 */
public class Widget {

    public synchronized void doSomething() {
        String name = Thread.currentThread().getName();
        System.out.println("我是父类中的doSomething, threadName : " + name);
    }

}
