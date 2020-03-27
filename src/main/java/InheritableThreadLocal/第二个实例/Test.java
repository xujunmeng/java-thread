package InheritableThreadLocal.第二个实例;

/**
 * @author james
 * @date 2019/9/24
 */
public class Test {

    public static InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {

        threadLocal.set(new Integer(32));

        Thread thread = new MyThread();
        thread.start();

        System.out.println("main = " + threadLocal.get());


    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("MyThread = " + threadLocal.get());
        }
    }

}
