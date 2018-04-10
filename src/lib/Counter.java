package lib;

public class Counter {
    protected long count = 0;
    
    public void add(long value) {
        this.count = this.count + value;
    }
    
    synchronized public long getCount() {
        return count;
    }
}
