package object;

public class Consumer implements Runnable {
    private Object obj;
    private int count;

    public Consumer(Object obj, int count) {
        this.obj = obj;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; ++i)
            obj.hug();
    }
}
