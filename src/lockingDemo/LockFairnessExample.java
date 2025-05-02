package lockingDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockFairnessExample {

    private final Lock lock = new ReentrantLock(true);

    public void fairLock(){
        lock.lock();
        try{
            System.out.println("Thread has acquired the lock " + Thread.currentThread().getName());
            Thread.sleep(1000);
        }catch (Exception e){
//            System.out.println("");
            Thread.currentThread().interrupt();
        }finally {
            System.out.println("Thread has unlocked the resource " + Thread.currentThread().getName());
            lock.unlock();
        }
    }
}

class FairnessMain{
    public static void main(String[] args) {
        LockFairnessExample obj = new LockFairnessExample();

        Thread t1 = new Thread(obj::fairLock,"Thread1");
        Thread t2 = new Thread(obj::fairLock,"Thread2");
        Thread t3 = new Thread(obj::fairLock,"Thread3");

        t1.start();
        t2.start();
        t3.start();
    }
}
