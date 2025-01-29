package object;

public class Egg implements Runnable {
    private Thread threadEgg;
    private int count;

    public Egg(int count) {
        threadEgg = new Thread(this, "Egg");
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; ++i)
            System.out.println("Egg");
    }

    public Thread getThreadEgg() {
        return threadEgg;
    }
}
