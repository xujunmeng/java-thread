package CompletableFuture用法;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xujunmeng
 * @date 2021/12/28
 */
public class TestMain {

    public List<String> test1(String str) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        list.add(str);
        list.add(str);
        list.add(str);
        list.add(str);
        int a = 1 / 0;
        return list;
    }

    public List<String> test2(String str) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        list.add(str);
        list.add(str);
        list.add(str);
        list.add(str);
        return list;
    }

    public List<String> test3(String str) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        list.add(str);
        list.add(str);
        list.add(str);
        list.add(str);
        return list;
    }

}
