package assignment1.metrics;

public class Counter {
    private long count = 0;

    public void increment() { count++; }
    public void add(long value) { count += value; }
    public long get() { return count; }
    public void reset() { count = 0; }
}
