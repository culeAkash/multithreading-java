package executorFramework;

import java.util.concurrent.*;

public class CountDownLatchExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<?> future1 = executorService.submit(new DependentService(latch));
        Future<?> future2 = executorService.submit(new DependentService(latch));
        Future<?> future3 = executorService.submit(new DependentService(latch));
        Future<?> future4 = executorService.submit(new DependentService(latch));

//        Thread thread1 = new Thread(new DependentService(latch));
//        Thread thread2 = new Thread(new DependentService(latch));
//        Thread thread3 = new Thread(new DependentService(latch));
//
//        thread1.start();
//        thread2.start();
//        thread3.start();

        latch.await();

        System.out.println("Main");
        executorService.shutdown();
    }
}


class DependentService implements Runnable{

    private final CountDownLatch latch;

    public DependentService(CountDownLatch latch){
        this.latch = latch;
    }

    public void run(){
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " current thread");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            latch.countDown();
        }
    }
}
