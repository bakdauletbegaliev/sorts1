import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class AlgorithmTest {
    private final Random random = new Random();

    @Test
    void testMergeSortCorrectness() {
        for (int t = 0; t < 50; t++) {
            int size = random.nextInt(500) + 1;
            int[] arr = random.ints(size, -1000, 1000).toArray();
            int[] expected = arr.clone();

            Arrays.sort(expected);
            MergeSort.sort(arr, new Metrics());

            assertArrayEquals(expected, arr);
        }
    }

    @Test
    void testQuickSortCorrectness() {
        for (int t = 0; t < 50; t++) {
            int size = random.nextInt(500) + 1;
            int[] arr = random.ints(size, -1000, 1000).toArray();
            int[] expected = arr.clone();

            Arrays.sort(expected);
            QuickSort.sort(arr, new Metrics());

            assertArrayEquals(expected, arr);
        }
    }

    @Test
    void testQuickSelectCorrectness() {
        for (int t = 0; t < 50; t++) {
            int size = random.nextInt(500) + 1;
            int[] arr = random.ints(size, -1000, 1000).toArray();
            int k = random.nextInt(size);

            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            int expected = sorted[k];

            int actual = QuickSelect.select(arr, k, new Metrics());
            assertEquals(expected, actual);
        }
    }

    @Test
    void testMetricsCollection() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        Metrics metrics = new Metrics();
        MergeSort.sort(arr, metrics);

        assertTrue(metrics.comparisons > 0);
        assertTrue(metrics.maxDepth > 0);
    }
}
