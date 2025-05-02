//Thread.State -> Java thread states enum
public class LifecycleDemo extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LifecycleDemo t1 = new LifecycleDemo();
        System.out.println(t1.getState()); // NEW State here

        t1.start();
        System.out.println(t1.getState()); // RUNNABLE HERE

        Thread.sleep(200);// main thread goes to sleep here, i.e goes to TIMED_WAITING state and gives time for t1 to run

        System.out.println(t1.getState());// Since, at this point the main waited for 200 millis and in the meantime t1 is sleeping for 2000
        // millis, here t1 is in TIMED_WAITING state

        t1.join();// Main method waits for the thread t1 to perform its action and get terminated before executing further, hence goes into WAITING state(main method)

        // the main method will reach here only when t1 is terminated because of the called join()
        System.out.println(t1.getState());
    }
}
