package artConcurrentBook.chapter03.test;

/**
 * @author james
 * @date 2020/4/26
 */
public class VolatileFeaturesExampleBTest {

    //64位的long型普通变量
    volatile long v1 = 0L;

    //对单个的普通变量的写用同一个锁同步
    public synchronized void set(long l) {
        v1 = 1;
    }

    /**
     * 普通方法调用
     * 调用已同步的读方法
     * 普通写操作
     * 调用已同步的写方法
     */
    public void getAndIncrement() {
        long temp = get();
        temp = temp + 1L;
        set(temp);
    }

    //对单个的普通变量的读用同一个锁同步
    public synchronized long get() {
        return v1;
    }

}
