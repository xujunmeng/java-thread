package artConcurrentBook.chapter04;

/**
 * Created by jgsoft on 2020/6/13.
 */
public class TimeOutMain {

    //对当前对象加锁
    public synchronized Object get(long mills) {
        long future = System.currentTimeMillis() + mills;

        long remaining = mills;

        return null;
    }

}
