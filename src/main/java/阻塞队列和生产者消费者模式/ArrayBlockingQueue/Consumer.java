package 阻塞队列和生产者消费者模式.ArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 定义消费者
 * @author james
 * @date 2020/3/30
 */
public class Consumer extends Thread{

    private ArrayBlockingQueue<String> abq = null;

    public Consumer(ArrayBlockingQueue<String> abq) {
        this.abq = abq;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                String msg = abq.remove();
                System.out.println("取出数据 : " + msg + " ,剩余数据量 : " + abq.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
