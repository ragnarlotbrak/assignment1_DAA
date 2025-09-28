package assignment1.util;

import java.util.*;

public class Metrics {
    public long comparisons = 0;
    public long swaps = 0;
    public long recDepth = 0;
    public long maxDepth = 0;

    public void incComp() {comparisons++;}
    public void incSwap() {swaps++;}

    public void enterRec() {recDepth++; maxDepth = Math.max(maxDepth, recDepth);}
    public void exitRec() {recDepth--; }

    public void reset() {
        comparisons = swaps = recDepth = maxDepth = 0;
    }

    @Override
    public String toString() {
        return "comparisons = " + comparisons + ", swaps = " + swaps + ", maxDepth = " + maxDepth;
    }
}
