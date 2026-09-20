import java.util.Random;

public class QuickSort {
    private static final Random random = new Random();

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        sort(a, 0, a.length - 1, metrics);
    }

    private static void sort(int[] a, int low, int high, Metrics metrics) {
        while (low < high) {
            metrics.enterRecursion();

            int pivotIndex = low + random.nextInt(high - low + 1);
            swap(a, low, pivotIndex);
            int pivot = a[low];

            int lt = low;
            int gt = high;
            int i = low + 1;

            while (i <= gt) {
                if (metrics.compareLessThan(a[i], pivot)) {
                    swap(a, lt++, i++);
                } else if (metrics.compareLessThan(pivot, a[i])) {
                    swap(a, i, gt--);
                } else {
                    i++;
                }
            }

            if (lt - low < high - gt) {
                sort(a, low, lt - 1, metrics);
                metrics.exitRecursion();
                low = gt + 1;
            } else {
                sort(a, gt + 1, high, metrics);
                metrics.exitRecursion();
                high = lt - 1;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}