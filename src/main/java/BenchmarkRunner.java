import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000};
        String[] types = {"random", "sorted", "duplicates"};

        try (PrintWriter writer = new PrintWriter(new FileWriter("results.csv"))) {
            writer.println("Algorithm,InputType,Size,Comparisons,MaxDepth,TimeNs");

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

        writer.printf("%s,%s,%d,%d,%d,%d%n", alg, type, size, metrics.comparisons, metrics.maxDepth, duration);
    }

    private static int[] generateArray(int size, String type) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            if (type.equals("random")) {
                arr[i] = random.nextInt(100000);
            } else if (type.equals("sorted")) {
                arr[i] = i;
            } else if (type.equals("duplicates")) {
                arr[i] = random.nextInt(5);
            }
        }
        return arr;
    }
}