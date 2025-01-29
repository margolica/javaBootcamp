package object;

public class Object {
    private StringBuilder str = new StringBuilder("Egg");

    public synchronized void egg() {
        while (str.toString().equals("Egg")) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Egg");
        str.replace(0, 3, "Egg");
        notify();
    }

    public synchronized void hug() {
        while (str.toString().equals("Hug")) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Hug");
        str.replace(0, 3, "Hug");
        notify();
    }
}
