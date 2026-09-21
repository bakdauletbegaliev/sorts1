import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 1000000};
        String[] types = {"random", "sorted", "duplicates"};

        try (PrintWriter writer = new PrintWriter(new FileWriter("results.csv"))) {
            writer.println("algorithm,input,n,time_ms,comparisons,max_depth");

            for (int size : sizes) {
                for (String type : types) {
                    benchmarkAlgorithm("MergeSort", size, type, writer);
                    benchmarkAlgorithm("QuickSort", size, type, writer);
                    benchmarkAlgorithm("QuickSelect", size, type, writer);
                }
            }

            System.out.println("Benchmark finished. Results saved to results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void benchmarkAlgorithm(String alg, int size, String type, PrintWriter writer) {
        long[] times = new long[5];

        long comparisons = 0;
        int maxDepth = 0;

        for (int run = 0; run < 5; run++) {
            int[] array = generateArray(size, type);
            Metrics metrics = new Metrics();

            long startTime = System.nanoTime();

            if (alg.equals("MergeSort")) {
                MergeSort.sort(array, metrics);
            } else if (alg.equals("QuickSort")) {
                QuickSort.sort(array, metrics);
            } else if (alg.equals("QuickSelect")) {
                QuickSelect.select(array, size / 2, metrics);
            }

            long duration = System.nanoTime() - startTime;

            times[run] = duration;
            comparisons = metrics.comparisons;
            maxDepth = metrics.maxDepth;
        }

        Arrays.sort(times);

        long medianTime = times[2];
        double timeMs = medianTime / 1_000_000.0;

        writer.printf(
                "%s,%s,%d,%.3f,%d,%d%n",
                alg,
                type,
                size,
                timeMs,
                comparisons,
                maxDepth
        );

        System.out.println(
                alg + " | " + type + " | " + size +
                        " | " + timeMs + " ms"
        );
    }

    private static int[] generateArray(int size, String type) {
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            if (type.equals("random")) {
                arr[i] = random.nextInt(100000);
            } else if (type.equals("sorted")) {
                arr[i] = i;
            } else if (type.equals("duplicates")) {
                arr[i] = random.nextInt(10);
            }
        }

        return arr;
    }
}
