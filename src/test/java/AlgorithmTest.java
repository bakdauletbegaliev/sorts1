import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class AlgorithmTest {
    private final Random random = new Random();

    @Test
    void testMergeSortAndQuickSortCorrectness() {
        for (int t = 0; t < 50; t++) {
            int size = random.nextInt(500) + 1;
            int[] arr1 = random.ints(size, -1000, 1000).toArray();
            int[] arr2 = arr1.clone();
            int[] expected = arr1.clone();

            Arrays.sort(expected);
            MergeSort.sort(arr1, new Metrics());
            QuickSort.sort(arr2, new Metrics());

            assertArrayEquals(expected, arr1);
            assertArrayEquals(expected, arr2);
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
}