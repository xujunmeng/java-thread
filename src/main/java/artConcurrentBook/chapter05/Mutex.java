package artConcurrentBook.chapter05;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author james
 * @date 2020/6/16
 */
public class Mutex {

    //静态内部类，自定义同步器
    static class Sync extends AbstractQueuedSynchronizer {

        //是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }


    }

}
