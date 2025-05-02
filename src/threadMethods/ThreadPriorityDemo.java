package threadMethods;

public class ThreadPriorityDemo {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("Minimum Priority");
        MyThread t2 = new MyThread("Medium Priority");
        MyThread t3 = new MyThread("Maximum Priority");

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }


}

class MyThread extends Thread{

    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i=0;i<5;i++){
            // heavy operations for simulation
            StringBuilder a = new StringBuilder();
            for(int j=0;j<100000;j++){
                a.append("a");
            }
            System.out.println(Thread.currentThread().getName() + "- Priority: " + Thread.currentThread().getPriority() + "- count: " + i);
        }
    }
}


