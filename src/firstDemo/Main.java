package firstDemo;

public class Main {
    public static void main(String[] args) {
        // main thread will run here


        // creating thread using thread class
        ExtendingThread world = new ExtendingThread();
        // calling the start() will call the run() defined in the extending class
        world.start();

        // creating thread using Runnable interface
        ImplementingRunnable world1 = new ImplementingRunnable();
        Thread t1 = new Thread(()->{
            for(int i=0;i<10000;i++){
                System.out.println("World");
            }
        });
        t1.start();

        for(int i=0;i<10000;i++){
            System.out.println("Hello");
        }
    }
}
