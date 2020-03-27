package 锁重入;

/**
 * @author james
 * @date 2019/3/9
 */
public class Main {

    public static void main(String[] args) {
        LoggingWidget loggingWidget = new LoggingWidget();
        loggingWidget.doSomething();
    }

}
