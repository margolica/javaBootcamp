package object;

public class Hen implements Runnable {
    private Thread threadHen;
    private int count;

    public Hen(int count) {
        threadHen = new Thread(this, "Hen");
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; ++i)
            System.out.println("Hen");
    }

    public Thread getThreadHen() {
        return threadHen;
    }
}
