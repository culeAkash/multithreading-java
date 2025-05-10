package volatileVsAtomic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class SharedObj{
    private volatile boolean flag = false;


    public void setFlagTrue() {
        try {
            Thread.sleep(2000);
            System.out.println("Flag is set to true!!!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.flag = true;
    }

    public void getFlagIfTrue(){
        // Threads make local copy of flag in their cache, we need main memory val to avoid this infinite loop condition
        while(!flag){
            // do nothing
        }
        System.out.println("Flag is true...");
    }
}


public class VolatileExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SharedObj  sharedObj = new SharedObj();

        ExecutorService executorService = Executors.newFixedThreadPool(2);


        Future<?> future = executorService.submit(sharedObj::setFlagTrue);
        Future<?> submitted = executorService.submit(sharedObj::getFlagIfTrue);

        future.get();
        submitted.get();


        System.out.println("Main");
        executorService.shutdown();

    }
}
