package lockingDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class BankAccount {
    private int balance = 100;

    private final Lock lock = new ReentrantLock();


    // If we use synchronized keyword and put intrinsic lock when a thread is accessing the resource
    // if there is a heavy duty db call or api call which takes a lot of time, then other threads have to wait for that much time
    // we can't customize this behaviour unless we use explicit locking
    public synchronized void withdrawal1(int amount){
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        if(balance >= amount){
            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
            try {
                Thread.sleep(100000);
                balance -= amount;

            }catch (Exception e){
                System.out.println("Withdrawal failed for "+Thread.currentThread().getName());
            }
            System.out.println(Thread.currentThread().getName() + " completed the withdrawal. Remaining balance: "+this.balance);
        }else{
            System.out.println(Thread.currentThread().getName() + " insufficient balance");
        }
    }

    // Now we will use explicit locking using Lock interface in java
    public void withdrawal(int amount){
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try{
            if(lock.tryLock()){
                if(amount <= balance){
                    System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                    try{
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " completed the withdrawal. Remaining balance: "+this.balance);
                    }catch (InterruptedException ex){
                        Thread.currentThread().interrupt();
                        System.out.println("Withdrawal failed for "+Thread.currentThread().getName());
                    }finally {
                        lock.unlock();
                    }
                }
                else{
                    System.out.println(Thread.currentThread().getName() + " insufficient balance");
                }
            }else{
                System.out.println(Thread.currentThread().getName() + " can't complete as the resource is locked");
            }
        }catch (Exception e){
            Thread.currentThread().interrupt();
            System.out.println("Withdrawal failed for "+Thread.currentThread().getName());
        }

        if(Thread.currentThread().isInterrupted()){
            System.out.println(Thread.currentThread().getName() + " is alive");
        }
    }


}
