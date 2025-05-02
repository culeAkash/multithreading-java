package synchronizationDemo;

public class MyThread extends Thread{
    private Counter counter;

    public MyThread(String name,Counter counter){
        super(name);
        this.counter = counter;
    }

    @Override
    public void run() {
       for(int i=0;i<2000;i++){
           this.counter.increment();
       }
    }
}
