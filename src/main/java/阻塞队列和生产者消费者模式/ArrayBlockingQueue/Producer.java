package 阻塞队列和生产者消费者模式.ArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 定义生产者
 * @author james
 * @date 2020/3/30
 */
public class Producer extends Thread {

    private ArrayBlockingQueue<String> abq = null;

    public Producer(ArrayBlockingQueue<String> abq) {
        this.abq = abq;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                Thread.sleep(500);
                ++i;
                abq.put("" + i);
                System.out.println("存放数据 ：" + i + " ,剩余数据量 : " + abq.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
