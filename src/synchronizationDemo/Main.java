package synchronizationDemo;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(0);

        MyThread t1 = new MyThread("Thread1",counter);
        MyThread t2 = new MyThread("Thread2",counter);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(counter.getCount());
    }
}
