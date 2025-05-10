package executorFramework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(new Subsystem("Web Server",3000,cyclicBarrier));
        executorService.submit(new Subsystem("Db",4000,cyclicBarrier));
        executorService.submit(new Subsystem("Cache",2000,cyclicBarrier));
        executorService.submit(new Subsystem("Message Service",1000,cyclicBarrier));


        System.out.println("Main");
        executorService.shutdown();


    }
}


class Subsystem implements Runnable{
    private final String name;
    private final int initializationTime;
    private final CyclicBarrier cyclicBarrier;

    public Subsystem(String name, int initializationTime, CyclicBarrier cyclicBarrier) {
        this.name = name;
        this.initializationTime = initializationTime;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(initializationTime);
            System.out.println(this.name + " has started running...");
            System.out.println("Waiting for other services...");
            cyclicBarrier.await();
            System.out.println(this.name + " is running");
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println("Error");
        }
    }
}