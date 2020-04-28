package artConcurrentBook.chapter03;

/**
 * @author james
 * @date 2020/4/23
 */
public class MonitorExample {
    int a = 0;

    public synchronized void writer() { //1
        a++; //2
    } //3

    public synchronized void reader() { //4
        int i = a; //5
        //бнбн
    } //6
}

