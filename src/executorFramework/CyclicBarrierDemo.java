package executorFramework;

import java.util.concurrent.*;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CountDownLatch latch = new CountDownLatch(3);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<?> future1 = executorService.submit(new DependentService1(cyclicBarrier));
        Future<?> future2 = executorService.submit(new DependentService1(cyclicBarrier));
        Future<?> future3 = executorService.submit(new DependentService1(cyclicBarrier));
//        Future<?> future4 = executorService.submit(new DependentService1(cyclicBarrier));


//        Thread thread1 = new Thread(new DependentService(latch));
//        Thread thread2 = new Thread(new DependentService(latch));
//        Thread thread3 = new Thread(new DependentService(latch));
//
//        thread1.start();
//        thread2.start();
//        thread3.start();

        System.out.println("Main");
        executorService.shutdown();
    }
}


class DependentService1 implements Runnable{

    private final CyclicBarrier barrier;

    public DependentService1(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    public void run(){
        try {
            System.out.println(Thread.currentThread().getName() + " current thread, service started");
            Thread.sleep(1000);
            barrier.await();
            System.out.println("Waiting at barrier...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
