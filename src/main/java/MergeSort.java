public class MergeSort {
    private static final int CUTOFF = 15;

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1, metrics);
    }

    private static void sort(int[] a, int[] aux, int low, int high, Metrics metrics) {
        metrics.enterRecursion();

        if (high - low <= CUTOFF) {
            insertionSort(a, low, high, metrics);
            metrics.exitRecursion();
            return;
        }

        int mid = low + (high - low) / 2;
        sort(a, aux, low, mid, metrics);
        sort(a, aux, mid + 1, high, metrics);

        merge(a, aux, low, mid, high, metrics);

        metrics.exitRecursion();
    }

    private static void merge(int[] a, int[] aux, int low, int mid, int high, Metrics metrics) {
        System.arraycopy(a, low, aux, low, high - low + 1);

        int i = low;
        int j = mid + 1;

        for (int k = low; k <= high; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > high) {
                a[k] = aux[i++];
            } else if (metrics.compareLessThan(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static void insertionSort(int[] a, int low, int high, Metrics metrics) {
        for (int i = low + 1; i <= high; i++) {
            for (int j = i; j > low && metrics.compareLessThan(a[j], a[j - 1]); j--) {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
    }
}