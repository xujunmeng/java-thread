package artConcurrentBook.chapter04;

/**
 * @author james
 * @date 2020/6/12
 */
public class Synchronized {

    public static void main(String[] args) {
        //对synchronized class 对象进行加锁
        synchronized (Synchronized.class) {
            m();
        }
    }

    public static synchronized void m() {

    }

}
