package lockingDemo;

class Pen{
    public synchronized void writeWithPenAndPaper(Paper paper){
        System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying to access paper " + paper);
        // here after acquiring the intrinsic lock of Pen class, thread will now try to acquire the lock of paper class as all methods are synchronized
        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
    }
}

class Paper{
    public synchronized void writeWithPenAndPaper(Pen pen){
        System.out.println(Thread.currentThread().getName() + " is using paper " + this + " and trying to access pen " + pen);
        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using paper " + this);
    }
}

class Task1 implements Runnable{

    Pen pen;
    Paper paper;

    public Task1(Pen pen,Paper paper){
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper);
    }
}

class Task2 implements Runnable{

    Pen pen;
    Paper paper;

    public Task2(Pen pen,Paper paper){
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        synchronized (pen){// it says that first lock the pen then you can aqcuire lock of paper, but pen lock is acquired by t1
            // and t1 can also acquire the paper lock as paper can be locked by t2 only when t2 acquires the pen lock
            paper.writeWithPenAndPaper(pen);
        }

    }
}

public class DeadlockExample {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread t1 = new Thread(new Task1(pen,paper));
        Thread t2 = new Thread(new Task2(pen,paper));

        t1.start();
        t2.start();
    }
}

// We can solve deadlock situation by making the threads acquire locks in consistent ordering
