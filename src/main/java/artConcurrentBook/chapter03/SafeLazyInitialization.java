package artConcurrentBook.chapter03;

/**
 * @author james
 * @date 2020/4/23
 */
public class SafeLazyInitialization {
    private static Instance instance;

    public synchronized static Instance getInstance() {
        if (instance == null)
            instance = new Instance();
        return instance;
    }

    static class Instance {
    }
}