package lockingDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantExample {

    private final Lock lock = new ReentrantLock();

    public void outerMethod(){
        lock.lock();
        try{
            System.out.println("Outer method " + Thread.currentThread().getName());
            innerMethod();
        }catch (Exception ex){
            System.out.println("Fail");
        }
        finally {
            lock.unlock();
        }
    }

    private void innerMethod() {
        lock.lock();
        try{
            System.out.println("Inner method " + Thread.currentThread().getName());
        }catch (Exception ex){
            System.out.println("Fail");
        }
        finally {
            lock.unlock();
        }
    }
}


class ReentrantMain{
    public static void main(String[] args) {
        ReEntrantExample obj = new ReEntrantExample();

        Runnable task = obj::outerMethod;

        Thread t1 = new Thread(task,"Thread 1");
        Thread t2 = new Thread(task,"Thread 2");

        t1.start();
        t2.start();
    }
}

/*
In the above example, the Outer method locks the resource and calls the inner method,
The inner method again calls the lock(), i.e, depend on itself to unlock the previously held lock. This condition is called deadlock

ReentrantLock class implements to prevent deadlock. It maintains a count for a thread about how many times a lock is held for the thread
and each corresponding lock must pair with an unlock and then only it releases the resource

If the number of unlock exceeds the lock, then gives an Exception

 */
