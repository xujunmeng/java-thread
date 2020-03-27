package Callable和Future和FutureTask.实例一;

import java.util.concurrent.Callable;

/**
 * @author james
 * @date 2019/3/28
 */
public class RtnThread implements Callable<Integer> {

    /**
     *     实现call方法，作为线程执行体
     */
    @Override
    public Integer call() throws Exception {
        int i = 0;
        for( ; i < 10 ; i++){
            System.out.println(Thread.currentThread().getName() + " 的循环变量i的值 :  " + i);
        }
        return i;
    }
}
