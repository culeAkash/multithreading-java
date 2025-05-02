package synchronizationDemo;

public class SyncMethodExample {
    public void printNumbers() {
        System.out.println(Thread.currentThread().getName() + " preparing");// can be accessed simultaneously by all threads
        // critical section starts and only one thread can use one at a time.
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " entered");
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName() + " exiting");
        }
    }
}

class TestSyncMethod{
    public static void main(String[] args) {
        SyncMethodExample obj = new SyncMethodExample();

        Runnable task = obj::printNumbers;

        Thread t1 = new Thread(task,"Thread-A");
        Thread t2 = new Thread(task,"Thread-B");

        t1.start();
        t2.start();
    }
}
