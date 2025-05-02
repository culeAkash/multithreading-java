package threadMethods;

public class ThreadMethodsDemo {
    public static void main(String[] args) throws InterruptedException {
        MyThread1 t1 = new MyThread1();
        System.out.println(Thread.currentThread());
        t1.start();

        InterruptExampleMyThread t2 = new InterruptExampleMyThread();
        t2.start();
        Thread.sleep(1);
        t2.interrupt();

        YieldExampleMyThread t3 = new YieldExampleMyThread("t3");
        YieldExampleMyThread t4 = new YieldExampleMyThread("t4");
        t3.start();
        t4.start();

        DaemonExampleMyThread t5 = new DaemonExampleMyThread("Daemon Thread");
        t5.setDaemon(true);
        t5.start();
        Thread.sleep(2);
        System.out.println("Main ended");
    }
}

class DaemonExampleMyThread extends Thread{
    public DaemonExampleMyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for(int i=0;i<200000;i++){
            System.out.println(i);
        }
    }
}



class YieldExampleMyThread extends Thread{

    public YieldExampleMyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        //            Thread.sleep(1000);
        for(int i=0;i<5;i++){
            Thread.yield();
            System.out.println(this.getName() + " " + i);
        }
    }
}

class InterruptExampleMyThread extends Thread{
    @Override
    public void run() {
        //            Thread.sleep(1000);
        for(int i=0;i<10000;i++){
            if(this.isInterrupted()){
                return;
            }
            System.out.println(i);
        }
    }
}


class MyThread1 extends Thread{
    @Override
    public void run() {
        System.out.println("Thread is running...");
        System.out.println(Thread.currentThread());
        for(int i=0;i<5;i++){
            try {
                Thread.sleep(1000);
                System.out.println(i);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
