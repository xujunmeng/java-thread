package artConcurrentBook.chapter03;

/**
 * @author james
 * @date 2020/4/23
 */
public class ReorderExample {
    int     a    = 0;
    boolean flag = false;
    public void writer() {
        a = 1; //1
        flag = true; //2
    }
    public void reader() {
        if (flag) { //3
            int i = a * a; //4
            //s……
        }
    }
}

