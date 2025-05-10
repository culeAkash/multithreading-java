package executorFramework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorClassMethodsExample {
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future<Integer> future = executorService.submit(() -> 42);
//        System.out.println(future.get());
//        if(future.isDone()){
//            System.out.println("Work is done!");
//        }
//        executorService.shutdown();
//    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<Integer> callable1 = () ->{
            Thread.sleep(1000);
            System.out.println("Task 1");
            return 1;
        };

        Callable<Integer> callable2 = () ->{
            Thread.sleep(1000);
            System.out.println("Task 2");
            return 2;
        };

        Callable<Integer> callable3 = () ->{
            Thread.sleep(1000);
            System.out.println("Task 3");
            return 3;
        };

        List<Future<Integer>> futureList = executorService
                .invokeAll(Arrays.asList(callable1, callable2, callable3));

        for(Future<Integer> future : futureList){
            System.out.println(future.get());
        }

        executorService.shutdown();
        executorService.awaitTermination(1,TimeUnit.SECONDS);
        System.out.println("Hello World");
    }
}
