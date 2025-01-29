package object;

public class Producer implements Runnable {
    private Object obj;
    private int count;

    public Producer(Object obj, int count) {
        this.obj = obj;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; ++i)
            obj.egg();
    }
}
