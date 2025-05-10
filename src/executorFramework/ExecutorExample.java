package executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class ExecutorExample {

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = newFixedThreadPool(2);
        for(int i=1;i<10;i++){
                int finalI = i;
                executorService.submit(()->{
                        try {
                            System.out.println("Factorial of " + finalI + " is " + factorial(finalI));
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
        }
        executorService.shutdown();
        try {
            if(!executorService.awaitTermination(1, TimeUnit.MICROSECONDS)){
                System.out.println("Waiting...");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static int factorial(int N) throws InterruptedException {
        Thread.sleep(200);
        if(N<=1)return 1;
        return N * factorial(N-1);
    }
}


//Example using manual thread creation and management
//public class ExecutorExample {
//    public static void main(String[] args) throws InterruptedException {
//        long startTime = System.currentTimeMillis();
//        Thread threads[] = new Thread[9];
//        for(int i=1;i<10;i++){
//            int finalI = i;
//            threads[i-1] = new Thread(
//                    ()->{
//                        try {
//                            System.out.println("Factorial of " + finalI + " is " + factorial(finalI));
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                        }
//                    }
//            );
//            threads[i-1].start();
//        }
//        for(Thread thread : threads){
//            thread.join();
//        }
//        System.out.println(System.currentTimeMillis() - startTime);
//
//    }
//}