package synchronizationDemo;

public class Counter {
    private int count = 0;

    public Counter(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment(){
        synchronized (this) {
            count++;
        }
    }
}
