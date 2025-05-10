package volatileVsAtomic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileCounter {
    private AtomicInteger counter = new AtomicInteger(0);

    public void increment(){
        this.counter.incrementAndGet();
    }

    public int getCounter(){
        return this.counter.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        VolatileCounter volatileCounter = new VolatileCounter();

        executorService.submit(()->{
            for(int i=0;i<1000;i++){
                volatileCounter.increment();
            }
        }).get();
        executorService.submit(()->{
            for(int i=0;i<1000;i++){
                volatileCounter.increment();
            }
        }).get();



        System.out.println("Main");
        System.out.println(volatileCounter.getCounter());
        executorService.shutdown();
    }
}
