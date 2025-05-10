package executorFramework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


        Executors.newCachedThreadPool();

        // will be executed after a delay of 1 second
//        scheduledExecutorService.schedule(()->{
//            System.out.println("Hello World");
//        },
//        1,
//                TimeUnit.SECONDS);


        // whether previous task is complete or not, next task will get executed after period time
        scheduledExecutorService.scheduleAtFixedRate(()-> System.out.println("Hello World"),5,5,TimeUnit.SECONDS);

        // runs after initial delay and only delay time after previous task was run and terminated
        scheduledExecutorService.scheduleWithFixedDelay(()-> System.out.println("Hello World"),5,5,TimeUnit.SECONDS);

        // only will be shutdown after 20 seconds so that above method can run periodically with an initial delay
        scheduledExecutorService.schedule(()->{
            System.out.println("Initiating shutdown");
            scheduledExecutorService.shutdown();
        },20,TimeUnit.SECONDS);

//        scheduledExecutorService.shutdown();
    }
}
