package artConcurrentBook.chapter03.test;

/**
 * @author james
 * @date 2020/4/26
 */
public class MonitorExample {

    int a = 0;

    public synchronized void writer() {  //1
        a = a + 1;                       //2
    }                                    //3

    public synchronized  void reader() { //4
        int i = a;                       //5
    }                                    //6

}
