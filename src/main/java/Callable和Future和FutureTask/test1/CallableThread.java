package Callable和Future和FutureTask.test1;

import java.util.concurrent.Callable;

/**
 * @author james
 * @date 2019/3/28
 */
public class CallableThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for(; i < 100 ; i++){
            System.out.println(Thread.currentThread().getName() + " 变量的值为 :  " + i);
        }
        return i;
    }

}
