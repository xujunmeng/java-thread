package artConcurrentBook.chapter03.test;

/**
 * @author james
 * @date 2020/4/26
 */
public class VolatileBarrierExample {

    int a;

    volatile int v1 = 1;

    volatile int v2 = 2;

    public void readAndWrite() {
        //第一个volatile读
        int i = v1;

        //第二个volatile读
        int j = v2;

        //普通写
        a = i + j;

        //第一个volatile写
        v1 = i + 1;

        //第二个volatile写
        v2 = j + 2;

    }


}
