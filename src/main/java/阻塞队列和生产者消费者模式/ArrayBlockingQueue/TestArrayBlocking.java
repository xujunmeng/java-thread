package 阻塞队列和生产者消费者模式.ArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author james
 * @date 2020/3/30
 */
public class TestArrayBlocking {

    public static void main(String[] args) throws InterruptedException {
//        insertBlocking();
//        fetchBlocking();

        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(10);
        testProducerConsumer(abq);
    }


    /**
     * 此防范用来测试生产者和消费者
     * 为了让程序在获取不到元素时不报错有两种方式：
     * 1.让生产者的生产速度大于消费者的消费速度
     * 2.在消费者获取资源出错时让消费者线程暂停一段时间，不输入错误
     */
    public static void testProducerConsumer(ArrayBlockingQueue<String> abq) {
        Consumer consumer = new Consumer(abq);
        Producer producer = new Producer(abq);
        consumer.start();
        producer.start();
    }


    /**
     * 此方法展示了ArrayBlockingQueue的插入阻塞特性：
     * 如果队列已经满了，那么插入的操作就会被阻塞，程序执行就会被迫暂停
     */
    public static void insertBlocking() throws InterruptedException {
        ArrayBlockingQueue<String> names = new ArrayBlockingQueue<>(1);
        names.put("a");

        //从这里开始后面的就不会被执行了
        names.put("b");

        System.out.println("end....");
    }

    /**
     * 此方法展示了ArrayBlockingQueue的取出阻塞特性：
     * 如果队列为空，那么取出的操作就会被阻塞，程序执行就会报错
     */
    public static void fetchBlocking() throws InterruptedException {
        ArrayBlockingQueue<String> names = new ArrayBlockingQueue<String>(1);
        names.put("a");
        names.remove();
        names.remove();
        names.put("b");

        System.out.println("end ...");

    }

}
