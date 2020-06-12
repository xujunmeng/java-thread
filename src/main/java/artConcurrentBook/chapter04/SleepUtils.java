package artConcurrentBook.chapter04;

import java.util.concurrent.TimeUnit;

/**
 * @author james
 * @date 2020/6/12
 */
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }

}
