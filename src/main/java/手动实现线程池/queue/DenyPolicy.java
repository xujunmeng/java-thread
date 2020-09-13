package 手动实现线程池.queue;

import 手动实现线程池.threadpoll.ThreadPool;

/**
 * 定义一个拒绝策略DenyPolicy
 */
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);

    //直接抛弃任务的拒绝策略
    class DiscardDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            //do nothing
        }
    }

    //该拒绝策略会向任务提交者抛出异常
    class AbortDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RuntimeException("The runnable " + runnable + " will be aborted.");
        }
    }

    //该拒绝策略会使任务在提交者所在的线程中执行任务
    class RunnerDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }

}
