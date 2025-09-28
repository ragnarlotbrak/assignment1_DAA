package assignment1.util;

import java.util.*;
import assignment1.metrics.*;

public class Metrics {
    private final Counter comparisons = new Counter();
    private final Counter swaps = new Counter();
    private final DepthTracker depthTracker = new DepthTracker();

    public void incComp() {comparisons.increment();}
    public void incSwap() {swaps.increment();}
    public void addComp(long value) {comparisons.add(value);}
    public void addSwap(long value) {swaps.add(value);}


    public void enterRec() {depthTracker.enter(); }
    public void exitRec() {depthTracker.exit(); }


    public long getComparisons() {return comparisons.get();}
    public long getSwaps() {return swaps.get();}
    public int getMaxDepth() {return depthTracker.getMaxDepth();}

    public void reset() {
        comparisons.reset();
        swaps.reset();
        depthTracker.reset();
    }

    @Override
    public String toString() {
        return String.format("comparisons=%d, swaps=%d, MaxDepth=%d", comparisons.get(), swaps.get(), depthTracker.getMaxDepth());
    }
}
