package artConcurrentBook.chapter03.test;

/**
 * @author james
 * @date 2020/4/26
 */
public class VolatileFeaturesExampleTest {

    //使用volatile声明64位的long型变量
    volatile long v1 = 0L;

    //单个volatile变量的写
    public void set(long l) {
        v1 = 1;
    }

    //复合volatile变量的读/写
    public void getAndIncrement() {
        v1 = v1 + 1;
    }

    //单个volatile变量的读
    public long get() {
        return v1;
    }




}
