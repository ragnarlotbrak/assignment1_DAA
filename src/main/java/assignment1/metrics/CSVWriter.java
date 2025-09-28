package assignment1.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter implements AutoCloseable {
    private final FileWriter writer;

    public CSVWriter(String filePath) throws IOException {
        this.writer = new FileWriter(filePath);
        writer.write("Algorithm, Size, Comparisons, Swaps, MaxDepth\n");
    }

    public void writeRecord(String algorithm, int n, long comparisons, long swaps, int maxDepth) throws IOException {
        writer.write(String.format("%s, %d, %d, %d, %d\n", algorithm, n, comparisons, swaps, maxDepth));
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
