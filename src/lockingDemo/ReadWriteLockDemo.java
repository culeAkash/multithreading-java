package lockingDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteCounter counter = new ReadWriteCounter();

        Runnable readTask = ()->{
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName() + " read: " + counter.getCount());
            }
        };

        Runnable writeTask = ()->{
            for(int i=0;i<10;i++){
                counter.increment();
                System.out.println(Thread.currentThread().getName() + " incremented.");
            }
        };

        Thread read1 = new Thread(readTask);
        Thread read2 = new Thread(readTask);
        Thread write = new Thread(writeTask);

        write.start();
        read1.start();
        read2.start();

        write.join();
        read1.join();
        read2.join();

        System.out.println("Final counter count: " + counter.getCount());
    }
}

class ReadWriteCounter{
    private int count;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment(){
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " acquired write lock.");
            Thread.sleep(100);
            this.count++;
            System.out.println(Thread.currentThread().getName() + " incremented to: " + count);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + " released write lock.");
        }
    }

    public int getCount(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " acquired read lock.");
            return this.count;
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " released read lock.");
        }
    }

}
