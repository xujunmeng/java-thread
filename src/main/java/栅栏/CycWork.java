package 栅栏;

import java.util.concurrent.CyclicBarrier;

/**
 * @author james
 * @date 2019/3/27
 */

public class CycWork implements Runnable {

    private CyclicBarrier cyclicBarrier ;

    private String name ;

    public CycWork(CyclicBarrier cyclicBarrier,String name)
    {
        this .name =name;
        this .cyclicBarrier =cyclicBarrier;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        System. out .println(name +"正在打桩，毕竟不轻松。。。。。" + "=======" + Thread.currentThread().getName() );

        try {
            Thread. sleep(5000);
            System. out .println(name +"不容易，终于把桩打完了。。。。" + "=======" + Thread.currentThread().getName() );
            cyclicBarrier .await();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        System. out .println(name +"：其他逗b把桩都打完了，又得忙活了。。。" + "=======" + Thread.currentThread().getName() );


    }

}
