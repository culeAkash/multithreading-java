package executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureClassMethodsExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> future = executorService.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Exception occurred: " + e);
            }
            System.out.println("Hello");
            return 42;
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Exception occurred: " + e);
        }
        // even if cancelled the process inside the thread will surely run, just the furture will be cancelled, hence no return val can be acquired
        future.cancel(false);
        System.out.println(future.isCancelled());
        System.out.println(future.isDone());
        executorService.shutdown();
    }
}
