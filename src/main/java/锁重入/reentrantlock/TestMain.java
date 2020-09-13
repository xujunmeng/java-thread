package 锁重入.reentrantlock;

public class TestMain {

    public static void main(String[] args) {

        ThreadDomain38 threadDomain38 = new ThreadDomain38();

        MyThread38 myThread1 = new MyThread38(threadDomain38);
        MyThread38 myThread2 = new MyThread38(threadDomain38);
        MyThread38 myThread3 = new MyThread38(threadDomain38);

        myThread1.start();
        myThread2.start();
        myThread3.start();

    }

}
