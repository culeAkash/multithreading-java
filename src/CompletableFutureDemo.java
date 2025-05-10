import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
//                runnable->{
//            Thread thread = new Thread(runnable);
//            thread.setDaemon(true);
//            return thread;
//        });
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
                System.out.println("Worker1");
            }catch(InterruptedException e) {

            }
                return "ok";

        },executorService);

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
                System.out.println("Worker2");
            }catch(InterruptedException e) {

            }
            return "ok";

        },executorService);

//        completableFuture.get();

//        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(completableFuture1, completableFuture2);
//
//        completableFuture.get();
        System.out.println("Main");
        executorService.shutdownNow();
    }
}
