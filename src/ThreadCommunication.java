class SharedResource{
    private int data;
    private boolean hasData;

    public synchronized void produce(int val){
        while(hasData){
            // if data is already in buffer, no need to produce new data wait for consumer to consume data in buffer
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // push data to buffer
        data = val;
        hasData = true;
        System.out.println(Thread.currentThread().getName() + " produces the data: " + val);
        notify();

    }


    public synchronized void consume(){
        if(!hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        notify();
        System.out.println(Thread.currentThread().getName() + " consumed the data: " + data);
    }
}


class Producer implements Runnable{

    private SharedResource sharedResource;

    public Producer(SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            sharedResource.produce(i);
        }
    }
}

class Consumer implements Runnable{
    private SharedResource sharedResource;

    public Consumer(SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            sharedResource.consume();
        }
    }
}




public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread producer = new Thread(new Producer(sharedResource));
        Thread consumer = new Thread(new Consumer(sharedResource));

        producer.start();
        consumer.start();
    }
}
